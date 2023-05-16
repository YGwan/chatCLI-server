package communication.chatgpt.repository;

import communication.chatgpt.entity.Keyword;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface KeywordRepository extends JpaRepository<Keyword,Long> {

    @Query("select k.keyword from Keyword k ORDER BY k.count desc")
    List<String> findTop5ByCountDesc(Pageable pageable);

    @Query("select k.keyword from Keyword k")
    List<String> findAllKeyword();

    Keyword findByKeyword(String word);


}
