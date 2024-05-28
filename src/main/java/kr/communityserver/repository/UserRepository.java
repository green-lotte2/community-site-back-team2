package kr.communityserver.repository;

import kr.communityserver.entity.User;
import org.apache.ibatis.annotations.Param;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UserRepository extends JpaRepository<User, String> {
    public User findByEmail(String email);
  
    @Query("SELECT u FROM User u WHERE u.email LIKE concat(:word,'%') ")
    public List<User> findAllByEmailContaining(@Param("word") String word);

    Boolean existsByEmail(String email);

}
