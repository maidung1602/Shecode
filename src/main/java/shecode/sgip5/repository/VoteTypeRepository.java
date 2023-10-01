package shecode.sgip5.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import shecode.sgip5.model.University;
import shecode.sgip5.model.VoteType;

public interface VoteTypeRepository extends JpaRepository<VoteType, Integer> {
}
