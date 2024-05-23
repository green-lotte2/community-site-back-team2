package kr.communityserver.repository;

import kr.communityserver.entity.ChatRoom;
import kr.communityserver.entity.ChatUser;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ChatUserRepository extends JpaRepository<ChatUser, Integer> {
    public List<ChatUser> findAllByUserId(String userId);
}
