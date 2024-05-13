package com.camsensehub.service;

import com.camsensehub.entity.WaterDetection;
import com.camsensehub.model.response.GenericResponse;

import java.util.Date;
import java.util.List;

public interface WaterDetectionService {
    public GenericResponse<List<WaterDetection>> getAllWaterDetection();
    public GenericResponse<List<WaterDetection>> findDetectedWaterByCam(String camera);
}
