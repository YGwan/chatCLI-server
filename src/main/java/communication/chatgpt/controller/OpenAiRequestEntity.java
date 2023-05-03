package communication.chatgpt.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import communication.chatgpt.data.Chat;
import communication.chatgpt.data.Edits;
import communication.chatgpt.dto.chat.request.ChatParsedRequestDto;
import communication.chatgpt.dto.chat.request.ChatRequestDto;
import communication.chatgpt.dto.chat.response.ChatMessageDto;
import communication.chatgpt.dto.edits.request.EditsParsedRequestDto;
import communication.chatgpt.dto.edits.request.EditsRequestDto;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.stereotype.Component;

import java.util.Collections;

@RequiredArgsConstructor
@Component
public class OpenAiRequestEntity {

    private final ObjectMapper objectMapper;
    private final HttpHeaders headers;

    public HttpEntity<String> chatParsed(ChatRequestDto chatRequest) throws JsonProcessingException {

        ChatMessageDto chatMessageDto = new ChatMessageDto(Chat.ROLE.data(), chatRequest.getContent());

        String chatOpenAiBody = objectMapper.
                writeValueAsString(
                        new ChatParsedRequestDto(
                                Chat.MODEL.data(),
                                Collections.singletonList(chatMessageDto)
                        ));
        return new HttpEntity<>(chatOpenAiBody, headers);
    }

    public HttpEntity<String> editsParsed(EditsRequestDto editRequest) throws JsonProcessingException {
        String editsOpenAiBody = objectMapper.
                writeValueAsString(
                        new EditsParsedRequestDto(
                                Edits.MODEL.data(),
                                editRequest.getInput(),
                                Edits.INSTRUCTION.data()
                        ));
        return new HttpEntity<>(editsOpenAiBody, headers);
    }
}
