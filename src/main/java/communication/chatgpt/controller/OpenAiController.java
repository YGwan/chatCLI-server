package communication.chatgpt.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import communication.chatgpt.service.KeywordService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import java.io.IOException;
import java.util.List;

@RequiredArgsConstructor
@RestController
@RequestMapping("/v1")
public class OpenAiController {

    private final OpenAiResponseEntity openAiResponseEntity;
    private final OpenAiRequestEntity openAiRequestEntity;
    private final KeywordService keywordService;

    private String chatRequest = "";

    @PostMapping("/chat/completions")
    public ResponseEntity<String> chat(@RequestBody String request) throws JsonProcessingException {
        keywordService.parse(request);
        chatRequest = chatRequest + request;
        HttpEntity<String> openAiRequest = openAiRequestEntity.chatParsed(chatRequest);
        ResponseEntity<String> openAiResponseEntity = this.openAiResponseEntity.chatParsed(openAiRequest);
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
        return openAiResponseEntity.completionsParsed(openAiRequest);
    }

    @PostMapping("/mood")
    public ResponseEntity<String> tweetClassifier(@RequestBody String request) throws JsonProcessingException {
        HttpEntity<String> openAiRequest = openAiRequestEntity.tweetClassifierParsed(request);
        return openAiResponseEntity.completionsParsed(openAiRequest);
    }

    @PostMapping("/trans")
    public ResponseEntity<String> translate(@RequestBody String request) throws JsonProcessingException {
        HttpEntity<String> openAiRequest = openAiRequestEntity.translateParsed(request);
        return openAiResponseEntity.completionsParsed(openAiRequest);
    }

    @PostMapping("/summarize")
    public HttpEntity<String> summarize(@RequestBody String request) throws JsonProcessingException {
        HttpEntity<String> openAiRequest = openAiRequestEntity.summarizeParsed(request);
        return openAiResponseEntity.completionsParsed(openAiRequest);
    }

    @PostMapping("/audio/transcriptions")
    public HttpEntity<String> transcriptions(@RequestPart("file") MultipartFile file) throws IOException {
        HttpEntity<MultiValueMap<String, Object>> multiValueMapHttpEntity = openAiRequestEntity.transcriptionParsed(file);
        return openAiResponseEntity.transcriptionParsed(multiValueMapHttpEntity);
    }
    @GetMapping("/keywords")
    public String keywords() {
        List<String> data = keywordService.popularKeywords();
        String result = String.join("\n",data);
        return result;
    }
}
