package communication.chatgpt.dto.tweetClassifier.response;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class TweetClassifierResponseDto {

    private String id;
    private String object;
    private long created;
    private String model;
    private List<TweetClassifierChoicesDto> choices;
    private TweetClassifierUsageDto usage;
}
