package communication.chatgpt.controller;

import communication.chatgpt.dto.*;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/chat")
public class ChatController {

    @PostMapping("/ask")
    public String ask(@RequestBody askRequest request) {
        return "ask";
    }

    @PostMapping("/grammar")
    public String grammarCheck(@RequestBody grammarCheckRequest request) {
        return "grammarCheck";
    }

    @PostMapping("/translator")
    public String translate(@RequestBody TranslateRequest request) {
        return "translate";
    }

    @PostMapping("/mood")
    public String emotionalAnalysis(@RequestBody emotionalAnalysisRequest request) {
        return "emotionalAnalysis";
    }

    @PostMapping("/summarize")
    public String summarize(@RequestBody SummarizeRequest request) {
        return "summarize";
    }
}
