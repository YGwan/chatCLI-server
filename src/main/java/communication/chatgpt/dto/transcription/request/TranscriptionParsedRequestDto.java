package communication.chatgpt.dto.transcription.request;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.web.multipart.MultipartFile;

import javax.validation.constraints.NotNull;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class TranscriptionParsedRequestDto {

    @NotNull
    private String model;

    @NotNull
    private MultipartFile file;
}
