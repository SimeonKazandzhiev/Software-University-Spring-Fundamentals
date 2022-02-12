package com.example.gira.service.impl;

import com.example.gira.model.entity.TaskEntity;
import com.example.gira.model.enums.ProgressNameEnum;
import com.example.gira.model.service.TaskServiceModel;
import com.example.gira.model.view.TaskViewModel;
import com.example.gira.repository.TaskRepository;
import com.example.gira.security.CurrentUser;
import com.example.gira.service.ClassificationService;
import com.example.gira.service.TaskService;
import com.example.gira.service.UserService;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class TaskServiceImpl implements TaskService {

    private final ModelMapper modelMapper;
    private final TaskRepository taskRepository;
    private final ClassificationService classificationService;
    private final UserService userService;
    private final CurrentUser currentUser;

    public TaskServiceImpl(TaskRepository taskRepository, ModelMapper modelMapper, ClassificationService classificationService, UserService userService, CurrentUser currentUser) {
        this.taskRepository = taskRepository;
        this.modelMapper = modelMapper;
        this.classificationService = classificationService;
        this.userService = userService;
        this.currentUser = currentUser;
    }


    @Override
    public void addTask(TaskServiceModel task) {
        TaskEntity taskEntity = modelMapper.map(task, TaskEntity.class);
        taskEntity
                .setName(task.getName())
                .setDescription(task.getDescription())
                .setDueDate(task.getDueDate())
                .setProgress(ProgressNameEnum.OPEN)
                .setClassification(classificationService.findBy(task.getClassification()));
        if (currentUser.getId() != null) {
            taskEntity.setUser(userService.findById(currentUser.getId()));
        }
        taskRepository.save(taskEntity);
    }

    @Override
    public List<TaskViewModel> findAllTasks() {
        return taskRepository.findAll()
                .stream()
                .map(taskEntity -> modelMapper.map(taskEntity, TaskViewModel.class)).collect(Collectors.toList());
    }

    @Override
    public void changeProgress(Long id) {
        TaskEntity task = taskRepository.findById(id).orElse(null);

        if (task != null) {
            if (task.getProgress() == ProgressNameEnum.COMPLETED || task.getProgress() == ProgressNameEnum.OTHER) {
                taskRepository.delete(task);
            } else {
                if (task.getProgress() == ProgressNameEnum.OPEN) {
                    task.setProgress(ProgressNameEnum.IN_PROGRESS);
                } else if (task.getProgress() == ProgressNameEnum.IN_PROGRESS) {
                    task.setProgress(ProgressNameEnum.COMPLETED);
                }

                taskRepository.save(task);
            }
        }
    }
}
