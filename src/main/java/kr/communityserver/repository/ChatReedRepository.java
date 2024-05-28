package kr.communityserver.repository;

import kr.communityserver.entity.ChatRead;
import kr.communityserver.entity.ChatRoom;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ChatReedRepository extends JpaRepository<ChatRead, Integer> {
    public List<ChatRead> findAllByChatRoomAndUserIdAndStatus(int room, String userId, int status);

    public List<ChatRead> findAllByUserIdAndStatus(String userId, int status);

    public List<ChatRead> findAllByUserIdAndStatusAndChatRoom(String userId, int status, int room);

    public ChatRead findByMessageAndUserId(int pk, String userId);
}
