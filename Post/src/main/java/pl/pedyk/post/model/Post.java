package pl.pedyk.post.model;

import jakarta.persistence.*;
import lombok.*;

import java.util.Date;

@Entity
@Getter
@Setter
@Builder
@Table(name = "posts")
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor(access = AccessLevel.PROTECTED)
public class Post {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO, generator = "post_seq_gen")
    @SequenceGenerator(name = "post_seq_gen", sequenceName = "POST_SEQ", allocationSize = 1, initialValue = 7)
    private Long id;

    private String topic;

    private Long authorId;

    private String text;

    private Date postedAt;
}
