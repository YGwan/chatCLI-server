package communication.chatgpt.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import communication.chatgpt.data.Chat;
import communication.chatgpt.data.Completions;
import communication.chatgpt.data.Transcription;
import communication.chatgpt.dto.UserResponse;
import communication.chatgpt.dto.chat.response.OpenAiChatResponseDto;
import communication.chatgpt.dto.completions.response.CompletionsResponseDto;
import communication.chatgpt.dto.transcription.response.TranscriptionResponseDto;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.util.MultiValueMap;
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
        return getUserResponseEntity(openAiMessage);
    }

    public ResponseEntity<String> completionsParsed(HttpEntity<String> openAiRequest) throws JsonProcessingException {
        ResponseEntity<String> response = rt.exchange(Completions.ENDPOINT.data(), HttpMethod.POST, openAiRequest, String.class);
        CompletionsResponseDto completionsResponseDto = objectMapper.readValue(response.getBody(), CompletionsResponseDto.class);
        String openAiMessage = completionsResponseDto.getChoices().get(0).getText().trim();
        return getUserResponseEntity(openAiMessage);
    }

    public ResponseEntity<String> transcriptionParsed(HttpEntity<MultiValueMap<String, Object>> openAiRequest) throws JsonProcessingException {
        ResponseEntity<String> response = rt.exchange(Transcription.ENDPOINT.data(), HttpMethod.POST, openAiRequest, String.class);
        TranscriptionResponseDto transcriptionResponseDto = objectMapper.readValue(response.getBody(), TranscriptionResponseDto.class);
        String openAiMessage = transcriptionResponseDto.getText();
        return getUserResponseEntity(openAiMessage);
    }

    private ResponseEntity<String> getUserResponseEntity(String openAiMessage) {
        UserResponse userResponse = UserResponse.of(openAiMessage);
        return ResponseEntity.ok(userResponse.answer());
    }
}
