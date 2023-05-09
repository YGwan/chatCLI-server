package communication.chatgpt.controller;

import com.jayway.jsonpath.DocumentContext;
import com.jayway.jsonpath.JsonPath;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.*;
import org.springframework.test.context.ActiveProfiles;

import static org.junit.jupiter.api.Assertions.*;
@ActiveProfiles("default")
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class OpenAiControllerTest {

    @Autowired
    private TestRestTemplate rt;

    private final String question = "Hello !";
    private String Request = "";
    private static HttpHeaders headers;

    @BeforeAll
    public static void init() {
        headers = new HttpHeaders();
        headers.setContentType(MediaType.TEXT_PLAIN);
    }

    @Test
    @DisplayName("chat api 테스트")
    public void chatApi_Test() {
        Request += question;
        HttpEntity<String> terminalRequest = new HttpEntity<>(Request, headers);
        ResponseEntity<String> response = rt.exchange("/v1/chat/completions", HttpMethod.POST, terminalRequest, String.class);
        HttpStatus statusCode = response.getStatusCode();

        DocumentContext dc = JsonPath.parse(response.getBody());
        String answer = dc.read("$");

        assertEquals(HttpStatus.OK, statusCode);
        assertNotNull(answer);
    }

    @Test
    @DisplayName("grammar check api 테스트")
    public void grammarCheck_Test() {
        Request += question;
        HttpEntity<String> terminalRequest = new HttpEntity<>(Request, headers);
        ResponseEntity<String> response = rt.exchange("/v1/gc", HttpMethod.POST, terminalRequest, String.class);
        HttpStatus statusCode = response.getStatusCode();

        DocumentContext dc = JsonPath.parse(response.getBody());
        String answer = dc.read("$");

        assertEquals(HttpStatus.OK, statusCode);
        assertNotNull(answer);
    }
}