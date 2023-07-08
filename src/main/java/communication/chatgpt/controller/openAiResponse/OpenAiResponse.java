package communication.chatgpt.controller.openAiResponse;

import com.fasterxml.jackson.core.JsonProcessingException;
import org.springframework.http.HttpEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.util.MultiValueMap;

public interface OpenAiResponse {

    ResponseEntity<String> chatParsed(HttpEntity<String> openAiRequest) throws JsonProcessingException;

    ResponseEntity<String> completionsParsed(HttpEntity<String> openAiRequest) throws JsonProcessingException;

    ResponseEntity<String> transcriptionParsed(HttpEntity<MultiValueMap<String, Object>> openAiRequest) throws JsonProcessingException;
}
