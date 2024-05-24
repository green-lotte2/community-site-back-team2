package kr.communityserver.service;

import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import kr.communityserver.entity.Chat;
import kr.communityserver.entity.ChatRoom;
import kr.communityserver.entity.ChatUser;
import kr.communityserver.entity.User;
import kr.communityserver.repository.ChatRepository;
import kr.communityserver.repository.ChatRoomRepository;
import kr.communityserver.repository.ChatUserRepository;
import kr.communityserver.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.hibernate.annotations.CreationTimestamp;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.socket.TextMessage;
import org.springframework.web.socket.WebSocketSession;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.util.*;

@Service
@Slf4j
@RequiredArgsConstructor
public class ChatService {
    private final ChatUserRepository chatUserRepository;
    private  final ChatRoomRepository chatRoomRepository;
    private  final ChatRepository chatRepository;
    private  final UserRepository userRepository;

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




    //채팅방 만들기
    public ResponseEntity makeChat(String userId, String chatName){
        ChatRoom chatRoom = new ChatRoom();
        chatRoom.setRoomName(chatName);
        chatRoom.setStatus(0);
        ChatRoom makeRoom = chatRoomRepository.save(chatRoom);

        ChatUser user = new ChatUser();
        user.setChatRoom(makeRoom.getChatRoomPk());
        user.setUserId(userId);

        chatUserRepository.save(user);

        Map<String, Integer> map = new HashMap<>();
        map.put("result", makeRoom.getChatRoomPk());
        return  ResponseEntity.ok().body(map);

    }

    //초대하기
    public ResponseEntity inviteUser(String userEmail, int room){
        User user = userRepository.findByEmail(userEmail);
        Map<String, Integer> map = new HashMap<>();
        if(user == null){
            map.put("result", 0);
        }else{
            ChatUser chatUser = new ChatUser();
            chatUser.setUserId(user.getUid());
            chatUser.setChatRoom(room);
            chatUserRepository.save(chatUser);

            map.put("result", 1);
        }
        return  ResponseEntity.ok().body(map);

    }

    //멤버조회
    public ResponseEntity searchMembers(int room){
        List<User> users = new ArrayList<>();

        List<ChatUser> chatUsers = chatUserRepository.findAllByChatRoom(room);

        for(ChatUser user : chatUsers){
            users.add(userRepository.findById(user.getUserId()).get());
        }

        Map<String, List<User>> map = new HashMap<>();
        map.put("result", users);

        return  ResponseEntity.ok().body(map);

    }

    public void saveChat(String msg){
        log.info("??뭐임?");
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
