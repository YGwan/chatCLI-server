package communication.chatgpt.dto.createchat.response;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import java.util.List;

@Getter @Setter
@NoArgsConstructor
@AllArgsConstructor
public class AskResponseDto {

    private String id;
    private String object;
    private long created;
    private String model;
    private UsageDto usage;
    private List<ChoiceDto> choices;

}