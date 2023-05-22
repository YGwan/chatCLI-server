package communication.chatgpt.entity;

import lombok.*;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
@Getter @Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Keyword {

    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    Long id;

    String keyword;

    int count;
}
