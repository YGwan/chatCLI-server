package communication.chatgpt.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import communication.chatgpt.controller.openAiResponse.OpenAiResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

@RequiredArgsConstructor
@RestController
@RequestMapping("/v1")
public class OpenAiController {

    private final OpenAiResponse openAiResponse;
    private final OpenAiRequestEntity openAiRequestEntity;

    private String chatRequest = "";

    @GetMapping()
    public ResponseEntity<String> healthCheck() {
        return ResponseEntity.ok("ok");
    }

    @PostMapping("/chat")
    public ResponseEntity<String> chat(@RequestBody String request) throws JsonProcessingException {
        chatRequest = chatRequest + request;
        HttpEntity<String> openAiRequest = openAiRequestEntity.chatParsed(chatRequest);
        ResponseEntity<String> openAiResponseEntity = this.openAiResponse.chatParsed(openAiRequest);
        chatRequest = chatRequest + openAiResponseEntity.getBody();
        return openAiResponseEntity;
    }

    @GetMapping("/chat/reset")
    public ResponseEntity<String> chatQuestionReset() {
        chatRequest = "";
        return ResponseEntity.ok("success");
    }

    @PostMapping("/gc")
    public ResponseEntity<String> grammarCheck(@RequestBody String request) throws JsonProcessingException {
        HttpEntity<String> openAiRequest = openAiRequestEntity.grammarCheckParsed(request);
        return openAiResponse.completionsParsed(openAiRequest);
    }

    @PostMapping("/mood")
    public ResponseEntity<String> tweetClassifier(@RequestBody String request) throws JsonProcessingException {
        HttpEntity<String> openAiRequest = openAiRequestEntity.tweetClassifierParsed(request);
        return openAiResponse.completionsParsed(openAiRequest);
    }

    @PostMapping("/trans")
    public ResponseEntity<String> translate(@RequestBody String request) throws JsonProcessingException {
        HttpEntity<String> openAiRequest = openAiRequestEntity.translateParsed(request);
        return openAiResponse.completionsParsed(openAiRequest);
    }

    @PostMapping("/summarize")
    public HttpEntity<String> summarize(@RequestBody String request) throws JsonProcessingException {
        HttpEntity<String> openAiRequest = openAiRequestEntity.summarizeParsed(request);
        return openAiResponse.completionsParsed(openAiRequest);
    }

    @PostMapping("/audio/transcriptions")
    public HttpEntity<String> transcriptions(@RequestPart("file") MultipartFile file) throws IOException {
        HttpEntity<MultiValueMap<String, Object>> multiValueMapHttpEntity = openAiRequestEntity.transcriptionParsed(file);
        return openAiResponse.transcriptionParsed(multiValueMapHttpEntity);
    }
}
