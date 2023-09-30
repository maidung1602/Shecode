package shecode.sgip5.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import shecode.sgip5.model.User;

public interface IUserRepository extends JpaRepository<User, Integer> {
    User findUserByEmail(String email);
}
