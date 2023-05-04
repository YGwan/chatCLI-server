package communication.chatgpt.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import communication.chatgpt.data.Chat;
import communication.chatgpt.data.Completions;
import communication.chatgpt.dto.UserResponse;
import communication.chatgpt.dto.chat.response.OpenAiChatResponseDto;
import communication.chatgpt.dto.completions.response.CompletionsResponseDto;
import communication.chatgpt.dto.summary.response.OpenAiSummaryResponseDto;
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

    public ResponseEntity<String> chatParsed(HttpEntity<String> openAiRequest) throws JsonProcessingException {
        ResponseEntity<String> response = rt.exchange(Chat.CHAT_ENDPOINT.data(), HttpMethod.POST, openAiRequest, String.class);
        OpenAiChatResponseDto openAiChatResponseDto = objectMapper.readValue(response.getBody(), OpenAiChatResponseDto.class);
        String openAiMessage = openAiChatResponseDto.getChoices().get(0).getMessage().getContent().trim();
        UserResponse userResponse = UserResponse.of(openAiMessage);
        return ResponseEntity.ok(userResponse.answer());
    }
    public ResponseEntity<String> completionsParsed(HttpEntity<String> openAiRequest) throws JsonProcessingException {
        System.out.println(openAiRequest);
        ResponseEntity<String> response = rt.exchange(Completions.ENDPOINT.data(), HttpMethod.POST, openAiRequest, String.class);
        CompletionsResponseDto completionsResponseDto = objectMapper.readValue(response.getBody(), CompletionsResponseDto.class);
        String openAiMessage = completionsResponseDto.getChoices().get(0).getText().trim();
        return getStringResponseEntity(openAiMessage);
    }

    public ResponseEntity<String> summarizeParsed(HttpEntity<String> openAiRequest) throws JsonProcessingException  {
        ResponseEntity<String> response = rt.exchange(Completions.ENDPOINT.data(), HttpMethod.POST, openAiRequest, String.class);
        OpenAiSummaryResponseDto openAiSummarizeResponseDto = objectMapper.readValue(response.getBody(), OpenAiSummaryResponseDto.class);
        String openAiMessage = openAiSummarizeResponseDto.getChoices().get(0).getText().trim();
        return getStringResponseEntity(openAiMessage);
    }

    private static ResponseEntity<String> getStringResponseEntity(String openAiMessage) {
        UserResponse userResponse = UserResponse.of(openAiMessage);
        return ResponseEntity.ok(userResponse.answer());
    }
}
