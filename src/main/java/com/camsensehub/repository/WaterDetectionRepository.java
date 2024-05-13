package com.camsensehub.repository;

import com.camsensehub.entity.WaterDetection;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;

import java.util.Date;
import java.util.List;

public interface WaterDetectionRepository extends MongoRepository<WaterDetection, String> {
    List<WaterDetection> findByCamera(String camera);
    List<WaterDetection> findAll();
}
