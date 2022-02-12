package com.example.gira.model.entity;


import com.example.gira.model.enums.ProgressNameEnum;

import javax.persistence.*;
import java.time.LocalDate;

@Entity
@Table(name = "tasks")
public class TaskEntity extends BaseEntity{

    private String name;
    private String description;
    private ProgressNameEnum progress;
    private LocalDate dueDate;
    private ClassificationEntity classification;
    private UserEntity user;

    public TaskEntity() {
    }

    @Column(name = "name", unique = true,nullable = false)
    public String getName() {
        return name;
    }

    public TaskEntity setName(String name) {
        this.name = name;
        return this;
    }

    @Column(name = "description",nullable = false)
    public String getDescription() {
        return description;
    }

    public TaskEntity setDescription(String description) {
        this.description = description;
        return this;
    }

    @Enumerated(EnumType.STRING)
    public ProgressNameEnum getProgress() {
        return progress;
    }

    public TaskEntity setProgress(ProgressNameEnum progress) {
        this.progress = progress;
        return this;
    }
    @Column(name = "due_date",nullable = false)
    public LocalDate getDueDate() {
        return dueDate;
    }

    public TaskEntity setDueDate(LocalDate dueDate) {
        this.dueDate = dueDate;
        return this;
    }
    @ManyToOne
    public ClassificationEntity getClassification() {
        return classification;
    }

    public TaskEntity setClassification(ClassificationEntity classification) {
        this.classification = classification;
        return this;
    }
    @ManyToOne
    public UserEntity getUser() {
        return user;
    }

    public TaskEntity setUser(UserEntity user) {
        this.user = user;
        return this;
    }
}
