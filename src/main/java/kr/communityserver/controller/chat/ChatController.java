package kr.communityserver.controller.chat;

import groovy.util.logging.Slf4j;
import kr.communityserver.service.ChatService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import java.util.HashMap;
import java.util.Map;

@Controller
@Slf4j
public class ChatController {

    @Autowired
    private ChatService chatService;

    @ResponseBody
    @GetMapping("/chat")
    public ResponseEntity chatEx(){
        Map<String, String> map = new HashMap<>();
        map.put("1","1");
        return ResponseEntity.ok().body(map);
    }

    @ResponseBody
    @GetMapping ("/chattingRoom")
    public ResponseEntity chattingRoom(@RequestParam(name = "userName")String userId) {
        return chatService.findChatRoom(userId);
    }

    @ResponseBody
    @GetMapping ("/myRoom")
    public ResponseEntity chattingRoom(@RequestParam(name = "room")int room) {
        return chatService.findChatRoom(room);
    }

    @ResponseBody
    @GetMapping ("/beforeChat")
    public ResponseEntity beforeChat(@RequestParam(name = "room")int room) {
        return chatService.searchBefore(room);
    }

}
