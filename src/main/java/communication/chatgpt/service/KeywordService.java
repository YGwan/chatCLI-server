package communication.chatgpt.service;

import communication.chatgpt.entity.Keyword;
import communication.chatgpt.repository.KeywordRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class KeywordService {

    private final KeywordRepository keywordRepository;

    @Transactional
    public void parse(String keywordsParsed) {
        String[] keywordList = keywordsParsed.replaceAll("Keywords: ", "").split(",");

        List<String> keywords = new ArrayList<>();
        for (String keyword : keywordList) {
            keywords.add(keyword.trim());
        }

        List<String> storedKeywords = keywordRepository.findAllKeyword();

        for (String keyword : keywords) {
            updateKeywordAndCount(storedKeywords, keyword);
        }
    }

    private void updateKeywordAndCount(List<String> storedKeywords, String keyword) {
        if (storedKeywords.contains(keyword)) {
            Keyword alreadyStoredKeyword = keywordRepository.findByKeyword(keyword);
            alreadyStoredKeyword.setCount(alreadyStoredKeyword.getCount() + 1);
            keywordRepository.save(alreadyStoredKeyword);
        } else {
            Keyword newKeyword = Keyword.builder()
                    .keyword(keyword)
                    .count(1)
                    .build();
            keywordRepository.save(newKeyword);
        }
    }

    public List<String> popularKeywords() {
        return keywordRepository.findTop5ByCountDesc(PageRequest.of(0, 5));
    }
}
