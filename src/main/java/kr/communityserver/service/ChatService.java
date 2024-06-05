package kr.communityserver.service;

import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.annotation.Resource;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import kr.communityserver.dto.UserDTO;
import kr.communityserver.entity.*;
import kr.communityserver.repository.*;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.InputStreamResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.FileInputStream;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.*;

@Service
@Slf4j
@RequiredArgsConstructor
public class ChatService  {
    private final ChatUserRepository chatUserRepository;
    private  final ChatRoomRepository chatRoomRepository;

    @Autowired
    private  final ChatRepository chatRepository;
    private  final UserRepository userRepository;
    private  final ChatReedRepository chatReedRepository;

    public ResponseEntity findChatRoom(String userId, int room){
        User user = userRepository.findById(userId).get();
        List<ChatUser> users = chatUserRepository.findAllByUserId(userId);
        List<ChatRoom> rooms = new ArrayList<>();
        for(ChatUser chatUser : users){
            ChatRoom findRoom =chatRoomRepository.findById(chatUser.getChatRoom()).get();

            if(findRoom.getStatus() != 0){
                String roomName = findRoom.getRoomName();
               roomName=  roomName.replace(user.getName(), "");
                log.info(roomName +"roomName!");
                if(roomName == null || roomName == ""){
                    roomName = user.getName();
                }
                findRoom.setRoomName(roomName);
            }
            if(findRoom.getChatRoomPk() == room){
                findRoom.setNewChat(0);
            }else{
                findRoom.setNewChat(chatReedRepository.findAllByChatRoomAndUserIdAndStatus(findRoom.getChatRoomPk(), userId, 0).size());
            }
            rooms.add(findRoom);
        }
        Map<String, List<ChatRoom>> map = new HashMap<>();
        map.put("result", rooms);
        return  ResponseEntity.ok().body(map);
    }

    public ResponseEntity findChatRoom(int room, String userId){
        ChatRoom rooms = chatRoomRepository.findById(room).get();
        User user = userRepository.findById(userId).get();
        if(rooms.getStatus() != 0){
            String roomName = rooms.getRoomName();
            roomName=  roomName.replace(user.getName(), "");
            log.info(roomName +"roomName!");
            if(roomName == null || roomName == ""){
                roomName = user.getName();
            }
            rooms.setRoomName(roomName);
        }
        Map<String, ChatRoom> map = new HashMap<>();
        map.put("result", rooms);
        return  ResponseEntity.ok().body(map);
    }

    public ResponseEntity totalChatAram(String userId, int room){
        int size = chatReedRepository.findAllByUserIdAndStatus(userId, 0).size();
        int minus = chatReedRepository.findAllByUserIdAndStatusAndChatRoom(userId, 0, room).size();
        Map<String, Integer> map = new HashMap<>();
        map.put("result", size- minus);
        return  ResponseEntity.ok().body(map);
    }

    //읽은 채팅 확인
    public ResponseEntity chatCheck(int room, String userId){
        List<ChatRead> reads = chatReedRepository.findAllByChatRoomAndUserIdAndStatus(room, userId, 0);
        log.info(reads.toString()+"이거 확인!");
        for (ChatRead read : reads){

            read.setStatus(1);
            chatReedRepository.save(read);
        }
        Map<String, Integer> map = new HashMap<>();
        map.put("result", 1);
        return  ResponseEntity.ok().body(map);
    }

    public ResponseEntity searchBefore(int room, String userId) {
        // 일주일 전까지만 조회합니다
        LocalDate currentDate = LocalDate.now();

        List<List<Chat>> lists = new ArrayList<>();
        LocalDate date2 = currentDate.minusDays(7);
        for (int i = 0; i <= 7; i++) {
            LocalDate date = date2.plusDays(i); // 각 루프 반복마다 새로운 날짜를 계산
            LocalDateTime startOfDay = date.atStartOfDay(); // 날짜의 시작 시각
            LocalDateTime endOfDay = date.atTime(LocalTime.MAX); // 날짜의 끝 시각
            List<Chat> chats = chatRepository.findAllByChatRoomAndLocalDateTimeBetween(room, startOfDay, endOfDay);
            log.info(startOfDay + "이거 확인해라~" + endOfDay);
            if (!chats.isEmpty()) { // 비어있지 않은 경우에만 처리
                List<Chat> chatList = new ArrayList<>();
                for (Chat chat : chats) {
                    if (chatReedRepository.findByMessageAndUserId(chat.getChatPk(), userId) != null) {
                        chat.setStatus(chatReedRepository.findByMessageAndUserId(chat.getChatPk(), userId).getStatus());
                    } else {
                        chat.setStatus(1);
                    }
                    chatList.add(chat);
                }
                lists.add(chatList);

            }
        }

        Map<String,  List<List<Chat>>> map = new HashMap<>();
        map.put("result", lists);
        return ResponseEntity.ok().body(map);
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
        }else if(chatUserRepository.findByChatRoomAndUserId(room,user.getUid()) != null){
            map.put("result", -1);

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
       Chat chat1 = chatRepository.save(chat);
        //읽지않는 것도 넣어주자.
        List<ChatUser> chatUsers = chatUserRepository.findAllByChatRoom(roomNumber);
        for(ChatUser user : chatUsers){
            if(user.getUserId().equals( userId)){

            }else{
                ChatRead chatRead = new ChatRead();
                chatRead.setChatRoom(roomNumber);
                chatRead.setStatus(0);
                chatRead.setMessage(chat1.getChatPk());
                chatRead.setUserId(user.getUserId());
                chatReedRepository.save(chatRead);
            }
        }
    }

    //Dm 조회
    public ResponseEntity searchDmMembers(String word){
        List<User> users = new ArrayList<>();
        if(word != null && word != ""){
            users= userRepository.findAllByEmailContaining(word);
        }

        Map<String, List<User>> map = new HashMap<>();
        map.put("result", users);
        return ResponseEntity.ok().body(map);
    }

    //DM 방 만들기
    public ResponseEntity makeDmRooms(String email, String userId){
        Map<String, Integer> map = new HashMap<>();

        User user = userRepository.findByEmail(email);
        User me = userRepository.findById(userId).get();
        if(user == null){
            map.put("result", 0);
        }else if(chatRoomRepository.findByRoomName(user.getName()+me.getName()) != null) {
            map.put("result",chatRoomRepository.findByRoomName(user.getName()+me.getName()).getChatRoomPk());
        }else{
            ChatRoom chatRoom = new ChatRoom();
            chatRoom.setRoomName(user.getName()+me.getName());
            chatRoom.setStatus(1);
            ChatRoom makeRoom = chatRoomRepository.save(chatRoom);

            ChatUser user1 = new ChatUser();
            user1.setChatRoom(makeRoom.getChatRoomPk());
            user1.setUserId(userId);

            ChatUser user2 = new ChatUser();
            user2.setChatRoom(makeRoom.getChatRoomPk());
            user2.setUserId(user.getUid());
            chatUserRepository.save(user1);
            chatUserRepository.save(user2);

            map.put("result", makeRoom.getChatRoomPk());
        }

        return ResponseEntity.ok().body(map);
    }

    //채팅방 나가기
    public ResponseEntity outChat(String userId, int room){
        ChatUser user = chatUserRepository.findByChatRoomAndUserId(room, userId);
        chatUserRepository.delete(user);
        log.info(chatUserRepository.findAllByChatRoom(room) +"????뭔데");
        if(chatUserRepository.findAllByChatRoom(room).size() == 0){
            log.info("이거 되나?");
            List<Chat> chats = chatRepository.findAllByChatRoom(room);
            chatRepository.deleteAll(chats);
            chatRoomRepository.deleteById(room);
        }
        Map<String, Integer> map = new HashMap<>();
        map.put("result", 0);
        return  ResponseEntity.ok().body(map);
    }


    @Value("${file.upload.path}")
    private  String fileUploadPath;
    //이미지 저장하기
    public ResponseEntity uploadImage(MultipartFile file , int chatRoom, String message , String uid, String time){
        String path = new java.io.File(fileUploadPath).getAbsolutePath();
        Chat chat1 = null;
        if(!file.isEmpty()){
            try{
                // 원본파일
                String originalFilename = file.getOriginalFilename();
                // 확장자 파일
                String extension = originalFilename.substring(originalFilename.lastIndexOf("."));
                log.info("originalFilename : " + originalFilename);

                // 저장될 파일 이름
                String sName = UUID.randomUUID().toString() + extension;
                log.info("sName : " + sName);

                // 파일이 저장될 경로
                file.transferTo(new File(path, sName));
                SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm");
                    Date date2 = formatter.parse(time);

                Chat chat = Chat.builder()
                        .oName(originalFilename)
                        .sName(sName)
                        .chatRoom(chatRoom)
                        .localDateTime(date2)
                        .file(1)
                        .userId(uid)
                        .message(message)
                        .build();
                chat1 = chatRepository.save(chat);

                //읽지않는 것도 넣어주자.
                List<ChatUser> chatUsers = chatUserRepository.findAllByChatRoom(chatRoom);
                for(ChatUser user : chatUsers){
                    if(user.getUserId().equals( uid)){

                    }else{
                        ChatRead chatRead = new ChatRead();
                        chatRead.setChatRoom(chatRoom);
                        chatRead.setStatus(0);
                        chatRead.setMessage(chat1.getChatPk());
                        chatRead.setUserId(user.getUserId());
                        chatReedRepository.save(chatRead);
                    }
                }

            }catch (Exception e){
                e.printStackTrace();
            }
        }
        return ResponseEntity.ok().body(chat1.getChatPk());
    }

    //파일 저장하기
    //파일다운로드
    public ResponseEntity downloadFile (String file) {
        String path = new java.io.File(fileUploadPath).getAbsolutePath();
        Chat chat = chatRepository.findBysName(file);
        try {

            File file1 = new File(path + "/" + file);
            InputStreamResource resource = new InputStreamResource(
                    new FileInputStream(file1));

            return  ResponseEntity.ok().header(HttpHeaders.CONTENT_DISPOSITION,
                    "attachment;filename="+chat.getOName())
                    .contentType(MediaType.APPLICATION_OCTET_STREAM)
                    .body(resource);
        } catch (Exception e) {
            log.info(e.getMessage());
        }
return null;
    }


}
