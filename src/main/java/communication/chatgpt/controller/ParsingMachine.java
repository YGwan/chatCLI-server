package communication.chatgpt.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import communication.chatgpt.data.Edits;
import communication.chatgpt.dto.edits.request.EditsParsedRequestDto;
import communication.chatgpt.dto.edits.request.EditsRequestDto;
import communication.chatgpt.dto.edits.response.EditResponseDto;
import org.springframework.stereotype.Component;

@Component
public class ParsingMachine {

    public String editsRequestDto(EditsRequestDto editRequest, ObjectMapper objectMapperWrite) throws JsonProcessingException {
        return objectMapperWrite.
                writeValueAsString(
                        new EditsParsedRequestDto(
                                Edits.MODEL.data(),
                                editRequest.getInput(),
                                Edits.INSTRUCTION.data()
                        ));
    }

    public String editsResponseDto(String response, ObjectMapper objectMapper) throws JsonProcessingException {
        EditResponseDto editResponseDto = objectMapper.readValue(response, EditResponseDto.class);
        return editResponseDto.getChoices().get(0).getText().trim();
    }
}
