package communication.chatgpt.dto.summary.request;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.NotNull;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class SummarizeParsedRequestDto {
    private final static double TEMPERATURE = 0.7;
    private final static int MAX_TOKENS = 100;
    private final static double TOP_P = 1.0;
    private final static double FREQUENCY_PENALTY = 0.0;
    private final static int PRESENCE_PENALTY = 1;

    @NotNull
    private String model;

    @NotNull
    private String prompt;

    private double temperature;

    private int max_tokens;

    private double top_p;

    private double frequency_penalty;

    private int presence_penalty;

    public SummarizeParsedRequestDto(String model, String prompt) {
        this.model = model;
        this.prompt = prompt;
        this.temperature = TEMPERATURE;
        this.max_tokens = MAX_TOKENS;
        this.top_p = TOP_P;
        this.frequency_penalty = FREQUENCY_PENALTY;
        this.presence_penalty = PRESENCE_PENALTY;
    }
}
