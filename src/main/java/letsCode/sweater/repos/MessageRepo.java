package letsCode.sweater.repos;

import letsCode.sweater.domain.Message;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

// This will be AUTO IMPLEMENTED by Spring into a Bean called MessageRepo
// CRUD refers Create, Read, Update, Delete
public interface MessageRepo extends CrudRepository<Message, Long> {

    // Look:
    // https://docs.spring.io/spring-data/jpa/docs/1.5.0.RELEASE/reference/html/jpa.repositories.html
    // #jpa.query-methods.query-creation
    // query created by naming method of interface which extends CrudRepository<T, ID>
    List<Message> findByTag (String tag);

}
