package shecode.sgip5.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import shecode.sgip5.model.University;
@Repository
public interface UniversityRepository extends JpaRepository<University, Integer> {
    University getUniversityByCode(String code);
}
