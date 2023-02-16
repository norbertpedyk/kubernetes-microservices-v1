package pl.pedyk.user.model;

import jakarta.persistence.*;
import lombok.Builder;
import lombok.Getter;

@Entity(name = "users")
@Table(name = "users")
@Builder
@Getter
public class User {

    protected User() {
    }

    protected User(Long id, String userName, String amount) {
        this.id = id;
        this.userName = userName;
        this.amount = amount;
    }

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO, generator = "user_seq_gen")
    @SequenceGenerator(name = "user_seq_gen", sequenceName = "USER_SEQ")
    private Long id;

    private String userName;

    private String amount;


}
