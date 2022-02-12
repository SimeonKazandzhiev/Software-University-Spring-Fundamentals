package com.example.gira.model.entity;


import com.example.gira.model.enums.ClassificationNameEnum;

import javax.persistence.*;

@Entity
@Table(name = "classifications")
public class ClassificationEntity extends BaseEntity{

    private ClassificationNameEnum classificationName;
    private String description;

    public ClassificationEntity() {
    }

    public ClassificationEntity(ClassificationNameEnum name) {
        this.classificationName = name;
    }

    @Enumerated(EnumType.STRING)
    public ClassificationNameEnum getClassificationName() {
        return classificationName;
    }

    public ClassificationEntity setClassificationName(ClassificationNameEnum classificationName) {
        this.classificationName = classificationName;
        return this;
    }

    @Column(name = "description",columnDefinition = "LONGTEXT")
    public String getDescription() {
        return description;
    }

    public ClassificationEntity setDescription(String description) {
        this.description = description;
        return this;
    }
}
