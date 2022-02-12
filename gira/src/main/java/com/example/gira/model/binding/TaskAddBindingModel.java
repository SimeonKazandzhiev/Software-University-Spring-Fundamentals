package com.example.gira.model.binding;

import com.example.gira.model.enums.ClassificationNameEnum;
import org.springframework.format.annotation.DateTimeFormat;

import javax.validation.constraints.FutureOrPresent;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.time.LocalDate;

public class TaskAddBindingModel {

    private String name;
    private String description;
    private LocalDate dueDate;
    private ClassificationNameEnum classification;

    public TaskAddBindingModel() {
    }

    @NotBlank
    @Size(min = 3,max = 20,message = "Name length must be between 3 and 20 characters (inclusive 3 and 20).")
    public String getName() {
        return name;
    }

    public TaskAddBindingModel setName(String name) {
        this.name = name;
        return this;
    }
    @NotBlank
    @Size(min = 5,message = "Description min length must be minimum 5(inclusive) characters")
    public String getDescription() {
        return description;
    }

    public TaskAddBindingModel setDescription(String description) {
        this.description = description;
        return this;
    }
    @FutureOrPresent
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    public LocalDate getDueDate() {
        return dueDate;
    }

    public TaskAddBindingModel setDueDate(LocalDate dueDate) {
        this.dueDate = dueDate;
        return this;
    }
    @NotNull(message = "Classification cannot be null.")
    public ClassificationNameEnum getClassification() {
        return classification;
    }

    public TaskAddBindingModel setClassification(ClassificationNameEnum classification) {
        this.classification = classification;
        return this;
    }
}
