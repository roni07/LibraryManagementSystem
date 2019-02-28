package library.management.system.librarymanagement.repository;

import library.management.system.librarymanagement.model.Category;
import org.springframework.data.repository.CrudRepository;

import java.util.Optional;

public interface CategoryRepository extends CrudRepository<Category, String> {

    Optional<Category> findByCategoryName(String categoryName);

    void deleteByCategoryName(String categoryName);
}
