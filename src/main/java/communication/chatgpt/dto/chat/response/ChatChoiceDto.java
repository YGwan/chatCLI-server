package communication.chatgpt.dto.chat.response;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class ChatChoiceDto {

    private ChatMessageDto message;
    private String finish_reason;
    private int index;
}
