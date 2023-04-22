package communication.chatgpt.dto.createchat.response;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.NotNull;

@Getter@Setter
@NoArgsConstructor
@AllArgsConstructor
public class RoleDto {

    @NotNull
    private String role;

    @NotNull
    private String content;

}
