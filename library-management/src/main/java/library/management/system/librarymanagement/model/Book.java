package library.management.system.librarymanagement.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.List;

@NoArgsConstructor
@AllArgsConstructor
@Data
@Entity
public class Book {
    @Id
    private String isbn;
    private String bookName;
    private String bookAuthor;
    private String publisher;
    private String edition;

    @ManyToMany
    private List<Category> category;

    private int quantity;
}
