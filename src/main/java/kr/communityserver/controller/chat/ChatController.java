package kr.communityserver.controller.chat;

import kr.communityserver.service.ChatService;
import lombok.extern.slf4j.Slf4j;
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
    

    //aside에 내 채팅방 이름 뜨게
    @ResponseBody
    @GetMapping ("/chattingRoom")
    public ResponseEntity chattingRoom(@RequestParam(name = "userName")String userId) {
        return chatService.findChatRoom(userId);
    }
    
    
    //채팅방 찾는
    @ResponseBody
    @GetMapping ("/myRoom")
    public ResponseEntity chattingRoom(@RequestParam(name = "room")int room) {
        return chatService.findChatRoom(room);
    }
    
    //기존 채팅 불러오기
    @ResponseBody
    @GetMapping ("/beforeChat")
    public ResponseEntity beforeChat(@RequestParam(name = "room")int room) {

        return chatService.searchBefore(room);
    }

    //채팅방 생성
    @ResponseBody
    @GetMapping ("/chatRegister")
    public ResponseEntity chatRegister(@RequestParam(name = "chatName")String chatName ,
                                       @RequestParam(name = "userId")String userId ) {
        return chatService.makeChat(userId, chatName);
    }

    //채팅방 초대
    @ResponseBody
    @GetMapping ("/chatSearchUser")
    public ResponseEntity chatSearchUser(@RequestParam(name = "userEmail")String userEmail ,
                                         @RequestParam(name = "room")int room) {
        return chatService.inviteUser(userEmail, room);
    }

    //채팅방 멤버조회
    @ResponseBody
    @GetMapping ("/chatMembers")
    public ResponseEntity chatMembers( @RequestParam(name = "room")int room) {
        return chatService.searchMembers(room);
    }


}
