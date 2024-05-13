package com.camsensehub.service;

import com.camsensehub.api.controller.WaterDetectionController;
import com.camsensehub.entity.WaterDetection;
import com.camsensehub.model.response.GenericResponse;
import com.camsensehub.repository.WaterDetectionRepository;
import com.camsensehub.utils.Constants;
import com.camsensehub.utils.UtilMethods;
import com.camsensehub.utils.UtilityFunctions;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Component
public class WaterDetectionServiceImpl implements WaterDetectionService {
    private static final Logger LOG = LoggerFactory.getLogger(WaterDetectionServiceImpl.class);

    private final WaterDetectionRepository waterDetectionRepository;

    public WaterDetectionServiceImpl(WaterDetectionRepository waterDetectionRepository) {
        this.waterDetectionRepository = waterDetectionRepository;
    }

    @Override
    public GenericResponse<List<WaterDetection>> getAllWaterDetection() {
        GenericResponse<List<WaterDetection>> response = new GenericResponse<>();
        List<WaterDetection> detectedMolds = new ArrayList<>();
        try {
            LOG.info("IN - getAllWaterDetection - Date: {}", new Date());
            LOG.info("fetching detectedMolds from DB start");
            detectedMolds = waterDetectionRepository.findAll();
            LOG.info("fetching detectedMolds from DB end");
            if(!detectedMolds.isEmpty()) {
                response = new GenericResponse<>(Constants.ResponseCodes.OK, detectedMolds);
                UtilMethods.logSuccess(response, LOG);
            }
            else {
                response = new GenericResponse<>(Constants.ResponseCodes.WATER_DETECTIOM_NOT_FOUND);
                UtilMethods.logFailed(response, LOG);
            }
        } catch (Exception e) {
            LOG.error("Exception in getAllWaterDetection: {}", e.toString());
            response = new GenericResponse<>(Constants.ResponseCodes.UNABLE_TO_PROCESS);
            UtilMethods.logFailed(response, LOG);
        }

        LOG.info("OUT - getAllWaterDetection - Date: {}", new Date());
        return response;
    }

    @Override
    public GenericResponse<List<WaterDetection>> findDetectedWaterByCam(String camera) {
    GenericResponse<List<WaterDetection>> response = new GenericResponse<>();
    List<WaterDetection> waterDetectedByCam = new ArrayList<>();
    try {
        LOG.info("IN - findWaterDetectedByCam - Date: {}", new Date());
        LOG.info("fetching waterDetectedByCam from DB start");
        waterDetectedByCam = waterDetectionRepository.findByCamera(camera);
        LOG.info("fetching waterDetectedByCam from DB end");

        if(!waterDetectedByCam.isEmpty()) {
            response = new GenericResponse<>(Constants.ResponseCodes.OK, waterDetectedByCam);
            UtilMethods.logSuccess(response, LOG);
        }
        else {
            response = new GenericResponse<>(Constants.ResponseCodes.WATER_DETECTIOM_NOT_FOUND);
            UtilMethods.logFailed(response, LOG);
        }
    } catch (Exception e) {
        LOG.error("Exception in findWaterDetectedByCam: {}", e.toString());
        response = new GenericResponse<>(Constants.ResponseCodes.UNABLE_TO_PROCESS);
        UtilMethods.logFailed(response, LOG);
    }

    LOG.info("OUT - findWaterDetectedByCam - Date: {}", new Date());
    return response;
  }
}