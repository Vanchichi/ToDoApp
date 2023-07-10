package ru.sber.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.stereotype.Repository;
import ru.sber.entity.Archive;
import ru.sber.entity.Category;

import java.util.List;


@Repository
@EnableJpaRepositories
public interface CategoryRepository extends JpaRepository<Category, Long> {

    List<Category> findAllByNameContainingIgnoreCase(String titleCategory);
}