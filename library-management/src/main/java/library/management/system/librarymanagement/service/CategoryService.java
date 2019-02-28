package library.management.system.librarymanagement.service;

import library.management.system.librarymanagement.exception.ResourceNotFoundException;
import library.management.system.librarymanagement.model.Category;
import library.management.system.librarymanagement.repository.CategoryRepository;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class CategoryService {

    private CategoryRepository categoryRepository;

    public CategoryService(CategoryRepository categoryRepository) {
        this.categoryRepository = categoryRepository;
    }

    public Category save(Category category){
        return categoryRepository.save(category);
    }

    public Iterable<Category> getAll(){
        return categoryRepository.findAll();
    }

    public Category getCategory(String categoryName){
        Optional<Category> optionalCategory = categoryRepository
                .findByCategoryName(categoryName);
        if (optionalCategory.isPresent()){
            return optionalCategory.get();
        }
        else {
            throw new ResourceNotFoundException("OOPS Category Name "
                    +categoryName+" is not present");
        }
    }

    public Category editCategory(Category category, String categoryName){
        Optional<Category> optionalCategory = categoryRepository
                .findByCategoryName(categoryName);
        if (optionalCategory.isPresent()){
            category.setCategoryName(categoryName);
            return categoryRepository.save(category);
        }
        else {
           throw  new ResourceNotFoundException(categoryName
                    +" Category Is Not Present");
        }

    }

    public void removeCategory(String categoryName){
        Optional<Category> optionalCategory = categoryRepository
                .findByCategoryName(categoryName);
        if (optionalCategory.isPresent()){
            categoryRepository.deleteByCategoryName(categoryName);
        }
        else {
            throw new ResourceNotFoundException(
                    categoryName +" category does not exist");
        }
    }
}
