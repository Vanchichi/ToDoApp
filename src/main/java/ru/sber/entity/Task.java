package ru.sber.entity;


import java.util.Date;

import org.springframework.format.annotation.DateTimeFormat;

import jakarta.annotation.Nonnull;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
@Entity
@Table (name="tasks")
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
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date date;

    @Column
    @Nonnull
    private String status;



    private Long id_category;

    private  Long id_group;
    public Task() {

    }
}