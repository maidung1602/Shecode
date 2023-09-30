package shecode.sgip5.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import shecode.sgip5.model.Comment;
import shecode.sgip5.model.University;

import java.util.List;
@Repository
public interface CommentRepository extends JpaRepository<Comment, Integer> {
    @Query(value="SELECT * \n" +
            "FROM shecodes.comment\n" +
            "WHERE university_id=?1 and parent_comment_id is null", nativeQuery = true)
    List<Comment> findCommentByUniversity(int universityId);

    @Query(value="SELECT * FROM shecodes.comment\n" +
            "WHERE  parent_comment_id =?1", nativeQuery = true)
    List<Comment> findSubComment(int commentId);
}
