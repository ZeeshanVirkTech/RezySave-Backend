package com.camsensehub.service;

import com.camsensehub.api.controller.MoldDetectionController;
import com.camsensehub.entity.MoldDetection;
import com.camsensehub.model.response.GenericResponse;
import com.camsensehub.repository.MoldDetectionRepository;
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
public class MoldDetectionServiceImpl implements MoldDetectionService {
    private static final Logger LOG = LoggerFactory.getLogger(MoldDetectionServiceImpl.class);

    private final MoldDetectionRepository moldDetectionRepository;

    public MoldDetectionServiceImpl(MoldDetectionRepository moldDetectionRepository) {
        this.moldDetectionRepository = moldDetectionRepository;
    }

    @Override
    public GenericResponse<List<MoldDetection>> getAllDetectedMolds() {
        GenericResponse<List<MoldDetection>> response = new GenericResponse<>();
        List<MoldDetection> detectedMolds = new ArrayList<>();
        try {
            LOG.info("IN - getAllDetectedMolds - Date: {}", new Date());
            LOG.info("fetching detectedMolds from DB start");
            detectedMolds = moldDetectionRepository.findAll();
            LOG.info("fetching detectedMolds from DB end");
            if(!detectedMolds.isEmpty()) {
                response = new GenericResponse<>(Constants.ResponseCodes.OK, detectedMolds);
                UtilMethods.logSuccess(response, LOG);
            }
            else {
                response = new GenericResponse<>(Constants.ResponseCodes.MOLD_DETECTIOM_ENDPOINT_NOT_FOUND);
                UtilMethods.logFailed(response, LOG);
            }
        } catch (Exception e) {
            LOG.error("Exception in getAllDetectedMolds: {}", e.toString());
            response = new GenericResponse<>(Constants.ResponseCodes.UNABLE_TO_PROCESS);
            UtilMethods.logFailed(response, LOG);
        }

        LOG.info("OUT - getAllDetectedMolds - Date: {}", new Date());
        return response;
    }

    @Override
    public GenericResponse<List<MoldDetection>> findDetectedMoldByCam(String camera) {
    GenericResponse<List<MoldDetection>> response = new GenericResponse<>();
    List<MoldDetection> moldDetectedByCame = new ArrayList<>();
    try {
        LOG.info("IN - findMoldDetectedByCam - Date: {}", new Date());
        LOG.info("fetching moldDetectedByCame from DB start");
        moldDetectedByCame = moldDetectionRepository.findByCamera(camera);
        LOG.info("fetching moldDetectedByCame from DB end");

        if(!moldDetectedByCame.isEmpty()) {
            response = new GenericResponse<>(Constants.ResponseCodes.OK, moldDetectedByCame);
            UtilMethods.logSuccess(response, LOG);
        }
        else {
            response = new GenericResponse<>(Constants.ResponseCodes.CAMERA_DETAILS_NOT_FOUND);
            UtilMethods.logFailed(response, LOG);
        }
    } catch (Exception e) {
        LOG.error("Exception in findMoldDetectedByCam: {}", e.toString());
        response = new GenericResponse<>(Constants.ResponseCodes.UNABLE_TO_PROCESS);
        UtilMethods.logFailed(response, LOG);
    }

    LOG.info("OUT - findMoldDetectedByCam - Date: {}", new Date());
    return response;
  }
}