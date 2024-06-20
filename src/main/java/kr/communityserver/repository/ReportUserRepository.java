package kr.communityserver.repository;

import kr.communityserver.entity.Report;
import kr.communityserver.entity.ReportUser;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ReportUserRepository extends JpaRepository<ReportUser, Integer> {
        public List<ReportUser> findAllByBadPerson(String uid);
}
