package communication.chatgpt.entity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class Keyword {

    @GeneratedValue(strategy =  GenerationType.IDENTITY)
    @Id
    Long id;

    String keyword;
}
