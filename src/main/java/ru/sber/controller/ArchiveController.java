package ru.sber.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;

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
import ru.sber.service.ArchiveService;
import ru.sber.service.TaskService;
import ru.sber.entity.Category;
import java.net.URI;
import java.util.List;

@Slf4j
@RestController
@RequestMapping("archive")
public class ArchiveController {

    private ArchiveService archiveService;

    @Autowired
    public ArchiveController(ArchiveService archiveService) {
        this.archiveService = archiveService;
    }


    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteTask(@PathVariable Long id){
        boolean isDeleted = archiveService.deleteInArchive(id);
        log.info("Удаление задачи по id из архива");
        if (isDeleted) {
            return ResponseEntity.noContent().build();

        } else {
            return ResponseEntity.notFound().build();
        }
    }
}
