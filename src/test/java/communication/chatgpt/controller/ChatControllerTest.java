package communication.chatgpt.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.jayway.jsonpath.DocumentContext;
import com.jayway.jsonpath.JsonPath;
import communication.chatgpt.data.Chat;
import communication.chatgpt.data.Completions;
import communication.chatgpt.dto.chat.request.ChatParsedRequestDto;
import communication.chatgpt.dto.chat.response.ChatMessageDto;
import communication.chatgpt.dto.completions.request.CompletionsParsedRequestDto;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.*;
import org.springframework.test.context.ActiveProfiles;

import java.util.Collections;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

@ActiveProfiles("default")
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class ChatControllerTest {
    @Value("${open-ai.token}")
    private static String token;

    @Autowired
    private TestRestTemplate rt;
    private static ObjectMapper om;
    private static HttpHeaders headers;

    @BeforeAll
    public static void init(){
        om = new ObjectMapper();
        headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        headers.setBearerAuth(token);
    }

    @Test
    @DisplayName("chat api 테스트")
    public void chatApi_Test() throws Exception {
        // given
        ChatParsedRequestDto chatParsedRequestDto = new ChatParsedRequestDto();
        ChatMessageDto chatMessageDto = new ChatMessageDto(Chat.ROLE.data(), "Hello");
        chatParsedRequestDto.setModel(Chat.MODEL.data());
        chatParsedRequestDto.setMessages(Collections.singletonList(chatMessageDto));
        String body = om.writeValueAsString(chatParsedRequestDto);

        // when
        HttpEntity<String> request = new HttpEntity<>(body, headers);
        ResponseEntity<String> response = rt.exchange("/v1/chat/completions", HttpMethod.POST, request, String.class);
        HttpStatus statusCode = response.getStatusCode();

        // then
        DocumentContext dc = JsonPath.parse(response.getBody());
        String answer = dc.read("$.messages[0].content");

        assertEquals(HttpStatus.OK, statusCode);
        assertNotNull(answer);
    }

    @Test
    @DisplayName("grammar Check 테스트")
    public void grammarCheck_Test() throws Exception {
        // given
        String prompt = "What day of the wek is it?";
        CompletionsParsedRequestDto completionsParsedRequestDto = new CompletionsParsedRequestDto(Completions.MODEL.data(),prompt,prompt.length());
        String body = om.writeValueAsString(completionsParsedRequestDto);

        // when
        HttpEntity<String> request = new HttpEntity<>(body, headers);
        ResponseEntity<String> response = rt.exchange("/v1/gc", HttpMethod.POST, request, String.class);
        HttpStatus statusCode = response.getStatusCode();
        System.out.println(response);

        // then
        DocumentContext dc = JsonPath.parse(response.getBody());
        String answer = dc.read("$.prompt");

        assertEquals(HttpStatus.OK, statusCode);
        assertNotNull(answer);
    }
}