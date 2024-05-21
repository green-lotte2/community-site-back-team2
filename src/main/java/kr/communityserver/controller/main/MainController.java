package kr.communityserver.controller.main;

import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Slf4j
@Configuration
@Controller
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
    public String main(){
        return "/index";
    }

}
