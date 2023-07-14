package ru.sber.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.sber.entity.Category;
import ru.sber.entity.EPriority;
import  ru.sber.entity.Task;
import ru.sber.repository.*;

@Service
public class TaskService {
    private final  TaskRepository repo;
    private final CategoryRepository repoService;

    private final  CategoryService categoryService;

    @Autowired
    public TaskService(TaskRepository repo, CategoryRepository repoService, CategoryService categoryService) {
        this.repo = repo;
        this.repoService = repoService;
        this.categoryService = categoryService;
    }


    public long createTask(Task task, Long categoryId) {
        // Получить категорию по идентификатору
        Optional<Category> optionalCategory = categoryService.getCategoryById(categoryId);
        if (optionalCategory.isPresent()) {
            Category category = optionalCategory.get();
            task.setCategory(category);
        } else {
            // Обработка ситуации, когда категория не найдена
            throw new RuntimeException("Категория не найдена");
        }

        return repo.save(task).getId();
    }
    public Optional<Task> findTaskById(Long taskId) {
        return repo.findById(taskId);
    }
    public Task getToDoItemById(Long id) {

        return repo.findById(id).get();
    }

  public boolean updateStatus(Long id) {
      Task todo = getToDoItemById(id);
       boolean oldStatus = todo.getStatus();
       if (oldStatus == false ) {
           todo.setStatus(true);

       } else if (oldStatus == true) {
           todo.setStatus(false);
       }
        return updateToDoItem(todo);
    }

    public boolean updateTask(Task task) {
        if (task.getId() == null) {
            throw new RuntimeException("Идентификатор задачи не указан");
        }

        Optional<Task> existingTask = repo.findById(task.getId());
        if (!existingTask.isPresent()) {
            throw new RuntimeException("Задача не найдена");
        }

        Task updatedTask = existingTask.get();
        updatedTask.setTitle(task.getTitle());
        updatedTask.setDescription(task.getDescription());

        repo.save(updatedTask);
        return true;
    }


    public boolean updatePriority (Long id, EPriority priority){
        Task todo = getToDoItemById(id);
        todo.setPriority(priority);
        return updateToDoItem(todo);
    }

    public boolean updateCategory(Long id, Category category){
        Task todo = getToDoItemById(id);
        todo.setCategory(category);
        return updateToDoItem(todo);
    }

    public boolean updateToDoItem(Task todo) {
        Task updatedObj = repo.save(todo);

        if (getToDoItemById(updatedObj.getId()) != null) {
            return true;
        }

        return false;
    }

    public Long saveToDoItem(Task todo) {
        Task savedObj = repo.save(todo);
        return savedObj.getId();
    }
    public boolean setInArchive (Long id){
        Task todo = getToDoItemById(id);
        todo.setArchive(true);
        return updateToDoItem(todo);
    }

    public boolean getInArchive (Long id){
        Task todo = getToDoItemById(id);
        todo.setArchive(false);
        return updateToDoItem(todo);
    }

    public List<Task> findAllNotInArchive(){
        return repo.findAllByArchiveFalse();
    }

    public List<Task> findAllInArchive(){
        return repo.findAllByArchiveTrue();
    }

    public List<Task> findTaskByName(String name) {
        List<Task> tasks = new ArrayList<>();

        tasks.addAll(repo.findAllByTitleContainingIgnoreCaseAndArchiveFalse(name));
        tasks.addAll(repo.findAllByDescriptionContainingIgnoreCaseAndArchiveFalse(name));

        return tasks;
    }

    public List<Task> findTaskByNameInArchive(String name) {
        List<Task> tasks = new ArrayList<>();

        tasks.addAll(repo.findAllByTitleContainingIgnoreCaseAndArchiveTrue(name));
        tasks.addAll(repo.findAllByDescriptionContainingIgnoreCaseAndArchiveTrue(name));

        return tasks;
    }

    public boolean deleteToDoItem(Long id) {
        Task todo = getToDoItemById(id);
        repo.deleteById(id);

        if (repo.findById(id).isEmpty()) {
            return true;
        }

        return false;
    }

    public List<Task> findByCategory(Category category){
        return repo.findAllByCategory(category);
    }

}
