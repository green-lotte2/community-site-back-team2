package kr.communityserver.repository;

import kr.communityserver.entity.Chat;
import kr.communityserver.entity.ChatUser;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ChatRepository extends JpaRepository<Chat, Integer> {
    public List<Chat> findAllByChatRoom(int room);
}
