package ru.sber.service;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import  ru.sber.entity.Task;
import ru.sber.entity.Group;
import ru.sber.repository.*;


@Service
public class TaskService {

    @Autowired
    DBTask repo;

    public List<Task> getAllToDoItems() {
        ArrayList<Task> todoList = new ArrayList<>();
        repo.findAll().forEach(todo -> todoList.add(todo));

        return todoList;
    }

    public Task getToDoItemById(Long id) {
        return repo.findById(id).get();
    }

    public boolean updateStatus(Long id) {
        Task todo = getToDoItemById(id);
        todo.setStatus("Completed");

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
        repo.deleteById(id);

        if (repo.findById(id).isEmpty()) {
            return true;
        }

        return false;
    }

}
