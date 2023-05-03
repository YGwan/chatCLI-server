package communication.chatgpt.dto.completions.response;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class CompletionsChoicesDto {

    private String text;
    private int index;
    private String logprobs;
    private String finish_reason;
}
