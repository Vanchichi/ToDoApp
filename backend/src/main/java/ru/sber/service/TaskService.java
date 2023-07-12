package ru.sber.service;

import java.util.ArrayList;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.sber.entity.Category;
import ru.sber.entity.EPriority;
import  ru.sber.entity.Task;
import ru.sber.entity.EStatus;
import ru.sber.repository.*;

@Service
public class TaskService {
    private final  TaskRepository repo;
    private final  CategoryService categoryService;

    @Autowired
    public TaskService(TaskRepository repo, CategoryService categoryService) {
        this.repo = repo;
        this.categoryService = categoryService;
    }

    public Task getToDoItemById(Long id) {

        return repo.findById(id).get();
    }

  public boolean updateStatus(Long id) {
      Task todo = getToDoItemById(id);
       EStatus oldStatus = todo.getStatus();

       if (oldStatus == EStatus.COMPLETED )
       {
           todo.setStatus(EStatus.PROCESSED);

       } else if (oldStatus == EStatus.PROCESSED )
       {
           todo.setStatus(EStatus.COMPLETED);
       }
        return updateToDoItem(todo);
    }

    public boolean updatePriority (Long id, EPriority priority){
        Task todo = getToDoItemById(id);
        todo.setPriority(priority);
        return updateToDoItem(todo);
    }

    public boolean updateCategory(Long id, Category category){
        Task todo = getToDoItemById(id);
        categoryService.addCategory(category);
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
        Task updatedObj = repo.save(todo);
        return updatedObj.getId();
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

}
