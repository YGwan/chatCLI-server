package communication.chatgpt.controller.openAiResponse;

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
import org.springframework.web.reactive.function.BodyInserters;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

import java.util.Objects;

@RequiredArgsConstructor
@Component
public class ResponseEntityByWebClient implements OpenAiResponse {

    private final ObjectMapper objectMapper;
    private final WebClient webClient;
    private final WebClient formWebClient;

    @Override
    public ResponseEntity<String> chatParsed(HttpEntity<String> openAiRequest) throws JsonProcessingException {
        String openAiResponseBody = getOpenAiStringResponseBody(openAiRequest.getBody(), Chat.CHAT_ENDPOINT.data());

        OpenAiChatResponseDto openAiChatResponseDto = objectMapper.readValue(openAiResponseBody, OpenAiChatResponseDto.class);
        String openAiMessage = openAiChatResponseDto.getChoices().get(0).getMessage().getContent().trim();

        return getUserResponseEntity(openAiMessage);
    }

    @Override
    public ResponseEntity<String> completionsParsed(HttpEntity<String> openAiRequest) throws JsonProcessingException {
        String openAiResponseBody = getOpenAiStringResponseBody(openAiRequest.getBody(), Completions.ENDPOINT.data());

        CompletionsResponseDto completionsResponseDto = objectMapper.readValue(openAiResponseBody, CompletionsResponseDto.class);
        String openAiMessage = completionsResponseDto.getChoices().get(0).getText().trim();

        return getUserResponseEntity(openAiMessage);
    }

    private String getOpenAiStringResponseBody(String openAiRequestBody, String url) {

        Mono<ResponseEntity<String>> responseMono = webClient
                .method(HttpMethod.POST)
                .uri(url)
                .body(BodyInserters.fromValue(openAiRequestBody))
                .retrieve()
                .toEntity(String.class);

        ResponseEntity<String> response = responseMono.block();
        return Objects.requireNonNull(response).getBody();
    }

    @Override
    public ResponseEntity<String> transcriptionParsed(HttpEntity<MultiValueMap<String, Object>> openAiRequest) throws JsonProcessingException {
        Mono<ResponseEntity<String>> responseMono = formWebClient
                .method(HttpMethod.POST)
                .uri(Transcription.ENDPOINT.data())
                .body(BodyInserters.fromValue(Objects.requireNonNull(openAiRequest.getBody())))
                .retrieve()
                .toEntity(String.class);

        ResponseEntity<String> response = responseMono.block();
        String openAiResponseBody = Objects.requireNonNull(response).getBody();

        TranscriptionResponseDto transcriptionResponseDto = objectMapper.readValue(openAiResponseBody, TranscriptionResponseDto.class);
        String openAiMessage = transcriptionResponseDto.getText();

        return getUserResponseEntity(openAiMessage);
    }

    private ResponseEntity<String> getUserResponseEntity(String openAiMessage) {
        UserResponse userResponse = UserResponse.of(openAiMessage);
        return ResponseEntity.ok(userResponse.answer());
    }
}
