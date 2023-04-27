package communication.chatgpt.dto.edits.request;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.NotNull;

@Getter @Setter
@NoArgsConstructor
@AllArgsConstructor
public class EditsParsedRequestDto {

    @NotNull
    private String model;

    @NotNull
    private String input;

    @NotNull
    private String instruction;
}
