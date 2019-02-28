package library.management.system.librarymanagement.controller;

import library.management.system.librarymanagement.exception.ResourceInsufficientException;
import library.management.system.librarymanagement.model.Book;
import library.management.system.librarymanagement.model.Issues;
import library.management.system.librarymanagement.model.User;
import library.management.system.librarymanagement.service.BookService;
import library.management.system.librarymanagement.service.IssuesService;
import library.management.system.librarymanagement.service.UserService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RequestMapping("issues")
@RestController
public class IssuesController {

    private IssuesService issuesService;
    private UserService userService;
    private BookService bookService;

    public IssuesController(IssuesService issuesService, UserService userService, BookService bookService) {
        this.issuesService = issuesService;
        this.userService = userService;
        this.bookService = bookService;
    }

    @PostMapping("/save/{userId}/{isbn}")
    public ResponseEntity<Issues> saveIssues (@RequestBody Issues issues,
                                              @PathVariable String isbn,
                                             @PathVariable String userId){
        try {
            User user = userService.getUser(userId);
            Book book = bookService.getBook(isbn);
            if (book.getQuantity() > 0) {
                issues.setStBook(book, user);
                Issues saveIssues = issuesService.issueBook(issues);

                book.setQuantity(book.getQuantity() - 1);
                bookService.update(book, book.getIsbn());

                ResponseEntity<Issues> issuesResponseEntity = ResponseEntity
                        .status(HttpStatus.CREATED)
                        .body(saveIssues);
                return issuesResponseEntity;
            } else {
                throw new ResourceInsufficientException("Insufficient Book");
            }
        }
        catch (Exception e){
            return ResponseEntity.badRequest().body(null);
        }
    }

}
