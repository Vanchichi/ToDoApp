package ru.sber.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import lombok.extern.slf4j.Slf4j;
import ru.sber.entity.*;
import ru.sber.service.CategoryService;
import ru.sber.service.TaskService;
import java.net.URI;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Slf4j
@RestController
public class TaskController {
    private TaskService taskService;
    private CategoryService categoryService;

    @Autowired
    public TaskController(TaskService taskService,CategoryService categoryService) {
        this.taskService = taskService;
       this.categoryService = categoryService;
    }

    @PostMapping("task")
    public ResponseEntity<?> addTask (@RequestBody Task todo, @RequestParam("categoryId") Long categoryId) {
        log.info("Добавление задачи");
        return ResponseEntity.created(URI.create("/task" + taskService.createTask(todo,categoryId))).build();
    }

    @GetMapping("/{taskId}")
    public ResponseEntity<?> getTaskById(@PathVariable Long taskId) {
        log.info("Получаем задачу с id: {}", taskId);
        Optional<Task> task = taskService.findTaskById(taskId);
        if (task.isPresent()) {
            return ResponseEntity.ok().body(task.get());
        } else {
            return ResponseEntity.notFound().build();
        }
    }



    @GetMapping("task/cat/")
    public List<Task> getAllTasksByCat(@RequestParam("categoryId") Long id){
        log.info("Вывод задачииииии");
        Category category = categoryService.findById(id);
        List<Task>  tasks = taskService.findByCategory(category);
        return  tasks;
    }

//    @GetMapping("task")
//    public List<Task> getTaskByNameNotArchive (@RequestParam("title") String name) {
//        log.info("Вывод задач по названию  не из архива");
//        return taskService.findTaskByName(name);
//    }

    @GetMapping("task")
    public  List<Task> getAllTasksNotInArchive(){
        log.info("Вывод всех задач");
        return taskService.findAllNotInArchive();
    }

    @PutMapping("task")
    public ResponseEntity<?> update(@RequestBody Task todo) {
        log.info("Обновление задачи");
        boolean update = taskService.updateToDoItem(todo);
        if (update) {
            return ResponseEntity.ok().build();
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @PutMapping("task/status")
    public ResponseEntity<?> updateStatus(@RequestBody Task task) {
        log.info("Обновление статуса");
        boolean changeStatus = taskService.updateStatus(task.getId());
        if (changeStatus) {
            return ResponseEntity.ok().build();
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @PutMapping("task/priority")
    public ResponseEntity<?> updatePriority(@RequestBody Task task) {
        log.info("Обновление приоритета");
        boolean changePriority = taskService.updatePriority(task.getId(), task.getPriority());
        if (changePriority) {
            return ResponseEntity.ok().build();
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @PutMapping("task/category")
    public ResponseEntity<?> updateCategory(@RequestBody Task task) {
        log.info("Обновление категории у задачи");
        boolean changeCategory = taskService.updateCategory(task.getId(), task.getCategory());
        if (changeCategory) {
            return ResponseEntity.ok().build();
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @GetMapping("archive/all")
    public  List<Task> getAllTasksInArchive(){
        log.info("Вывод всех задач в архиве");
        return taskService.findAllInArchive();
    }

    @GetMapping("archive")
    public List<Task> getTaskByNameInArchive (@RequestParam("title") String name) {
        log.info("Вывод задач по названию  в архива");
        return taskService.findTaskByNameInArchive(name);
    }

    @DeleteMapping("archive/in/{id}")
    public ResponseEntity<?> putTaskInArchive(@PathVariable Long id) {
        boolean isDeleted = taskService.setInArchive(id);
        log.info("Удаление задачи по id из списка задач и  перенос в архив", id);
        if (isDeleted) {
            return ResponseEntity.noContent().build();

        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("archive/out/{id}")
    public ResponseEntity<?> getOutTaskArchive(@PathVariable Long id) {
        boolean isDeleted = taskService.getInArchive(id);
        log.info("Добавление задачи из архива", id);
        if (isDeleted) {
            return ResponseEntity.noContent().build();

        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("archive/{id}")
    public ResponseEntity<?> deleteTask(@PathVariable Long id) {
        boolean isDeleted = taskService.deleteToDoItem(id);
        log.info("Удаление задачи по id: {} из списка задач", id);
        if (isDeleted) {
            return ResponseEntity.noContent().build();

        } else {
            return ResponseEntity.notFound().build();
        }
    }


    @GetMapping("task/priorities")
    public ResponseEntity<List<String>> getEnum2List() {
        List<EPriority> enumList = Arrays.asList(EPriority.values());
        List<String> stringList = enumList.stream()
                .map(Enum::toString)
                .collect(Collectors.toList());
        return ResponseEntity.ok().body(stringList);
    }

    @GetMapping("task/regularities")
    public ResponseEntity<List<String>> getEnum3List() {
        List<ERegularity> enumList = Arrays.asList(ERegularity.values());
        List<String> stringList = enumList.stream()
                .map(Enum::toString)
                .collect(Collectors.toList());
        return ResponseEntity.ok().body(stringList);
    }


}