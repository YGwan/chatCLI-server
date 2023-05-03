package communication.chatgpt.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import communication.chatgpt.data.Edits;
import communication.chatgpt.data.Completions;
import communication.chatgpt.dto.UserResponse;
import communication.chatgpt.dto.edits.response.OpenAiEditResponseDto;
import communication.chatgpt.dto.completions.response.CompletionsResponseDto;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

@RequiredArgsConstructor
@Component
public class OpenAiResponseEntity {

    private final ObjectMapper objectMapper;
    private final RestTemplate rt;

    public ResponseEntity<String> editsParsed(HttpEntity<String> openAiRequest) throws JsonProcessingException {
        ResponseEntity<String> response = rt.exchange(Edits.ENDPOINT.data(), HttpMethod.POST, openAiRequest, String.class);
        OpenAiEditResponseDto openAiEditResponseDto = objectMapper.readValue(response.getBody(), OpenAiEditResponseDto.class);
        String openAiMessage = openAiEditResponseDto.getChoices().get(0).getText().trim();
        return getStringResponseEntity(openAiMessage);
    }

    public ResponseEntity<String> tweetClassifierParsed(HttpEntity<String> openAiRequest) throws JsonProcessingException {
        ResponseEntity<String> response = rt.exchange(Completions.ENDPOINT.data(), HttpMethod.POST, openAiRequest, String.class);
        CompletionsResponseDto completionsResponseDto = objectMapper.readValue(response.getBody(), CompletionsResponseDto.class);
        String openAiMessage = completionsResponseDto.getChoices().get(0).getText().trim();
        return getStringResponseEntity(openAiMessage);
    }

    private static ResponseEntity<String> getStringResponseEntity(String openAiMessage) {
        UserResponse userResponse = UserResponse.of(openAiMessage);
        return ResponseEntity.ok(userResponse.answer());
    }
}
