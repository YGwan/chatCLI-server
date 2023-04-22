package communication.chatgpt.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import communication.chatgpt.dto.*;
import communication.chatgpt.dto.createchat.reqeust.AskRequestDto;
import communication.chatgpt.dto.createchat.response.AskResponseDto;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.*;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

@RestController
@RequestMapping("/v1")
public class ChatController {

    @Value("${open-ai.chat.model}")
    private String model;

    @Value("${open-ai.token}")
    private String token;

    @Value("${open-ai.apiUrl}")
    private String apiUrl;

    @PostMapping("/chat/completions")
    public String ask(@RequestBody AskRequestDto askRequest) throws JsonProcessingException {

        askRequest.setModel(model);

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        headers.setBearerAuth(token);

        ObjectMapper objectMapperWrite = new ObjectMapper();
        String body = objectMapperWrite.writeValueAsString(askRequest);

        HttpEntity<String> request = new HttpEntity<>(body,headers);
        RestTemplate rt = new RestTemplate();

        ResponseEntity<String> response = rt.exchange(apiUrl,HttpMethod.POST,request,String.class);
        String json = response.getBody();

        ObjectMapper objectMapperRead = new ObjectMapper();
        AskResponseDto askResponseDto = objectMapperRead.readValue(json, AskResponseDto.class);
        String content = askResponseDto.getChoices().get(0).getMessage().getContent();
        return content;
    }

    @PostMapping("/grammar")
    public String grammarCheck(@RequestBody GrammarCheckRequest request) {
        return "grammarCheck";
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
