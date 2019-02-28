package library.management.system.librarymanagement.repository;

import library.management.system.librarymanagement.model.Role;
import org.springframework.data.repository.CrudRepository;

import java.util.Optional;

public interface RoleRepository extends CrudRepository<Role, Integer> {
    Optional<Role> findByRole(String role);

    void deleteByRole(String roleName);
}
