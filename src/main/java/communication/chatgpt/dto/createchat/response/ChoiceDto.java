package communication.chatgpt.dto.createchat.response;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class ChoiceDto {

    private MessageDto message;
    private String finish_reason;
    private int index;

}
