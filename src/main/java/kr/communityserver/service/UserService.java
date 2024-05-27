package kr.communityserver.service;

import jakarta.mail.Message;
import jakarta.mail.internet.InternetAddress;
import jakarta.mail.internet.MimeMessage;
import jakarta.servlet.http.HttpSession;
import kr.communityserver.dto.UserDTO;
import kr.communityserver.entity.User;
import kr.communityserver.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.beans.factory.annotation.Value;

import java.util.concurrent.ThreadLocalRandom;

@Log4j2
@RequiredArgsConstructor
@Service
public class UserService {

    private final PasswordEncoder passwordEncoder;
    private final UserRepository userRepository;
    private final ModelMapper modelMapper;
    private final JavaMailSender javaMailSender;

    public String register(UserDTO userDTO) {
        String encoded = passwordEncoder.encode(userDTO.getPass());
        userDTO.setPass(encoded);

        User user = modelMapper.map(userDTO, User.class);
        User savedUser = userRepository.save(user);

        return savedUser.getUid();
    }

    public boolean existsByEmail(String email) {
        return userRepository.existsByEmail(email);
    }

    @Value("${spring.mail.username}")
    private String sender;

    public void sendEmailCode(HttpSession session, String receiver){

        log.info("sender : " + sender);

        // MimeMessage 생성
        MimeMessage message = javaMailSender.createMimeMessage();

        // 인증코드 생성 후 세션 저장
        String code = String.valueOf(ThreadLocalRandom.current().nextInt(100000, 1000000));
        session.setAttribute("code", code);

        log.info("code : " + code);

        String title = "인증코드 입니다.";
        String content = "<h1>인증코드는 " + code + "입니다.</h1>";


        try {
            message.setSubject(title);
            message.setFrom(new InternetAddress(sender, "보내는 사람", "UTF-8"));
            message.addRecipient(Message.RecipientType.TO, new InternetAddress(receiver));
            message.setSubject(title);
            message.setContent(content, "text/html;charset=UTF-8");

            javaMailSender.send(message);

        } catch (Exception e) {
            log.error("error={}", e.getMessage());
        }

    }
}


