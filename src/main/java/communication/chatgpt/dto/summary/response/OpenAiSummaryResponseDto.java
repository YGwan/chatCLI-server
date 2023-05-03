package communication.chatgpt.dto.summary.response;

import communication.chatgpt.dto.chat.response.ChatUsageDto;
import communication.chatgpt.dto.edits.response.EditChoiceDto;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class OpenAiSummaryResponseDto {
    private String id;
    private String object;
    private long created;
    private String model;
    private List<SummaryChoiceDto> choices;
    private SummaryUsageDto usage;
}