package communication.chatgpt.dto.createchat.response;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class UsageDto {

    private int prompt_tokens;
    private int completion_tokens;
    private int total_tokens;

}
