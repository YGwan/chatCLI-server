package communication.chatgpt.dto.edits.response;

import communication.chatgpt.dto.chat.response.ChatUsageDto;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class OpenAiEditResponseDto {

    private String object;
    private long created;
    private List<EditChoiceDto> choices;
    private ChatUsageDto usage;
}