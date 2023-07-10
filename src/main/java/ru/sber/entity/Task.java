package ru.sber.entity;


import java.time.LocalDateTime;


import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.persistence.*;
import org.springframework.format.annotation.DateTimeFormat;

import jakarta.annotation.Nonnull;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table (name="Tasks")
public class Task {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Nonnull
    private Long id;

    @Column
    @Nonnull
    private String title;

    @Column
    @Nonnull
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime date;

    @Column
    @Nonnull
    @Enumerated(EnumType.STRING)
    private EStatus status;

    @Column
    @Nonnull
    @Enumerated(EnumType.STRING)
    private EPriority priority;


    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_category", nullable = false)
    private Category category;


    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_user", nullable = false)
    private User user;

}