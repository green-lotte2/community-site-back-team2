package kr.communityserver.repository;

import kr.communityserver.entity.PageUser;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PageUserRepository extends JpaRepository<PageUser, Integer> {

    public List<PageUser> findByUid(String uid);

}
