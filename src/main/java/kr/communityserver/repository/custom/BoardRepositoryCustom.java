package kr.communityserver.repository.custom;

import com.querydsl.core.Tuple;
import kr.communityserver.dto.PageRequestDTO;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;


public interface BoardRepositoryCustom {

    public Page<Tuple> list(PageRequestDTO pageRequestDTO, Pageable pageable);
    public Page<Tuple> searchArticles(PageRequestDTO pageRequestDTO, Pageable pageable);

}
