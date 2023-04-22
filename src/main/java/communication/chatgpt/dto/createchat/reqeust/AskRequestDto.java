package communication.chatgpt.dto.createchat.reqeust;


import communication.chatgpt.dto.createchat.response.RoleDto;
import lombok.*;

import javax.validation.constraints.NotNull;
import java.util.List;

@Getter@Setter
@AllArgsConstructor
@NoArgsConstructor
public class AskRequestDto {

    @NotNull
    private String model;

    @NotNull
    private List<RoleDto> messages;

}