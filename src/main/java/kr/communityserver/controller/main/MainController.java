package kr.communityserver.controller.main;

import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@Configuration
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

    @RestController
    public class ControllerTest {

        @RequestMapping("/index")
        public String Test(){

            return "connection 준형이";
        }
    }

}
