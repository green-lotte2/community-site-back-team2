package kr.communityserver.repository;

import kr.communityserver.entity.Chat;
import kr.communityserver.entity.ChatUser;
import org.apache.ibatis.annotations.Param;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Date;
import java.util.List;

@Repository
public interface ChatRepository extends JpaRepository<Chat, Integer> {
    public List<Chat> findAllByChatRoom(int room);
    public Chat findBysName(String sName);


    @Query("SELECT u FROM Chat u WHERE u.chatRoom = :room AND u.localDateTime BETWEEN :startDate AND :endDate")
    public List<Chat> findAllByChatRoomAndLocalDateTimeBetween(@Param("room") int room, @Param("startDate") LocalDateTime startDate, @Param("endDate") LocalDateTime endDate);
}
