package shecode.sgip5.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import shecode.sgip5.model.School;
@Repository
public interface ISchoolRepository extends JpaRepository<School, Integer> {
    School getSchoolByCode(String code);
}
