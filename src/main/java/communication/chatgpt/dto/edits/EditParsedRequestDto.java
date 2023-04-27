package communication.chatgpt.dto.edits;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.NotNull;

@Getter @Setter
@NoArgsConstructor
@AllArgsConstructor
public class EditParsedRequestDto {

    @NotNull
    private String model;

    @NotNull
    private String input;

    @NotNull
    private String instruction;
}
