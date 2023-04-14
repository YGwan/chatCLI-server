package communication.chatgpt.chat;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import communication.chatgpt.chat.dto.SendChatRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.*;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

@RestController
public class ChatController {
    @Value("${open-ai.token}")
    private String token;
    @Value("${open-ai.url}")
    private String apiUrl;
    @PostMapping("/v1/edits")
    public String chat(@RequestBody SendChatRequest sendChatRequest) throws JsonProcessingException {

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        headers.setBearerAuth(token);

        ObjectMapper objectMapper = new ObjectMapper();
        String body = objectMapper.writeValueAsString(sendChatRequest);

        HttpEntity<String> request = new HttpEntity<>(body,headers);
        RestTemplate rt = new RestTemplate();

        ResponseEntity<String> response = rt.exchange(apiUrl,HttpMethod.POST, request, String.class);

        String json = response.getBody();

        JsonNode jsonNode = objectMapper.readTree(json);
        String text = jsonNode.get("choices").get(0).get("text").asText();

        System.out.println(sendChatRequest.getN());
        return text;
    }
}
