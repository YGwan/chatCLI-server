package communication.chatgpt.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import communication.chatgpt.dto.chat.response.OpenAiChatResponseDto;
import communication.chatgpt.dto.completions.response.CompletionsResponseDto;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpEntity;
import org.springframework.http.ResponseEntity;

@SpringBootTest
class OpenAiResponseEntityTest {

    @Autowired
    private OpenAiRequestEntity openAiRequestEntity;

    ParsedOpenAiResponse parsedOpenAiResponse = new ParsedOpenAiResponse();
    SendOpenAiServer sendOpenAiServer = new SendOpenAiServer();

    private final String question = "question";

    @DisplayName("질문하기 명령어 응답 패킷이 제대로 처리가 되었는지 테스트")
    @Test
    void chatParsed() throws JsonProcessingException {
        HttpEntity<String> openAiRequestPacket = openAiRequestEntity.chatParsed(question);
        ResponseEntity<String> openAiResponsePacket = sendOpenAiServer.chatCommand(openAiRequestPacket);
        Assertions.assertThat(parsedOpenAiResponse.chatParsed(openAiResponsePacket)).isNotNull();
    }

    @DisplayName("문법 검사 명령어 응답 패킷이 제대로 처리가 되었는지 테스트")
    @Test
    void grammarCheckParsed() throws JsonProcessingException {
        HttpEntity<String> openAiRequestPacket = openAiRequestEntity.grammarCheckParsed(question);
        ResponseEntity<String> openAiResponsePacket = sendOpenAiServer.completionsCommand(openAiRequestPacket);
        Assertions.assertThat(parsedOpenAiResponse.completionsParsed(openAiResponsePacket)).isNotNull();
    }

    @DisplayName("번역 명령어 응답 패킷이 제대로 처리가 되었는지 테스트")
    @Test
    void translateParsed() throws JsonProcessingException {
        HttpEntity<String> openAiRequestPacket = openAiRequestEntity.translateParsed(question);
        ResponseEntity<String> openAiResponsePacket = sendOpenAiServer.completionsCommand(openAiRequestPacket);
        Assertions.assertThat(parsedOpenAiResponse.completionsParsed(openAiResponsePacket)).isNotNull();
    }

    @DisplayName("감정 분석 명령어 응답 패킷이 제대로 처리가 되었는지 테스트")
    @Test
    void tweetClassifierParsed() throws JsonProcessingException {
        HttpEntity<String> openAiRequestPacket = openAiRequestEntity.tweetClassifierParsed(question);
        ResponseEntity<String> openAiResponsePacket = sendOpenAiServer.completionsCommand(openAiRequestPacket);
        Assertions.assertThat(parsedOpenAiResponse.completionsParsed(openAiResponsePacket)).isNotNull();
    }

    @DisplayName("요약 명령어 응답 패킷이 제대로 처리가 되었는지 테스트")
    @Test
    void summarizeParsed() throws JsonProcessingException {
        HttpEntity<String> openAiRequestPacket = openAiRequestEntity.summarizeParsed(question);
        ResponseEntity<String> openAiResponsePacket = sendOpenAiServer.completionsCommand(openAiRequestPacket);
        Assertions.assertThat(parsedOpenAiResponse.completionsParsed(openAiResponsePacket)).isNotNull();
    }
}

class ParsedOpenAiResponse {

    ObjectMapper objectMapper = new ObjectMapper();

    public String chatParsed(ResponseEntity<String> response) throws JsonProcessingException {
        OpenAiChatResponseDto openAiChatResponseDto = objectMapper.readValue(response.getBody(), OpenAiChatResponseDto.class);
        return openAiChatResponseDto.getChoices().get(0).getMessage().getContent().trim();
    }

    public String completionsParsed(ResponseEntity<String> response) throws JsonProcessingException {
        CompletionsResponseDto completionsResponseDto = objectMapper.readValue(response.getBody(), CompletionsResponseDto.class);
        return completionsResponseDto.getChoices().get(0).getText().trim();
    }
}
