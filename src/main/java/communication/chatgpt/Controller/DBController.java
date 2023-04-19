package communication.chatgpt.Controller;

import communication.chatgpt.dto.SearchUserQRequest;
import communication.chatgpt.dto.askAndStoreRequest;
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
    public String askAndStore(@RequestBody askAndStoreRequest request) {
        return "askAndStore";
    }

    @PostMapping("search/questions")
    public List<String> searchUserQ(@RequestBody SearchUserQRequest request) {
        List<String> answer = new ArrayList<>();
        answer.add("searchUserQ");
        return answer;
    }
}
