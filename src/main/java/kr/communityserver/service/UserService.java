package kr.communityserver.service;

import jakarta.mail.Message;
import jakarta.mail.internet.InternetAddress;
import jakarta.mail.internet.MimeMessage;
import kr.communityserver.dto.UserDTO;
import kr.communityserver.entity.User;
import kr.communityserver.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import net.coobird.thumbnailator.Thumbnails;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;
import java.util.UUID;
import java.util.concurrent.ThreadLocalRandom;

@Log4j2
@RequiredArgsConstructor
@Service
public class UserService {

    private final PasswordEncoder passwordEncoder;
    private final UserRepository userRepository;
    private final ModelMapper modelMapper;
    private final JavaMailSender javaMailSender;

    public User register(UserDTO userDTO) {
        String encoded = passwordEncoder.encode(userDTO.getPass());
        userDTO.setPass(encoded);

        log.info("userDTO : " + userDTO);
        User user = modelMapper.map(userDTO, User.class);

        if(userDTO.getProfileImg() == null){
            user.setImage("everybody24.png");
            User savedUser = userRepository.save(user);
            return savedUser;
        }

        try{
            MultipartFile img1 = userDTO.getProfileImg();

            if(img1.getOriginalFilename() != null && img1.getOriginalFilename() != ""){
                UserDTO uploadedImage = uploadProfileImage(img1);

                if(uploadedImage != null){

                    UserDTO imageDTO = uploadedImage;
                    log.info("imageDTO : " + imageDTO);

                    userDTO.setSName(uploadedImage.getSName());
                    userDTO.setImage(uploadedImage.getSName());

                    log.info("sName : " + userDTO.getSName());
                    log.info("image : " + userDTO.getImage());
                }

                User user1 = modelMapper.map(userDTO, User.class);
                log.info("user1 : " + user1);

                User savedUser = userRepository.save(user1);
                log.info("savedUser : " + savedUser);

                UserDTO userDTO1 = uploadedImage;
                log.info("userDTO1 : " + userDTO1);

                return savedUser;
            }
        } catch (Exception e){
            return user;
        }

        return user;
    }

    public User modify(UserDTO userDTO) {

        log.info("모디파이서비스1 : " + userDTO);

        // 기존 유저 정보를 가져온다
        User originalUser = userRepository.findById(userDTO.getUid()).orElseThrow(() -> new RuntimeException("유저를 찾을 수 없습니다."));
        log.info("모디파이서비스2 : " + originalUser);

        // 기존의 regDate, role, image를 유지한다
        LocalDateTime originalRegDate = originalUser.getRegDate();
        String originalRole = originalUser.getRole();
        String originalImage = originalUser.getImage();
        log.info("모디파이서비스3 : " + originalImage);

        User user = modelMapper.map(userDTO, User.class);
        log.info("모디파이서비스4 : " + user);

        // 기존 regDate와 role을 새로운 user 객체에 설정한다
        user.setRegDate(originalRegDate);
        user.setRole(originalRole.substring(5)); // "ROLE_" 접두사를 제거하고 설정
        log.info("모디파이서비스5 : " + originalRegDate);
        log.info("모디파이서비스6 : " + originalRole);

        if(userDTO.getProfileImg() == null || userDTO.getProfileImg().isEmpty()){
            user.setImage(originalImage);
            User savedUser = userRepository.save(user);
            return savedUser;
        }

        try{
            MultipartFile img1 = userDTO.getProfileImg();

            if(img1.getOriginalFilename() != null && img1.getOriginalFilename() != ""){
                UserDTO uploadedImage = uploadProfileImage(img1);

                if(uploadedImage != null){

                    UserDTO imageDTO = uploadedImage;
                    log.info("imageDTO : " + imageDTO);

                    userDTO.setSName(uploadedImage.getSName());
                    userDTO.setImage(uploadedImage.getSName());

                    log.info("sName : " + userDTO.getSName());
                    log.info("image : " + userDTO.getImage());
                }

                User user1 = modelMapper.map(userDTO, User.class);
                log.info("user1 : " + user1);

                // 다시 기존 regDate과 role을 설정
                user1.setRegDate(originalRegDate);
                user1.setRole(originalRole.substring(5)); // "ROLE_" 접두사를 제거하고 설정

                User savedUser = userRepository.save(user1);
                log.info("savedUser : " + savedUser);

                UserDTO userDTO1 = uploadedImage;
                log.info("userDTO1 : " + userDTO1);

                return savedUser;
            }
        } catch (Exception e){
            return user;
        }

        return user;
    }

    public Boolean existsByEmail(String email) {
        return userRepository.existsByEmail(email);
    }

    @Value("${spring.mail.username}")
    private String sender;

    public long sendEmailCode(String receiver){

        log.info("sender : " + sender);

        // MimeMessage 생성
        MimeMessage message = javaMailSender.createMimeMessage();

        // 인증코드 생성 후 세션 저장
        String code12 = String.valueOf(ThreadLocalRandom.current().nextInt(100000, 1000000));
        int code = ThreadLocalRandom.current().nextInt(100000, 1000000);
        log.info("code : " + code);

        long savedCode = ((long) (code + code) * code) - 1;
        log.info("savedCode : " + savedCode);

        String title = "일름보 인증코드 입니다.";
        String content = "<h1>인증코드는 " + code + "입니다.</h1>";

        try {
            message.setSubject(title);
            message.setFrom(new InternetAddress(sender, "보내는 사람", "UTF-8"));
            message.addRecipient(Message.RecipientType.TO, new InternetAddress(receiver));
            message.setSubject(title);
            message.setContent(content, "text/html;charset=UTF-8");

            javaMailSender.send(message);

            return savedCode;

        } catch (Exception e) {
            log.error("error={}", e.getMessage());

            return 0;
        }
    }

    @Value("${file.upload.path}")
    private String fileUploadPath;

    public UserDTO uploadProfileImage(MultipartFile file){
        String path = new java.io.File(fileUploadPath).getAbsolutePath();

        if(!file.isEmpty()){
            try{
                // 원본파일
                String originalFilename = file.getOriginalFilename();
                // 확장자 파일
                String extension = originalFilename.substring(originalFilename.lastIndexOf("."));
                log.info("originalFilename : " + originalFilename);

                // 저장될 파일 이름
                String sName = UUID.randomUUID().toString() + extension;
                log.info("sName : " + sName);

                // 파일이 저장될 경로
                java.io.File loc = new File(path, sName);

                Thumbnails.of(file.getInputStream())
                        .size(150, 150)
                        .toFile(loc);

                log.info("loc : " + loc);

                return UserDTO.builder()
                        .oName(originalFilename)
                        .sName(sName)
                        .build();

            }catch (IOException e){
                e.printStackTrace();
            }
        }
        return null;
    }

    public boolean existsById(String uid) {
        return userRepository.existsByUid(uid);
    }

    public String findId(String email, String name){
        User user = userRepository.findIdByEmailAndName(email, name);
        return user.getUid();
    }

    public String findPw(String uid, String email){
        User user = userRepository.findIdByUidAndEmail(uid, email);
        return user.getUid();
    }

    public ResponseEntity changePw(String uid, String pass){
        log.info("changePw4 : " + uid);
        log.info("changePw5 : " + pass);
        String encoded = passwordEncoder.encode(pass);

        Optional<User> userDTO = userRepository.findById(uid);
        User user = modelMapper.map(userDTO, User.class);

        boolean matchesTest = passwordEncoder.matches(pass, user.getPass());
        log.info(matchesTest);
        if(matchesTest){
            return ResponseEntity.ok().body("이미 등록된 비밀번호 입니다.");
        }else{
            user.setPass(encoded);
            userRepository.save(user);
            return ResponseEntity.ok().body("비밀번호가 재설정 되었습니다.");
        }
    }

    public User getUserById(String uid){
        return userRepository.findById(uid).orElse(null);
    }

    public void updateUserGrade(String uid, String grade) {
        Optional<User> optionalUser = userRepository.findById(uid);
        log.info("등급1 : " + optionalUser);
        if (optionalUser.isPresent()) {
            User user = optionalUser.get();
            log.info("등급2 : " + user);
            user.setGrade(grade);
            userRepository.save(user);
            log.info("완전바뀜 : " + user);
        } else {
            throw new RuntimeException("uid에 해당하는 사용자를 찾을 수 없습니다: " + uid);
        }
    }
}


