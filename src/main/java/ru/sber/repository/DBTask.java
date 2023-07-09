package ru.sber.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.sber.entity.Task;

@Repository
public interface DBTask extends JpaRepository<Task, Long>{

}

