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
        keywordsParsed = keywordsParsed.replaceAll("Keywords: ", "");
        List<String> list = new ArrayList<>();

        String[] items = keywordsParsed.split(",");
        for (String item : items) {
            String cleanItem = item.trim();
            list.add(cleanItem);
        }

        List<String> all = keywordRepository.findAllKeyword();
        for (String value : list) {
            if (all.contains(value)) {
                Keyword checkWord = keywordRepository.findByKeyword(value);
                checkWord.setCount(checkWord.getCount() + 1);
            } else {
                Keyword build = Keyword.builder()
                        .keyword(value)
                        .count(1)
                        .build();
                keywordRepository.save(build);
            }
        }
    }

    public List<String> popularKeywords() {
        return keywordRepository.findTop5ByCountDesc(PageRequest.of(0, 5));
    }
}
