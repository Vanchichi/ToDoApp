package ru.sber.entity;
import java.time.LocalDateTime;


import jakarta.persistence.*;
import org.springframework.format.annotation.DateTimeFormat;

import jakarta.annotation.Nonnull;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;
import lombok.Data;

@Entity
@Table(name = "archive")
@NoArgsConstructor
@AllArgsConstructor
@Data
public class Archive {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "task_id", nullable = false)
    private Task task;

    public Archive(Task task) {
        this.task = task;
    }
}