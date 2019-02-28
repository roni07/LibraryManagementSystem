package library.management.system.librarymanagement.service;

import library.management.system.librarymanagement.acl_enum.Operations;
import library.management.system.librarymanagement.exception.ResourceAlreadyExistException;
import library.management.system.librarymanagement.exception.ResourceNotFoundException;
import library.management.system.librarymanagement.model.Book;
import library.management.system.librarymanagement.repository.BookRepository;
import library.management.system.librarymanagement.security_constant.BitMaskHandler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;
import java.util.List;
import java.util.Optional;

@Service
public class BookService {

    private BookRepository bookRepository;

    public BookService(BookRepository bookRepository) {
        this.bookRepository = bookRepository;
    }

    public Book save(Book book, List<Integer> bitMaskList) {

        for (Integer integer : bitMaskList) {

            if(integer == 65551) {
                Optional<Book> optionalBook = bookRepository.findBookByIsbn(book.getIsbn());
                if (optionalBook.isPresent()) {
                    throw new ResourceAlreadyExistException("OOPS duplicate entry with ISBN " + book.getIsbn());
                } else {
                    return bookRepository.save(book);
                }
            }

        }

        throw new RuntimeException("Unauthorized!");
    }


    public Iterable<Book> getAll() {
        return bookRepository.findAll();
    }

    public Book getBook(String isbn) {
        Optional<Book> optionalBook = bookRepository.findBookByIsbn(isbn);
        if (optionalBook.isPresent()) {
            return optionalBook.get();
        } else {
            throw new ResourceNotFoundException("ISBN " + isbn + " is not found");
        }
    }

    public Book update(Book book, String isbn) {
        Optional<Book> optionalBook = bookRepository.findBookByIsbn(isbn);
        if (optionalBook.isPresent()) {
            book.setIsbn(isbn);
            book.setCategory(optionalBook.get().getCategory());
            return bookRepository.save(book);
        } else {
            throw new ResourceNotFoundException("OOPS ISBN " + isbn + " is not present");
        }
    }

    public void delete(String isbn) {
        Optional<Book> optionalBook = bookRepository.findBookByIsbn(isbn);
        if (optionalBook.isPresent()) {
            bookRepository.deleteById(isbn);
        } else {
            throw new ResourceNotFoundException("OOPS ISBN " + isbn + " is not present");
        }
    }

    /*public void checkAcl(Book book, int bitmask) {

        if (checkAuthority(bitmask, Operations.CREATE_BOOK)) {
            //then save or do anything
        }
    }

    public boolean checkAuthority(int bitMask, Operations serviceOperations) {

        List<Operations> operations = BitMaskHandler.parseBitMaskGetOperations(bitMask); // Here check the bit mask and find out the accessible authorization List<Operations>

        for (Operations value : operations) {

            if(value.name().equals(serviceOperations.getName())) {
                return  true;
            }

        }

        return false;
    }*/
}
