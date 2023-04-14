package communication.chatgpt.chat.dto;

import lombok.*;

import javax.validation.constraints.NotNull;
import java.util.Optional;

@NoArgsConstructor
@AllArgsConstructor
@Getter @Setter
public class SendChatRequest {
    @NotNull
    private String model;

    private String input;

    @NotNull
    private String instruction;

    private Integer n;

    private Number temperature;

    private Number top_p;
}
