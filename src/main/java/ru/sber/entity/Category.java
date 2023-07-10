package ru.sber.entity;


import jakarta.annotation.Nonnull;
import jakarta.persistence.*;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "Categories",
        uniqueConstraints = {
                @UniqueConstraint(columnNames = "name")
        })
public class Category {
    @Id
    @Nonnull
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Nonnull
    private String name;

    public Category(String name) {
        this.name = name;
    }
}