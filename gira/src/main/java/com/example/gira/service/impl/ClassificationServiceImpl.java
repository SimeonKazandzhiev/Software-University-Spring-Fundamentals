package com.example.gira.service.impl;

import com.example.gira.model.entity.ClassificationEntity;
import com.example.gira.model.enums.ClassificationNameEnum;
import com.example.gira.repository.ClassificationRepository;
import com.example.gira.service.ClassificationService;
import org.springframework.stereotype.Service;

@Service
public class ClassificationServiceImpl implements ClassificationService {

    private final ClassificationRepository classificationRepository;

    public ClassificationServiceImpl(ClassificationRepository classificationRepository) {
        this.classificationRepository = classificationRepository;
    }


    @Override
    public void initClassifications() {
        if(classificationRepository.count() == 0){
            classificationRepository.save(new ClassificationEntity(ClassificationNameEnum.BUG));
            classificationRepository.save(new ClassificationEntity(ClassificationNameEnum.FEATURE));
            classificationRepository.save(new ClassificationEntity(ClassificationNameEnum.SUPPORT));
            classificationRepository.save(new ClassificationEntity(ClassificationNameEnum.OTHER));

        }
    }

    @Override
    public ClassificationEntity findBy(ClassificationNameEnum classification) {
        return classificationRepository.findByClassificationName(classification).orElse(null);
    }
}
