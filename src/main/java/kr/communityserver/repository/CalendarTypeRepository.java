package kr.communityserver.repository;

import kr.communityserver.entity.Calendar;
import kr.communityserver.entity.CalendarType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CalendarTypeRepository extends JpaRepository<CalendarType, Integer> {

    public List<CalendarType> findByUid(String uid);

}
