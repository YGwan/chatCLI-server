package communication.chatgpt.dto.chat.request;

import communication.chatgpt.dto.chat.response.ChatMessageDto;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.NotNull;
import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class ChatParsedRequestDto {

    @NotNull
    private String model;

    @NotNull
    private List<ChatMessageDto> messages;
}
