package communication.chatgpt.dto.chat.response;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class OpenAiChatResponseDto {

    private String id;
    private String object;
    private long created;
    private String model;
    private ChatUsageDto usage;
    private List<ChatChoiceDto> choices;
}