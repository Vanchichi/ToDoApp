package ru.sber.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.sber.entity.Category;
import ru.sber.repository.CategoryRepository;
import java.util.List;

@Service
public class CategoryService {

   private final CategoryRepository repoCategory;

    @Autowired
    public CategoryService(CategoryRepository repoCategory) {
        this.repoCategory = repoCategory;
    }
    public Category getCategoryById(Long id ){
        return repoCategory.findById(id).get();
    }

    public Long addCategory(Category category){
       return repoCategory.save(category).getId();
    }

    public List<Category> findAllTasksByName(String title) {
        return repoCategory.findAllByNameContainingIgnoreCase(title);
    }

    public boolean deleteCategory(Long id){
        Category category = getCategoryById(id);
        if (repoCategory.findById(id).isEmpty()) {
            return true;
        }
        return false;
    }
}
