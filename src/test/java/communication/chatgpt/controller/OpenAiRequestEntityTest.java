package communication.chatgpt.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import communication.chatgpt.data.Chat;
import communication.chatgpt.data.Completions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest
class OpenAiRequestEntityTest {

    @Autowired
    private OpenAiRequestEntity openAiRequestEntity;

    private final SendOpenAiServer sendOpenAiServer = new SendOpenAiServer();

    private final String question = "question";

    @DisplayName("질문하기 명령어 요청 패킷 테스트")
    @Test
    void chatParsed() throws JsonProcessingException {
        HttpEntity<String> openAiRequestPacket = openAiRequestEntity.chatParsed(question);
        ResponseEntity<String> openAiResponsePacket = sendOpenAiServer.chatCommand(openAiRequestPacket);
        assertEquals(HttpStatus.OK, openAiResponsePacket.getStatusCode());
    }

    @DisplayName("문법 검사 명령어 요청 패킷 테스트")
    @Test
    void grammarCheckParsed() throws JsonProcessingException {
        HttpEntity<String> openAiRequestPacket = openAiRequestEntity.grammarCheckParsed(question);
        ResponseEntity<String> openAiResponsePacket = sendOpenAiServer.completionsCommand(openAiRequestPacket);
        assertEquals(HttpStatus.OK, openAiResponsePacket.getStatusCode());
    }

    @DisplayName("번역 명령어 요청 패킷 테스트")
    @Test
    void translateParsed() throws JsonProcessingException {
        HttpEntity<String> openAiRequestPacket = openAiRequestEntity.translateParsed(question);
        ResponseEntity<String> openAiResponsePacket = sendOpenAiServer.completionsCommand(openAiRequestPacket);
        assertEquals(HttpStatus.OK, openAiResponsePacket.getStatusCode());

    }

    @DisplayName("감정 분석 명령어 요청 패킷 테스트")
    @Test
    void tweetClassifierParsed() throws JsonProcessingException {
        HttpEntity<String> openAiRequestPacket = openAiRequestEntity.tweetClassifierParsed(question);
        ResponseEntity<String> openAiResponsePacket = sendOpenAiServer.completionsCommand(openAiRequestPacket);
        assertEquals(HttpStatus.OK, openAiResponsePacket.getStatusCode());
    }

    @DisplayName("요약 명령어 요청 패킷 테스트")
    @Test
    void summarizeParsed() throws JsonProcessingException {
        HttpEntity<String> openAiRequestPacket = openAiRequestEntity.summarizeParsed(question);
        ResponseEntity<String> openAiResponsePacket = sendOpenAiServer.completionsCommand(openAiRequestPacket);
        assertEquals(HttpStatus.OK, openAiResponsePacket.getStatusCode());
    }
}

class SendOpenAiServer {

    private final RestTemplate rt = new RestTemplate();

    public ResponseEntity<String> chatCommand(HttpEntity<String> openAiRequestPacket) {
        return rt.exchange(Chat.CHAT_ENDPOINT.data(), HttpMethod.POST, openAiRequestPacket, String.class);
    }

    public ResponseEntity<String> completionsCommand(HttpEntity<String> openAiRequest) {
        return rt.exchange(Completions.ENDPOINT.data(), HttpMethod.POST, openAiRequest, String.class);
    }
}