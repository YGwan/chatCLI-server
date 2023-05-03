package communication.chatgpt.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import communication.chatgpt.dto.EmotionalAnalysisRequest;
import communication.chatgpt.dto.SummarizeRequest;
import communication.chatgpt.dto.TranslateRequest;
import communication.chatgpt.dto.chat.request.ChatRequestDto;
import communication.chatgpt.dto.edits.request.EditsRequestDto;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequiredArgsConstructor
@RestController
@RequestMapping("/v1")
public class ChatController {

    private final OpenAiResponseEntity openAiResponseEntity;
    private final OpenAiRequestEntity openAiRequestEntity;

    @PostMapping("/chat/completions")
    public ResponseEntity<String> chat(@RequestBody ChatRequestDto request) throws JsonProcessingException {
        HttpEntity<String> openAiRequest = openAiRequestEntity.chatParsed(request);
        return openAiResponseEntity.chatParsed(openAiRequest);
    }

    @PostMapping("/edits")
    public ResponseEntity<String> edits(@RequestBody EditsRequestDto request) throws JsonProcessingException {
        HttpEntity<String> openAiRequest = openAiRequestEntity.editsParsed(request);
        return openAiResponseEntity.editsParsed(openAiRequest);
    }

    @PostMapping("/translator")
    public String translate(@RequestBody TranslateRequest request) {
        return "translate";
    }

    @PostMapping("/mood")
    public String emotionalAnalysis(@RequestBody EmotionalAnalysisRequest request) {
        return "emotionalAnalysis";
    }

    @PostMapping("/summarize")
    public String summarize(@RequestBody SummarizeRequest request) {
        return "summarize";
    }
}
