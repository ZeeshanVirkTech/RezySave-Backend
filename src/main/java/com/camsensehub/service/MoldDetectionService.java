package com.camsensehub.service;

import com.camsensehub.entity.MoldDetection;
import com.camsensehub.model.response.GenericResponse;

import java.util.Date;
import java.util.List;

public interface MoldDetectionService {
    public GenericResponse<List<MoldDetection>> getAllDetectedMolds();
    public GenericResponse<List<MoldDetection>> findDetectedMoldByCam(String camera);
}
