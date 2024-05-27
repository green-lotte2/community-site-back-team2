package kr.communityserver.repository;

import kr.communityserver.entity.ChatRoom;
import kr.communityserver.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ChatRoomRepository extends JpaRepository<ChatRoom, Integer> {
    public ChatRoom findByRoomName(String name);
}
