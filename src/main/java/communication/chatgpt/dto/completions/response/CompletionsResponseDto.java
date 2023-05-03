package communication.chatgpt.dto.completions.response;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class CompletionsResponseDto {

    private String id;
    private String object;
    private long created;
    private String model;
    private List<CompletionsChoicesDto> choices;
    private CompletionsUsageDto usage;
}
