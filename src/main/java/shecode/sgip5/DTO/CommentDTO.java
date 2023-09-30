package shecode.sgip5.DTO;

import jakarta.persistence.Column;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import shecode.sgip5.model.Blog;
import shecode.sgip5.model.Comment;
import shecode.sgip5.model.University;
import shecode.sgip5.model.User;

import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CommentDTO {

    private Integer id;

    private String content;

    private Timestamp createAt = Timestamp.valueOf(LocalDateTime.now());

    private User user;

    private List<Comment> subComment;
}
