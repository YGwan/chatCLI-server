package communication.chatgpt.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import communication.chatgpt.data.Edits;
import communication.chatgpt.data.TweetClassifier;
import communication.chatgpt.dto.edits.request.EditsParsedRequestDto;
import communication.chatgpt.dto.edits.request.EditsRequestDto;
import communication.chatgpt.dto.tweetClassifier.request.TweetClassifierParsedRequestDto;
import communication.chatgpt.dto.tweetClassifier.request.TweetClassifierRequestDto;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.stereotype.Component;

@RequiredArgsConstructor
@Component
public class OpenAiRequestEntity {

    private final ObjectMapper objectMapper;
    private final HttpHeaders headers;

    public HttpEntity<String> editsParsed(EditsRequestDto editRequest) throws JsonProcessingException {
        String editsOpenAiBody = objectMapper.
                writeValueAsString(
                        new EditsParsedRequestDto(
                                Edits.MODEL.data(),
                                editRequest.getInput(),
                                Edits.INSTRUCTION.data()
                        ));
        return new HttpEntity<>(editsOpenAiBody, headers);
    }

    public HttpEntity<String> tweetClassifierParsed(TweetClassifierRequestDto request) throws JsonProcessingException {
        String tweetClassifierOpenAiBody = objectMapper.
                writeValueAsString(
                        new TweetClassifierParsedRequestDto(
                                TweetClassifier.MODEL.data(),
                                TweetClassifier.MESSAGE.data() + request.getPrompt()
                                )
                );
        return new HttpEntity<>(tweetClassifierOpenAiBody, headers);
    }
}
