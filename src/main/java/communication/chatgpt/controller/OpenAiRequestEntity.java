package communication.chatgpt.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import communication.chatgpt.data.Chat;
import communication.chatgpt.data.Completions;
import communication.chatgpt.dto.chat.request.ChatParsedRequestDto;
import communication.chatgpt.dto.chat.response.ChatMessageDto;
import communication.chatgpt.dto.completions.request.CompletionsParsedRequestDto;
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

    public HttpEntity<String> chatParsed(String content) throws JsonProcessingException {
        ChatMessageDto chatMessageDto = new ChatMessageDto(Chat.ROLE.data(), content);

        String chatOpenAiBody = objectMapper.
                writeValueAsString(
                        new ChatParsedRequestDto(
                                Chat.MODEL.data(),
                                Collections.singletonList(chatMessageDto)
                        ));
        return new HttpEntity<>(chatOpenAiBody, headers);
    }

    public HttpEntity<String> grammarCheckParsed(String prompt) throws JsonProcessingException {
        String editsOpenAiBody = objectMapper.
                writeValueAsString(
                        new CompletionsParsedRequestDto(
                                Completions.MODEL.data(),
                                Completions.GRAMMAR_CHECK.data() + prompt,
                                prompt.length() * 2
                        ));
        return new HttpEntity<>(editsOpenAiBody, headers);
    }

    public HttpEntity<String> translateParsed(String prompt) throws JsonProcessingException {
        String translateOpenAiBody = objectMapper.
                writeValueAsString(
                        new CompletionsParsedRequestDto(
                                Completions.MODEL.data(),
                                Completions.TRANSLATE.data() + prompt,
                                prompt.length() * 2
                        )
                );
        return new HttpEntity<>(translateOpenAiBody, headers);

    }

    public HttpEntity<String> tweetClassifierParsed(String prompt) throws JsonProcessingException {
        String tweetClassifierOpenAiBody = objectMapper.
                writeValueAsString(
                        new CompletionsParsedRequestDto(
                                Completions.MODEL.data(),
                                Completions.TWEET_CLASSIFIER.data() + prompt,
                                prompt.length() * 2
                        )
                );
        return new HttpEntity<>(tweetClassifierOpenAiBody, headers);
    }

    public HttpEntity<String> summarizeParsed(String prompt) throws JsonProcessingException {
        String summarizeOpenAiBody = objectMapper.
                writeValueAsString(
                        new CompletionsParsedRequestDto(
                                Completions.MODEL.data(),
                                prompt + Completions.SUMMARIZE.data(),
                                prompt.length() * 2
                        )
                );
        return new HttpEntity<>(summarizeOpenAiBody, headers);
    }
}
