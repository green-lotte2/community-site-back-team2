package kr.communityserver.controller;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import kr.communityserver.DTO.UserDTO;
import kr.communityserver.entity.User;
import kr.communityserver.security.MyUserDetails;
import kr.communityserver.service.UserService;
import kr.communityserver.util.JWTProvider;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

@Log4j2
@RequiredArgsConstructor
@RestController
public class UserController {

    private final UserService userService;
    private final AuthenticationManager authenticationManager;
    private final JWTProvider jwtProvider;


    @PostMapping("/user/login")
    public ResponseEntity login(@RequestBody UserDTO userDTO){

        log.info("login...1 : " + userDTO);

        try {
            // Security 인증 처리
            UsernamePasswordAuthenticationToken authToken
                    = new UsernamePasswordAuthenticationToken(userDTO.getUid(), userDTO.getPass());


            // 사용자 DB 조회
            Authentication authentication = authenticationManager.authenticate(authToken);
            log.info("login...2");

            // 인증된 사용자 가져오기
            MyUserDetails userDetails = (MyUserDetails) authentication.getPrincipal();
            User user = userDetails.getUser();

            log.info("login...3 : " + user);


            // 토큰 발급(액세스, 리프레쉬)
            String access  = jwtProvider.createToken(user, 1); // 1일
            String refresh = jwtProvider.createToken(user, 7); // 7일

            // 리프레쉬 토큰 DB 저장

            // 액세스 토큰 클라이언트 전송
            Map<String, Object> map = new HashMap<>();
            map.put("grantType", "Bearer");
            map.put("username", user.getUid());
            map.put("userRole", user.getRole());
            map.put("accessToken", access);
            map.put("refreshToken", refresh);

            return ResponseEntity.ok().body(map);

        }catch (Exception e){
            log.info("login...3 : " + e.getMessage());
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("user not found");
        }
    }

    @PostMapping("/user")
    public Map<String, String> register(@RequestBody UserDTO userDTO, HttpServletRequest req){

        String sms = (String) req.getSession().getAttribute("sms");

        String regip = req.getRemoteAddr();
        userDTO.setRegip(regip);
        userDTO.setRole("USER");
        userDTO.setSms(sms);
        log.info(userDTO);

        String uid = userService.register(userDTO);
        return Map.of("userid", uid);
    }

    @GetMapping("/checkEmail")
    public ResponseEntity<?> checkEmail(@RequestParam("email") String email){

        Boolean isExist = userService.existsByEmail(email);
        log.info("isExist : " + isExist);

        // Json 생성
        Map<String, String> resultMap = new HashMap<>();

        // 중복 없으면 이메일 인증코드 발송
        if (!isExist){
            log.info("email : " + email);
            long savedCode = userService.sendEmailCode(email);
            resultMap.put("result", "이메일 전송에 성공하였습니다.");
            resultMap.put("savedCode", String.valueOf(savedCode));
            return ResponseEntity.ok().body(resultMap);
        }else{
            resultMap.put("result", "이메일 전송에 실패하였습니다.");
            return ResponseEntity.ok().body(resultMap);
        }
    }

    @GetMapping("/checkEmailCode")
    public ResponseEntity<?> checkEmailCode(@RequestParam("email") String email, @RequestParam("code") String code, @RequestParam("scode") String scode) {

        log.info("email : " + email);
        log.info("code : " + code);
        log.info("scode : " + scode);

        // JSON 출력
        Map<String, String> resultMap = new HashMap<>();
        long before = Long.parseLong(scode);
        long intermediate = (before + 1) / 2;
        long savedCode = (long) Math.sqrt(intermediate);

        String originCode = String.valueOf(savedCode);
        if (originCode.equals(code)) {
            resultMap.put("result", "인증 코드 인식에 성공하였습니다.");
        } else {
            resultMap.put("result", "인증 코드 인식에 실패하셨습니다.");
        }

        return ResponseEntity.ok().body(resultMap);
    }

}
