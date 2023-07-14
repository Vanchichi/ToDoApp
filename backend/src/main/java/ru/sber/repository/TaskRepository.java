package ru.sber.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.stereotype.Repository;
import ru.sber.entity.Category;
import ru.sber.entity.Task;
import java.util.List;

@Repository
@EnableJpaRepositories
public interface TaskRepository extends JpaRepository<Task, Long>{

    List<Task> findAllByCategory(Category category);
    List<Task> findAllByTitleContainingIgnoreCaseAndArchiveFalse(String title);
    List<Task> findAllByDescriptionContainingIgnoreCaseAndArchiveFalse(String description);
    List<Task> findAllByTitleContainingIgnoreCaseAndArchiveTrue(String title);
    List<Task> findAllByDescriptionContainingIgnoreCaseAndArchiveTrue(String description);
    List<Task> findAllByArchiveFalse();
    List<Task> findAllByArchiveTrue();
    }
