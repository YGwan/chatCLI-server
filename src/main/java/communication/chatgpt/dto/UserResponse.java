package communication.chatgpt.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class UserResponse {

    private String answer;

    public static UserResponse of(String openAiMessage) {
        return new UserResponse(openAiMessage);
    }

    public String answer() {
        return answer;
    }
}
