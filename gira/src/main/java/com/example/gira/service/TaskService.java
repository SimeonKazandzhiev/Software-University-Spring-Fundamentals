package com.example.gira.service;

import com.example.gira.model.service.TaskServiceModel;
import com.example.gira.model.view.TaskViewModel;

import java.util.List;

public interface TaskService {
    void addTask(TaskServiceModel task);
    List<TaskViewModel> findAllTasks();

    void changeProgress(Long id);
}
