package communication.chatgpt.dto.summary.response;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class SummaryChoiceDto {
    private String text;
    private int index;
    private String logprobs;
    private String finish_reason;
}
