package kr.communityserver.repository;

import kr.communityserver.entity.Board;
import kr.communityserver.entity.Calendar;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

@Repository
public interface CalendarRepository extends JpaRepository<Calendar, Integer> {

    public List<Calendar> findByUid(String uid);

    public List<Calendar> findByStartBetweenOrEndBetweenAndUid(LocalDateTime start, LocalDateTime start2, LocalDateTime end, LocalDateTime end2, String uid);


}
