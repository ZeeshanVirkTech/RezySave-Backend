package com.camsensehub.repository;

import com.camsensehub.entity.WeatherDetection;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;

import java.util.Date;
import java.util.List;

public interface WeatherDetectionRepository extends MongoRepository<WeatherDetection, String> {
    List<WeatherDetection> findByCamera(String camera);
    List<WeatherDetection> findAll();
}
