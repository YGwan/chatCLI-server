package communication.chatgpt.dto.tweetClassifier;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.NotNull;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class TweetClassifierParsedRequestDto {

    private final static int TEMPERATURE = 0;
    private final static int MAX_TOKENS = 60;
    private final static double TOP_P = 1.0;
    private final static double FREQUENCY_PENALTY = 0.0;
    private final static double PRESENCE_PENALTY = 0.0;


    @NotNull
    private String model;

    @NotNull
    private String prompt;

    private int temperature;

    private int max_tokens;

    private double top_p;

    private double frequency_penalty;

    private double presence_penalty;

    public TweetClassifierParsedRequestDto(String model, String prompt) {
        this.model = model;
        this.prompt = prompt;
        this.temperature = TEMPERATURE;
        this.max_tokens = MAX_TOKENS;
        this.top_p = TOP_P;
        this.frequency_penalty = FREQUENCY_PENALTY;
        this.presence_penalty = PRESENCE_PENALTY;
    }
}
