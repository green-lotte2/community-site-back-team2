package kr.communityserver.controller.chat;

import jakarta.annotation.Resource;
import kr.communityserver.Handler.WebsocketHandler;
import kr.communityserver.service.ChatService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.InputStreamResource;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.socket.WebSocketSession;

import java.nio.file.Path;
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
    public ResponseEntity chattingRoom(@RequestParam(name = "userName")String userId ,@RequestParam(name="room") int r) {
        return chatService.findChatRoom(userId ,  r);
    }

    //채팅방 알람
    @ResponseBody
    @GetMapping("/chatAlarm")
    public ResponseEntity chattingAram(@RequestParam(name = "userName")String userId, @RequestParam(name = "r") int r) {
        return chatService.totalChatAram(userId, r);
    }
    //채팅방 찾는
    @ResponseBody
    @GetMapping ("/myRoom")
    public ResponseEntity chattingRoom(@RequestParam(name = "room")int room,
                                       @RequestParam(name = "userId")String userId) {
        return chatService.findChatRoom(room, userId);
    }

    //읽은 채팅 확인
    @ResponseBody
    @GetMapping ("/beforeChatRead")
    public ResponseEntity beforeChatRead(@RequestParam(name = "room")int room,
                                       @RequestParam(name = "userId")String userId) {
        return chatService.chatCheck(room, userId);
    }
    
    //기존 채팅 불러오기
    @ResponseBody
    @GetMapping ("/beforeChat")
    public ResponseEntity beforeChat(@RequestParam(name = "room")int room,  @RequestParam(name = "userId")String userId) {
        return chatService.searchBefore(room, userId);
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

    //DM 멤버 조회
    @ResponseBody
    @GetMapping("/searchDm")
    public  ResponseEntity searchDm(@RequestParam(name = "word")String word){
        return  chatService.searchDmMembers(word);
    }

    //DM 방 만들기
    @ResponseBody
    @GetMapping("/makeDm")
    public  ResponseEntity makeDm(@RequestParam(name = "email")String email,
                                  @RequestParam(name = "user")String userId){
        return  chatService.makeDmRooms(email, userId);
    }

    //채팅방 나가기
    @ResponseBody
    @GetMapping("/outChatRoom")
    public  ResponseEntity outChatRoom(@RequestParam(name = "userId")String userId,
                                  @RequestParam(name = "room")int room){
        return  chatService.outChat(userId, room);
    }


    //파일 업로드
    @ResponseBody
    @PostMapping("/chat/fileUpload")
    public ResponseEntity uploadFile(
            @RequestParam("file") MultipartFile file,
            @RequestParam("userName") String userName,
            @RequestParam("time") String time,
            @RequestParam("chatRoomPk") int chatRoomPk,
            @RequestParam("message") String message ) {

        return chatService.uploadImage(file ,chatRoomPk , message, userName , time);
    }



    @GetMapping("/downloadFile")
    public ResponseEntity<InputStreamResource> downloadFile(@RequestParam String fileName ) {
      return   chatService.downloadFile(fileName);

    }


}
