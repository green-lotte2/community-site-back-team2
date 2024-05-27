package kr.communityserver.controller;

import kr.communityserver.config.AppInfo;
import lombok.Value;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

@Slf4j
@Configuration
@CrossOrigin(origins = "http://localhost:3000")
@RestController
public class MainController {

    /*
    @ResponseBody
    @GetMapping("/")
    public ResponseEntity practice(){
        Map<String, String> map = new HashMap<>();
        map.put("1","1");
        return ResponseEntity.ok().body(map);
    }
    */


    @GetMapping("/")
    public String home(){

        return "/";
    }

    @GetMapping("/main")
        public String dashboard(){

            return "/main";
        }


    @RestController
    public class ControllerTest {

        @RequestMapping("/index")
        public String Test(){

            return "connection 김준형은 천재";
        }
    }

    @GetMapping("/snapshot")
    public String Snapshot() {

        String snapshot = "안녕";

        return snapshot;
    }


    /*
    private String aa = "안녕" ;

    @GetMapping("/snapshot")
    public Map<String, String> Snapshot() {
        Map<String, String> response = new HashMap<>();
        response.put("snapshot", String.valueOf(aa));

        log.info("appInfo : "+aa);

        return response;
    }
     */

}
