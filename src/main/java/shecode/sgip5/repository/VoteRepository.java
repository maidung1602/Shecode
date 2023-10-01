package shecode.sgip5.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import shecode.sgip5.model.Vote;

import java.util.List;

public interface VoteRepository extends JpaRepository<Vote, Integer> {
    @Query(value="SELECT v.university_id, v.vote_type_id, ROUND(AVG(v.star), 1) FROM Vote v WHERE v.university_id=?1 GROUP BY v.university_id, v.vote_type_id", nativeQuery = true)
    List<Object[]> calculateStarByUniversityAndVoteType(int universityId);
    @Query(value="SELECT ROUND(AVG(v.star), 1) FROM Vote v WHERE v.university_id=?1", nativeQuery = true)
    Double calculateStarByUniversity(int universityId);
}
