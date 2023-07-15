package ru.sber.entity;

import java.time.LocalDateTime;
import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.persistence.*;
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
    private Long id;

    @Column(nullable = false)
    private String title;

    @Column
    private String description;

    @Column
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime date;

    @Column
    private boolean status;

    @Column
    @Enumerated(EnumType.STRING)
    private EPriority priority;

    @Column(nullable = false)
    private boolean archive;

   @Column
   @Enumerated(EnumType.STRING)
    private ERegularity regularity;

    @ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinColumn(name = "id_category", nullable = false)
    private Category category;

    public boolean getStatus() {
        return status;
    }
}