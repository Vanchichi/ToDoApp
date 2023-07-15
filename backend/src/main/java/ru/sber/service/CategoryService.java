package ru.sber.service;
import org.springframework.web.server.ResponseStatusException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.sber.entity.Category;
import ru.sber.repository.CategoryRepository;
import java.util.List;
import java.util.Optional;
import org.springframework.http.HttpStatus;
@Service
public class CategoryService {
   private final CategoryRepository repoCategory;

    @Autowired
    public CategoryService(CategoryRepository repoCategory) {
        this.repoCategory = repoCategory;
    }
    public Optional<Category> getCategoryById(Long id ){

        return repoCategory.findById(id);
    }

    public Long addCategory(Category category){
       return repoCategory.save(category).getId();
    }

   public List<Category> findAllCategory(){
        return  repoCategory.findAll();
   }

   public List<Category> findCategoryByName(String name){
        return repoCategory.findAllByNameContainingIgnoreCase(name);
   }
    public Category updateCategory (Category updatedCategory){
        Category category = repoCategory.findById(updatedCategory.getId())
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Category not found"));
        category.setName(updatedCategory.getName());
        return repoCategory.save(category);
    }

    public boolean deleteCategory(Long id){
        if (repoCategory.findById(id).isEmpty()) {

            return false;
        }
        repoCategory.deleteById(id);
        return true;
    }

    public Category findById (Long id){
        return repoCategory.findById(id).get();
    }
}
