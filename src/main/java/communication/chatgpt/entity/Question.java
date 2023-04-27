package communication.chatgpt.entity;

import javax.persistence.*;

@Entity
public class Question {

    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    Long id;

    String question;

    String answer;

    @ManyToOne
    @JoinColumn(name = "keyword_id")
    private Keyword keyword;
}
