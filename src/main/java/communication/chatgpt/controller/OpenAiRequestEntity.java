package communication.chatgpt.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import communication.chatgpt.data.Chat;
import communication.chatgpt.data.Completions;
import communication.chatgpt.data.Summarize;
import communication.chatgpt.dto.chat.request.ChatParsedRequestDto;
import communication.chatgpt.dto.chat.request.ChatRequestDto;
import communication.chatgpt.dto.chat.response.ChatMessageDto;
import communication.chatgpt.dto.completions.request.CompletionsParsedRequestDto;
import communication.chatgpt.dto.completions.request.CompletionsRequestDto;
import communication.chatgpt.dto.summary.request.SummarizeParsedRequestDto;
import communication.chatgpt.dto.summary.request.SummarizeRequestDto;
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

    public HttpEntity<String> grammarCheckParsed(CompletionsRequestDto completionsRequest) throws JsonProcessingException {
        String editsOpenAiBody = objectMapper.
                writeValueAsString(
                        new CompletionsParsedRequestDto(
                                Completions.MODEL.data(),
                                Completions.GRAMMAR_CHECK.data() + completionsRequest.getPrompt(),
                                completionsRequest.getPrompt().length() * 2
                        ));
        return new HttpEntity<>(editsOpenAiBody, headers);
    }

    public HttpEntity<String> translateParsed(CompletionsRequestDto completionsRequest) throws JsonProcessingException {
        String translateOpenAiBody = objectMapper.
                writeValueAsString(
                        new CompletionsParsedRequestDto(
                                Completions.MODEL.data(),
                                Completions.TRANSLATE.data() + completionsRequest.getPrompt(),
                                completionsRequest.getPrompt().length() * 2
                        )
                );
        return new HttpEntity<>(translateOpenAiBody, headers);

    }

    public HttpEntity<String> tweetClassifierParsed(CompletionsRequestDto completionsRequest) throws JsonProcessingException {
        String summarizeOpenAiBody = objectMapper.
                writeValueAsString(
                        new CompletionsParsedRequestDto(
                                Completions.MODEL.data(),
                                Completions.TWEET_CLASSIFIER.data() + completionsRequest.getPrompt(),
                                completionsRequest.getPrompt().length() * 2
                        )
                );
        return new HttpEntity<>(summarizeOpenAiBody, headers);
    }

    public HttpEntity<String> summarizeParsed(SummarizeRequestDto summarizeRequest) throws JsonProcessingException {
        String summarizeOpenAiBody = objectMapper.
                writeValueAsString(
                        new SummarizeParsedRequestDto(
                                Summarize.MODEL.data(),
                                summarizeRequest.getPrompt() + "Tl;dr"
                        )
                );
        return new HttpEntity<>(summarizeOpenAiBody, headers);
    }
}
