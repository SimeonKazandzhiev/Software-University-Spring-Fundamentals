package com.example.gira.model.view;

import com.example.gira.model.entity.ClassificationEntity;
import com.example.gira.model.entity.UserEntity;
import com.example.gira.model.enums.ProgressNameEnum;

import java.time.LocalDate;

public class TaskViewModel {

    private Long id;
    private String name;
    private UserEntity user;
    private ClassificationEntity classification;
    private LocalDate dueDate;
    private ProgressNameEnum progress;

    public TaskViewModel() {
    }

    public Long getId() {
        return id;
    }

    public TaskViewModel setId(Long id) {
        this.id = id;
        return this;
    }

    public String getName() {
        return name;
    }

    public TaskViewModel setName(String name) {
        this.name = name;
        return this;
    }

    public UserEntity getUser() {
        return user;
    }

    public TaskViewModel setUser(UserEntity user) {
        this.user = user;
        return this;
    }

    public ClassificationEntity getClassification() {
        return classification;
    }

    public TaskViewModel setClassification(ClassificationEntity classification) {
        this.classification = classification;
        return this;
    }

    public LocalDate getDueDate() {
        return dueDate;
    }

    public TaskViewModel setDueDate(LocalDate dueDate) {
        this.dueDate = dueDate;
        return this;
    }

    public ProgressNameEnum getProgress() {
        return progress;
    }

    public TaskViewModel setProgress(ProgressNameEnum progress) {
        this.progress = progress;
        return this;
    }
}
