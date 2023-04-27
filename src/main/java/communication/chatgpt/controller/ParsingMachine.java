package communication.chatgpt.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import communication.chatgpt.data.Edits;
import communication.chatgpt.dto.edits.EditsParsedRequestDto;
import communication.chatgpt.dto.edits.EditsRequestDto;
import org.springframework.http.ResponseEntity;
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

//    AskResponseDto askResponseDto = objectMapperRead.readValue(json, AskResponseDto.class);
//    String content = askResponseDto.getChoices().get(0).getMessage().getContent();
//        return content;
    public String editsResponseDto(ResponseEntity<String> response, ObjectMapper objectMapper) throws JsonProcessingException {
        JsonNode rootNode = objectMapper.readTree(response.getBody());
        JsonNode choiceObject = rootNode.get("choices").get(0);
        return choiceObject.get("text").asText();
    }
}
