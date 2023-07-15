package ru.sber.controller;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import lombok.extern.slf4j.Slf4j;
import ru.sber.entity.Category;
import ru.sber.service.CategoryService;
import java.net.URI;
import java.util.List;
import java.util.Optional;

@Slf4j
@RestController
@RequestMapping("/category")
public class CategoryController {
    private CategoryService categoryService;

    @Autowired
    public CategoryController(CategoryService categoryService) {
        this.categoryService = categoryService;
    }

    @PostMapping
    public ResponseEntity<?> addCategory(@RequestBody Category category) {
        log.info("Добавление категории");
        return ResponseEntity.created(URI.create("/category" + categoryService.addCategory(category))).build();
    }

    @GetMapping
    public List<Category> allCategory (){
        log.info("Вывод всех категорий");
        return categoryService.findAllCategory();
    }


  @GetMapping("/search")
    public List<Category> search  (@RequestParam("name") String name) {
        log.info("Вывод категорий  по имени");
        return categoryService.findCategoryByName(name);
    };


    @PutMapping("/{categoryId}")
    public ResponseEntity<Category> updateCategory(@PathVariable long categoryId, @RequestBody Category category) {
        Optional<Category> optionalCategory = categoryService.getCategoryById(categoryId);
        log.info("Категория с id: {} изменена", categoryId);
        if (optionalCategory.isEmpty()) {
            return ResponseEntity.notFound().build();
        }

        Category existingCategory = optionalCategory.get();
        existingCategory.setName(category.getName());

        Category updatedCategory = categoryService.updateCategory(existingCategory);
        return ResponseEntity.ok(updatedCategory);
    }


    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteCategory(@PathVariable Long id) {
        boolean isDeleted = categoryService.deleteCategory(id);
        log.info("Удаление категории");
        if (isDeleted) {
            return ResponseEntity.noContent().build();

        } else {
            return ResponseEntity.notFound().build();
        }
    }
    }