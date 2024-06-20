package kr.communityserver.repository;

import kr.communityserver.entity.Chat;
import kr.communityserver.entity.ChatRoom;
import kr.communityserver.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ChatRoomRepository extends JpaRepository<ChatRoom, Integer> {
    public ChatRoom findByRoomName(String name);

    public List<ChatRoom> findAllByUserId(String userId);
}
