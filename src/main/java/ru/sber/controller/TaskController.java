package ru.sber.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import lombok.extern.slf4j.Slf4j;
import ru.sber.entity.EPriority;
import ru.sber.entity.EStatus;
import ru.sber.entity.Task;
import ru.sber.service.TaskService;
import ru.sber.entity.Category;
import java.net.URI;
import java.util.List;

@Slf4j
@RestController
@RequestMapping("task")
public class TaskController {


    private TaskService taskService;

    @Autowired
    public TaskController(TaskService taskService) {
        this.taskService = taskService;
    }

    @PostMapping
    public ResponseEntity<?> addTask(@RequestBody Task todo) {
        log.info("Добавление задачи");
        return ResponseEntity.created(URI.create("/task" + taskService.saveOrUpdateToDoItem(todo))).build();
    }

    @PutMapping
    public ResponseEntity<?>  update(@RequestBody Task todo){
        log.info("Обновление задачи");
        boolean update = taskService.saveOrUpdateToDoItem(todo);
        if (update){
            return ResponseEntity.ok().build();
        } else{
            return ResponseEntity.notFound().build();
        }
    }

    @PutMapping("/status")
    public ResponseEntity<?>  updateStatus( @RequestBody(required = true) Long id_task){
    log.info("Обновление статуса");
    boolean changeStatus = taskService.updateStatus(id_task);
   if(changeStatus){
           return ResponseEntity.ok().build();
        } else{
            return ResponseEntity.notFound().build();
        }
    }

    @PutMapping("/priority")
    public ResponseEntity<?>  updatePriority(@RequestBody Long id_task, @RequestBody EPriority priority){
        log.info("Обновление приоритета");
        boolean changePriority = taskService.updatePriority(id_task,priority);
        if(changePriority){
            return ResponseEntity.ok().build();
        } else{
            return ResponseEntity.notFound().build();
        }
    }

    @PutMapping("/category")
    public ResponseEntity<?>  updateCategory(@RequestBody Long id_task, @RequestBody Category category){
        log.info("Обновление категории");
        boolean changeCategory = taskService.updateCategory(id_task,category);
        if(changeCategory){
            return ResponseEntity.ok().build();
        } else{
            return ResponseEntity.notFound().build();
        }
    }

    @GetMapping
    public List<Task> getTaskByName(@RequestParam String title){
        log.info("Вывод задач по названию ");
        return taskService.findAllTasksByName(title);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteTask(@PathVariable Long id){
        boolean isDeleted = taskService.deleteToDoItem(id);
        log.info("Удаление задачи по id из списка задач",id);
        if (isDeleted) {
            return ResponseEntity.noContent().build();

        } else {
            return ResponseEntity.notFound().build();
        }
    }
    }
