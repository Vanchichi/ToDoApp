package ru.sber.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import lombok.extern.slf4j.Slf4j;
import ru.sber.entity.Task;
import ru.sber.service.TaskService;
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
    public ResponseEntity<?> update(@RequestBody Task todo) {
        log.info("Обновление задачи");
        boolean update = taskService.saveOrUpdateToDoItem(todo);
        if (update) {
            return ResponseEntity.ok().build();
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @PutMapping("/status")
    public ResponseEntity<?> updateStatus(@RequestBody Task task) {
        log.info("Обновление статуса");
        boolean changeStatus = taskService.updateStatus(task.getId());
        if (changeStatus) {
            return ResponseEntity.ok().build();
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @PutMapping("/priority")
    public ResponseEntity<?> updatePriority(@RequestBody Task task) {
        log.info("Обновление приоритета");
        boolean changePriority = taskService.updatePriority(task.getId(), task.getPriority());
        if (changePriority) {
            return ResponseEntity.ok().build();
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @PutMapping("/category")
    public ResponseEntity<?> updateCategory(@RequestBody Task task) {
        log.info("Обновление категории");
        boolean changeCategory = taskService.updateCategory(task.getId(), task.getCategory());
        if (changeCategory) {
            return ResponseEntity.ok().build();
        } else {
            return ResponseEntity.notFound().build();
        }
    }


    @GetMapping
    public List<Task> getTaskByNameNotArchive (@RequestParam("title") String name) {
    log.info("Вывод задач по названию  не из архива");
    return taskService.findTaskByName(name);
   }

    @GetMapping("/archive")
    public List<Task> getTaskByNameInArchive (@RequestParam("title") String name) {
        log.info("Вывод задач по названию  в архива");
        return taskService.findTaskByNameInArchive(name);
    }
    @GetMapping("/all")
    public  List<Task> getAllTasksNotInArchive(){
        log.info("Вывод всех задач");
        return taskService.findAllNotInArchive();
    }

    @GetMapping("/archive/all")
    public  List<Task> getAllTasksInArchive(){
        log.info("Вывод всех задач в архиве");
        return taskService.findAllInArchive();
    }
    @DeleteMapping("inArchive/{id}")
    public ResponseEntity<?> putTaskInArchive(@PathVariable Long id) {
        boolean isDeleted = taskService.setInArchive(id);
        log.info("Удаление задачи по id из списка задач и  перенос в архив", id);
        if (isDeleted) {
            return ResponseEntity.noContent().build();

        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("OutArchive/{id}")
    public ResponseEntity<?> getOutTaskArchive(@PathVariable Long id) {
        boolean isDeleted = taskService.getInArchive(id);
        log.info("Добавление задачи из архива", id);
        if (isDeleted) {
            return ResponseEntity.noContent().build();

        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteTask(@PathVariable Long id) {
        boolean isDeleted = taskService.deleteToDoItem(id);
        log.info("Удаление задачи по id: {} из списка задач", id);
        if (isDeleted) {
            return ResponseEntity.noContent().build();

        } else {
            return ResponseEntity.notFound().build();
        }
    }
}