package letsCode.sweater.repos;

import letsCode.sweater.domain.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepo extends JpaRepository<User, Long> {

    // Look:
    // https://docs.spring.io/spring-data/jpa/docs/1.5.0.RELEASE/reference/html/jpa.repositories.html
    // #jpa.query-methods.query-creation
    // query can be created by naming method of interface which extends JpaRepository<T, ID>
    // returns user with special 'username'
    User findByUsername(String username);

}
