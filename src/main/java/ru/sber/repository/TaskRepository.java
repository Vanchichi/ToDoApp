package ru.sber.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.stereotype.Repository;
import ru.sber.entity.Task;

import java.util.List;

@Repository
@EnableJpaRepositories
public interface TaskRepository extends JpaRepository<Task, Long>{


    List<Task> findAllByTitleContainingIgnoreCase(String title);

}