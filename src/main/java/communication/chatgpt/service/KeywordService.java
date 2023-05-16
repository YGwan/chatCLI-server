package communication.chatgpt.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import communication.chatgpt.controller.OpenAiRequestEntity;
import communication.chatgpt.controller.OpenAiResponseEntity;
import communication.chatgpt.entity.Keyword;
import communication.chatgpt.repository.KeywordRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.HttpEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class KeywordService {

    private final KeywordRepository keywordRepository;
    private final OpenAiResponseEntity openAiResponseEntity;
    private final OpenAiRequestEntity openAiRequestEntity;
    @Transactional
    public List<String> parse(String request) throws JsonProcessingException {
        HttpEntity<String> openAiRequest = openAiRequestEntity.keywordsParsed(request);
        String keywordsParsed = openAiResponseEntity.keywordsParsed(openAiRequest);
        String[] lines = keywordsParsed.split("\n");
        List<String> list = new ArrayList<>();

        for (String line : lines) {
            String[] items = line.split(",");
            for (String item : items) {
                String s = item.replaceAll("-", "");
                String cleanItem = s.trim();
                list.add(cleanItem);
            }
        }

        List<String> all = keywordRepository.findAllKeyword();
        for(String value : list){
            if(all.contains(value)){
                Keyword checkWord = keywordRepository.findByKeyword(value);
                checkWord.setCount(checkWord.getCount()+1);
            }
            else{
                Keyword build = Keyword.builder()
                        .keyword(value)
                        .count(1)
                        .build();
                keywordRepository.save(build);
            }
        }

        List<String> limitedResults = keywordRepository.findTop5ByCountDesc(PageRequest.of(0, 5));
        return limitedResults;
    }
}
