package library.management.system.librarymanagement.repository;

import library.management.system.librarymanagement.model.Issues;
import org.springframework.data.repository.CrudRepository;

public interface IssuesRepository extends CrudRepository<Issues, Integer> {
}
