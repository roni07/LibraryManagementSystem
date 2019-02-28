package library.management.system.librarymanagement.controller;

import library.management.system.librarymanagement.model.Book;
import library.management.system.librarymanagement.model.Category;
import library.management.system.librarymanagement.security_constant.JwtGenerator;
import library.management.system.librarymanagement.security_constant.SecurityConstant;
import library.management.system.librarymanagement.service.BookService;
import library.management.system.librarymanagement.service.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.List;

@RequestMapping("book")
@RestController
public class BookController {

    private BookService bookService;

    @Autowired
    private HttpServletRequest httpServletRequest;


    private CategoryService categoryService;

    public BookController(BookService bookService, CategoryService categoryService) {
        this.bookService = bookService;
        this.categoryService = categoryService;
    }

    @PostMapping("/save/{categoryName}")
    public ResponseEntity<Book> saveBook(@RequestBody Book book,
                                         @PathVariable String categoryName) {

        List<Category> categories = new ArrayList<>();

        String token = httpServletRequest.getHeader(SecurityConstant.HEADER_STRING);

        List<Integer> bitMaskList =  JwtGenerator.parseTokenGetBitMask(token);

        for (Integer o : bitMaskList) {
            System.out.println(o);
        }

        try {
            Category category = categoryService.getCategory(categoryName);
            categories.add(category);
            book.setCategory(categories);
            bookService.save(book, bitMaskList);
            ResponseEntity<Book> bookResponseEntity = ResponseEntity
                    .status(HttpStatus.CREATED)
                    .body(book);
            return bookResponseEntity;
        }
        catch (Exception e){
            return ResponseEntity.badRequest().body(null);
        }
    }

    @PutMapping("update/{isbn}")
    public ResponseEntity<Book> updateBook(@RequestBody Book book,
                                           @PathVariable String isbn){
        try{
            bookService.update(book, isbn);
            ResponseEntity<Book> bookResponseEntity = ResponseEntity
                    .status(HttpStatus.CREATED)
                    .body(book);
            return bookResponseEntity;
        }
        catch (Exception e){
            return ResponseEntity.badRequest().body(null);
        }
    }

    @DeleteMapping("delete/{isbn}")
    public ResponseEntity<?> deleteBook(@PathVariable String isbn){
        try {
            bookService.delete(isbn);
            ResponseEntity responseEntity = ResponseEntity
                    .status(HttpStatus.OK)
                    .body("Deleted");
            return responseEntity;
        }
        catch (Exception e){
            return ResponseEntity.badRequest().body(null);
        }
    }

    @GetMapping("all")
    public Iterable<Book> getAllBook(){
        return bookService.getAll();
    }
}
