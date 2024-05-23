package kr.communityserver.service;

import com.fasterxml.jackson.databind.ObjectMapper;
import kr.communityserver.DTO.ChatDTO;
import kr.communityserver.entity.Chat;
import kr.communityserver.entity.ChatRoom;
import kr.communityserver.entity.ChatUser;
import kr.communityserver.repository.ChatRepository;
import kr.communityserver.repository.ChatRoomRepository;
import kr.communityserver.repository.ChatUserRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.socket.TextMessage;
import org.springframework.web.socket.WebSocketSession;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.*;

@Service
@Slf4j
@RequiredArgsConstructor
public class ChatService {
    private final ChatUserRepository chatUserRepository;
    private  final ChatRoomRepository chatRoomRepository;
    private  final ChatRepository chatRepository;
    public ResponseEntity findChatRoom(String userId){
        List<ChatUser> users = chatUserRepository.findAllByUserId(userId);
        List<ChatRoom> rooms = new ArrayList<>();
        for(ChatUser chatUser : users){
            rooms.add(chatRoomRepository.findById(chatUser.getChatRoom()).get());
        }
        Map<String, List<ChatRoom>> map = new HashMap<>();
        map.put("result", rooms);
        return  ResponseEntity.ok().body(map);
    }

    public ResponseEntity findChatRoom(int room){
        ChatRoom rooms = chatRoomRepository.findById(room).get();
        Map<String, ChatRoom> map = new HashMap<>();
        map.put("result", rooms);
        return  ResponseEntity.ok().body(map);
    }

    public ResponseEntity searchBefore(int room){
        List<Chat> chats = chatRepository.findAllByChatRoom(room);
        Map<String, List<Chat>> map = new HashMap<>();
        map.put("result", chats);
        return  ResponseEntity.ok().body(map);
    }

    public void saveChat(String msg){
    String [] parts = msg.split("\\*");
    for(String ex : parts){
        log.info(ex);
    }

    String userId = parts[0];
    String date = parts[1];
    int roomNumber = Integer.parseInt(parts[2]);
    String message = parts[3];
        Chat chat = new Chat();

        chat.setChatRoom(roomNumber);
        chat.setMessage(message);
        chat.setUserId(userId);

        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm");
        try{
            Date date2 = formatter.parse(date);
            chat.setLocalDateTime(date2);
        }catch (Exception e){

        }
        chatRepository.save(chat);
    }


}
