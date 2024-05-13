package com.camsensehub.service;

import com.camsensehub.api.controller.EntityCaptureController;
import com.camsensehub.entity.EntityCapture;
import com.camsensehub.model.response.GenericResponse;
import com.camsensehub.repository.EntityDetectionRepository;
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
public class EntityCaptureServiceImpl implements EntityCaptureService {
    private static final Logger LOG = LoggerFactory.getLogger(EntityCaptureServiceImpl.class);

    private final EntityDetectionRepository entityDetectionRepository;

    public EntityCaptureServiceImpl(EntityDetectionRepository entityDetectionRepository) {
        this.entityDetectionRepository = entityDetectionRepository;
    }

    @Override
    public GenericResponse<List<EntityCapture>> getAllDetectedEntites() {
        GenericResponse<List<EntityCapture>> response = new GenericResponse<>();
        List<EntityCapture> capturedEntityDetails = new ArrayList<>();
        try {
            LOG.info("IN - getAllDetectedEntites - Date: {}", new Date());
            LOG.info("fetching capturedEntityDetails from DB start");
            capturedEntityDetails = entityDetectionRepository.findAll();
            LOG.info("fetching capturedEntityDetails from DB end");
            if(!capturedEntityDetails.isEmpty()) {
                response = new GenericResponse<>(Constants.ResponseCodes.OK, capturedEntityDetails);
                UtilMethods.logSuccess(response, LOG);
            }
            else {
                response = new GenericResponse<>(Constants.ResponseCodes.ENTITY_ENDPOINT_NOT_FOUND);
                UtilMethods.logFailed(response, LOG);
            }
        } catch (Exception e) {
            LOG.error("Exception in getAllDetectedEntites: {}", e.toString());
            response = new GenericResponse<>(Constants.ResponseCodes.UNABLE_TO_PROCESS);
            UtilMethods.logFailed(response, LOG);
        }

        LOG.info("OUT - getAllDetectedEntites - Date: {}", new Date());
        return response;
    }

    @Override
    public GenericResponse<List<EntityCapture>> findEntityDetectionByCamera(String camera) {
    GenericResponse<List<EntityCapture>> response = new GenericResponse<>();
    List<EntityCapture> allEntityDetailsDetectedByCamDetails = new ArrayList<>();
    try {
        LOG.info("IN - findEntitiesDetectedByCamera - Date: {}", new Date());
        LOG.info("fetching allEntityDetailsDetectedByCamDetails from DB start");
        allEntityDetailsDetectedByCamDetails = entityDetectionRepository.findByCamera(camera);
        LOG.info("fetching allEntityDetailsDetectedByCamDetails from DB end");

        if(!allEntityDetailsDetectedByCamDetails.isEmpty()) {
            response = new GenericResponse<>(Constants.ResponseCodes.OK, allEntityDetailsDetectedByCamDetails);
            UtilMethods.logSuccess(response, LOG);
        }
        else {
            response = new GenericResponse<>(Constants.ResponseCodes.CAMERA_DETAILS_NOT_FOUND);
            UtilMethods.logFailed(response, LOG);
        }
    } catch (Exception e) {
        LOG.error("Exception in findEntitiesDetectedByCamera: {}", e.toString());
        response = new GenericResponse<>(Constants.ResponseCodes.UNABLE_TO_PROCESS);
        UtilMethods.logFailed(response, LOG);
    }

    LOG.info("OUT - findEntitiesDetectedByCamera - Date: {}", new Date());
    return response;
  }
}