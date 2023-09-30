package shecode.sgip5.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import shecode.sgip5.model.Blog;

public interface BlogRepository extends JpaRepository<Blog, Integer> {
}
