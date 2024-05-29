package kr.communityserver.repository;

import kr.communityserver.entity.Terms;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TermsRepository extends JpaRepository<Terms, Integer> {
    Terms findByTerms(String terms);
    Terms findByPrivacy(String privacy);
    Terms findBySms(String sms);
}
