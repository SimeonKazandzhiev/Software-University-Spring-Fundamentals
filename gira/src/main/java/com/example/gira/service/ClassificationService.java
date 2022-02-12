package com.example.gira.service;

import com.example.gira.model.entity.ClassificationEntity;
import com.example.gira.model.enums.ClassificationNameEnum;

public interface ClassificationService {
    void initClassifications();

    ClassificationEntity findBy(ClassificationNameEnum classification);
}
