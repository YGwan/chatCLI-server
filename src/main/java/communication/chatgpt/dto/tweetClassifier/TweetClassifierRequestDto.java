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
public class TweetClassifierRequestDto {

    @NotNull
    private String prompt;
}
