package communication.chatgpt.controller;

import communication.chatgpt.dto.AskAndStoreRequest;
import communication.chatgpt.dto.SearchUserQRequest;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/chat/db")
public class DBController {

    @PostMapping("/ask")
    public String askAndStore(@RequestBody AskAndStoreRequest request) {
        return "askAndStore";
    }

    @PostMapping("search/questions")
    public List<String> searchUserQ(@RequestBody SearchUserQRequest request) {
        List<String> answer = new ArrayList<>();
        answer.add("searchUserQ");
        return answer;
    }
}
