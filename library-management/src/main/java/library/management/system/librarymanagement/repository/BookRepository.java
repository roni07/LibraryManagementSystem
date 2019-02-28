package library.management.system.librarymanagement.repository;

import library.management.system.librarymanagement.model.Book;
import org.springframework.data.repository.CrudRepository;

import java.util.Optional;

public interface BookRepository extends CrudRepository<Book, String> {
    Optional<Book> findBookByIsbn(String isbn);
}
