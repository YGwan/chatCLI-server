package communication.chatgpt.dto.summary.response;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class SummarizeUsageDto {
    private int prompt_tokens;
    private int completion_tokens;
    private int total_tokens;
}
