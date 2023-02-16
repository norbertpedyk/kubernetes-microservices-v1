package pl.pedyk.post.dto;

import jakarta.validation.constraints.NotNull;
import lombok.*;

import java.util.Date;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class PostDto {

    private Long id;

    private String topic;
    @NotNull(message = "post must contain authorId")
    private Long authorId;
    private String text;
    private Date postedAt;
}
