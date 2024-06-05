package kr.communityserver.repository;

import kr.communityserver.entity.Board;
import kr.communityserver.entity.Report;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface ReportRepository extends JpaRepository<Report, Integer> {

}
