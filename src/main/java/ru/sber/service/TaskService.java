package ru.sber.service;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

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

    private final  ArchiveService archiveService;

    @Autowired
    public TaskService(TaskRepository repo, CategoryService categoryService, ArchiveService archiveService) {
        this.repo = repo;
        this.categoryService = categoryService;
        this.archiveService = archiveService;
    }

    public List<Task> findAllTasksByName(String titleTask) {
        return repo.findAllByTitleContainingIgnoreCase(titleTask);
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
        return saveOrUpdateToDoItem(todo);
    }

    public boolean updatePriority (Long id, EPriority priority){
        Task todo = getToDoItemById(id);
        todo.setPriority(priority);
        return saveOrUpdateToDoItem(todo);
    }

    public boolean updateCategory(Long id, Category category){
        Task todo = getToDoItemById(id);
        categoryService.addCategory(category);
        todo.setCategory(category);
        return saveOrUpdateToDoItem(todo);
    }

    public boolean saveOrUpdateToDoItem(Task todo) {
        Task updatedObj = repo.save(todo);

        if (getToDoItemById(updatedObj.getId()) != null) {
            return true;
        }

        return false;
    }

    public boolean deleteToDoItem(Long id) {
        Task todo = getToDoItemById(id);
        Long id_archive = archiveService.addToArchive(todo);
        repo.deleteById(id);

        if (repo.findById(id).isEmpty()) {
            return true;
        }

        return false;
    }

}
