package com.camsensehub.repository;

import com.camsensehub.entity.EntityCapture;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;

import java.util.Date;
import java.util.List;

public interface EntityDetectionRepository extends MongoRepository<EntityCapture, String> {
    List<EntityCapture> findByCamera(String camera);
    List<EntityCapture> findAll();
}
