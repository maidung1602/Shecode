package shecode.sgip5.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import shecode.sgip5.model.University;

import java.util.List;

@Repository
public interface UniversityRepository extends JpaRepository<University, Integer> {
    University getUniversityByCode(String code);

    @Query(value="SELECT * FROM shecodes.university u\n" +
            "WHERE LOWER(u.name) LIKE LOWER(CONCAT('%', :keyword, '%')) OR LOWER(u.code) LIKE LOWER(CONCAT('%', :keyword, '%'))", nativeQuery = true)
    List<University> searchByNameOrCode(@Param("keyword") String keyword);

}
