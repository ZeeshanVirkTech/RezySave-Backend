package com.camsensehub.repository;

import com.camsensehub.entity.MoldDetection;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;

import java.util.Date;
import java.util.List;

public interface MoldDetectionRepository extends MongoRepository<MoldDetection, String> {
    List<MoldDetection> findByCamera(String camera);
    List<MoldDetection> findAll();
}
