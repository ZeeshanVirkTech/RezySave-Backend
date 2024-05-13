package com.camsensehub.service;

import com.camsensehub.entity.EntityCapture;
import com.camsensehub.model.response.GenericResponse;

import java.util.Date;
import java.util.List;

public interface EntityCaptureService {
    public GenericResponse<List<EntityCapture>> getAllDetectedEntites();
    public GenericResponse<List<EntityCapture>> findEntityDetectionByCamera(String camera);

    // public GenericResponse<List<EntityCapture>> getCameraDetailsByLocationAndStampDateBetween(String location, String startDate, String endDate);

    // public GenericResponse<List<EntityCapture>> findFireDetectorCameraDetails();
}
