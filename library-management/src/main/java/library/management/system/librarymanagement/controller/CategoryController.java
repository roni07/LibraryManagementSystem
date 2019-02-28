package library.management.system.librarymanagement.controller;

import library.management.system.librarymanagement.model.Category;
import library.management.system.librarymanagement.service.CategoryService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("category")
public class CategoryController {

    private CategoryService categoryService;

    public CategoryController(CategoryService categoryService) {
        this.categoryService = categoryService;
    }

    @PostMapping("save")
    public ResponseEntity<Category> saveCategory(@RequestBody Category category){
        categoryService.save(category);
        try {
            ResponseEntity<Category> categoryResponseEntity = ResponseEntity
                    .status(HttpStatus.CREATED)
                    .body(category);
            return categoryResponseEntity;
        }
        catch (Exception e){
            return ResponseEntity.badRequest().body(null);
        }
    }

    @GetMapping("read")
    public ResponseEntity<Category> readCategory(){
        //categoryService.getAll();
        try {
            ResponseEntity responseEntity = ResponseEntity
                    .status(HttpStatus.FOUND)
                    .body(categoryService.getAll());
            return responseEntity;
        }
        catch (Exception e){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
        }
    }

    @PutMapping("update/{categoryName}")
    public ResponseEntity<Category> updateCategory(@RequestBody Category category,
                                                 @PathVariable String categoryName){
        categoryService.editCategory(category, categoryName);
        try {
            ResponseEntity<Category> categoryResponseEntity = ResponseEntity
                    .status(HttpStatus.CREATED)
                    .body(category);
            return categoryResponseEntity;
        }
        catch (Exception e){
            return ResponseEntity.badRequest().body(null);
        }

    }

    @DeleteMapping("delete/{categoryName}")
    public ResponseEntity<?> deleteCategory(@PathVariable String categoryName){
        categoryService.removeCategory(categoryName);
        try {
            ResponseEntity responseEntity = ResponseEntity
                    .status(HttpStatus.OK)
                    .body("Deleted");
            return responseEntity;
        }
        catch (Exception e){
           return ResponseEntity.badRequest().body(null);
        }
    }
}
