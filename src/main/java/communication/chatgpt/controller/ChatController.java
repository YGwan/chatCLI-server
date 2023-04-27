package communication.chatgpt.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import communication.chatgpt.data.Edits;
import communication.chatgpt.dto.EmotionalAnalysisRequest;
import communication.chatgpt.dto.SummarizeRequest;
import communication.chatgpt.dto.TranslateRequest;
import communication.chatgpt.dto.edits.request.EditsRequestDto;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

@RestController
@RequestMapping("/v1")
public class ChatController {

    private final HttpHeaders headers;
    private final RestTemplate rt;
    private final ObjectMapper om;
    private final ParsingMachine parsingMachine;

    public ChatController(HttpHeaders headers, RestTemplate rt, ObjectMapper om, ParsingMachine parsingMachine) {
        this.headers = headers;
        this.rt = rt;
        this.om = om;
        this.parsingMachine = parsingMachine;
    }

    //    @PostMapping("/chat/completions")
//    public String ask(@RequestBody AskRequestDto askRequest) throws JsonProcessingException {
//
//        HttpHeaders headers = new HttpHeaders();
//        headers.setContentType(MediaType.APPLICATION_JSON);
//        headers.setBearerAuth(token);
//
//        ObjectMapper objectMapperWrite = new ObjectMapper();
//        String body = objectMapperWrite.writeValueAsString(askRequest);
//
//        HttpEntity<String> request = new HttpEntity<>(body,headers);
//        RestTemplate rt = new RestTemplate();
//
//        ResponseEntity<String> response = rt.exchange(apiUrl,HttpMethod.POST,request,String.class);
//        String json = response.getBody();
//
//        ObjectMapper objectMapperRead = new ObjectMapper();
//        AskResponseDto askResponseDto = objectMapperRead.readValue(json, AskResponseDto.class);
//        String content = askResponseDto.getChoices().get(0).getMessage().getContent();
//        return content;
//    }

    @PostMapping("/edits")
    public String edits(@RequestBody EditsRequestDto requestDto) throws JsonProcessingException {
        String editsRequestDto = parsingMachine.editsRequestDto(requestDto, om);
        HttpEntity<String> request = new HttpEntity<>(editsRequestDto, headers);
        ResponseEntity<String> response = rt.exchange(Edits.GRAMMAR_ENDPOINT.data(), HttpMethod.POST, request, String.class);
        return parsingMachine.editsResponseDto(response.getBody(), om);
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
