package communication.chatgpt.controller;

import communication.chatgpt.dto.edits.request.EditsRequestDto;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class ChatControllerTest {

    @Autowired
    private TestRestTemplate restTemplate;

    @DisplayName("EDITS 명령어 테스트")
    @Test
    void EDITS_명령어_테스트() {
        final String input = "What day of the wek is it?";
        final EditsRequestDto request = new EditsRequestDto(input);

        ResponseEntity<String> responseEntity = restTemplate.postForEntity("/v1/edits", request, String.class);

        HttpStatus statusCode = responseEntity.getStatusCode();
        String responseBody = responseEntity.getBody();

        final String actual = "What day of the week is it?";

        assertEquals(HttpStatus.OK, statusCode);
        assertEquals(actual, responseBody);
    }
}