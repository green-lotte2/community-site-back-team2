package kr.communityserver.controller.chat;

import groovy.util.logging.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import java.util.HashMap;
import java.util.Map;

@Controller
@Slf4j
public class ChatController {

    @ResponseBody
    @GetMapping("/chat")
    public ResponseEntity chatEx(){
        Map<String, String> map = new HashMap<>();
        map.put("1","1");
        return ResponseEntity.ok().body(map);
    }

    @RequestMapping("/chatting")
    public ModelAndView chat() {
        ModelAndView mv = new ModelAndView();
        mv.setViewName("chatting");
        return mv;
    }

}
