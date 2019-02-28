package library.management.system.librarymanagement.repository;

import library.management.system.librarymanagement.model.User;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface UserRepository extends CrudRepository<User, String> {

    Optional<User> findUserByUserId(String userId);
    List<User> findUsersByUserIdIsNotNull();

    void deleteByUserId(String userId);
}
