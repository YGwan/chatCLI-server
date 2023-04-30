package communication.chatgpt.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import communication.chatgpt.data.Edits;
import communication.chatgpt.dto.UserResponse;
import communication.chatgpt.dto.edits.response.OpenAiEditResponseDto;
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
        ResponseEntity<String> response = rt.exchange(Edits.GRAMMAR_ENDPOINT.data(), HttpMethod.POST, openAiRequest, String.class);
        OpenAiEditResponseDto openAiEditResponseDto = objectMapper.readValue(response.getBody(), OpenAiEditResponseDto.class);
        String openAiMessage = openAiEditResponseDto.getChoices().get(0).getText().trim();
        UserResponse userResponse = UserResponse.of(openAiMessage);
        // 사용자한테 반환하는 꼴은 OpenAiResponse나 ParsingMachine은 전혀 관계 없이 의존 끊어짐
        // 이제부턴 UserResponse의 문제.
        return ResponseEntity.ok(userResponse.answer());
    }
}
