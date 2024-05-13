package com.camsensehub.service;

import com.camsensehub.api.controller.WeatherDetectionController;
import com.camsensehub.entity.WeatherDetection;
import com.camsensehub.model.response.GenericResponse;
import com.camsensehub.repository.WeatherDetectionRepository;
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
public class WeatherDetectionServiceImpl implements WeatherDetectionService {
    private static final Logger LOG = LoggerFactory.getLogger(WeatherDetectionServiceImpl.class);

    private final WeatherDetectionRepository weatherDetectionRepository;

    public WeatherDetectionServiceImpl(WeatherDetectionRepository weatherDetectionRepository) {
        this.weatherDetectionRepository = weatherDetectionRepository;
    }

    @Override
    public GenericResponse<List<WeatherDetection>> getAllWeatherDetection() {
        GenericResponse<List<WeatherDetection>> response = new GenericResponse<>();
        List<WeatherDetection> detectedMolds = new ArrayList<>();
        try {
            LOG.info("IN - getAllWeatherDetection - Date: {}", new Date());
            LOG.info("fetching detectedMolds from DB start");
            detectedMolds = weatherDetectionRepository.findAll();
            LOG.info("fetching detectedMolds from DB end");
            if(!detectedMolds.isEmpty()) {
                response = new GenericResponse<>(Constants.ResponseCodes.OK, detectedMolds);
                UtilMethods.logSuccess(response, LOG);
            }
            else {
                response = new GenericResponse<>(Constants.ResponseCodes.WEATHER_DETECTIOM_NOT_FOUND);
                UtilMethods.logFailed(response, LOG);
            }
        } catch (Exception e) {
            LOG.error("Exception in getAllWeatherDetection: {}", e.toString());
            response = new GenericResponse<>(Constants.ResponseCodes.UNABLE_TO_PROCESS);
            UtilMethods.logFailed(response, LOG);
        }

        LOG.info("OUT - getAllWeatherDetection - Date: {}", new Date());
        return response;
    }

    @Override
    public GenericResponse<List<WeatherDetection>> findDetectedWeatherByCam(String camera) {
    GenericResponse<List<WeatherDetection>> response = new GenericResponse<>();
    List<WeatherDetection> weatherDetectedByCam = new ArrayList<>();
    try {
        LOG.info("IN - findDetectedWeatherByCam - Date: {}", new Date());
        LOG.info("fetching weatherDetectedByCam from DB start");
        weatherDetectedByCam = weatherDetectionRepository.findByCamera(camera);
        LOG.info("fetching weatherDetectedByCam from DB end");

        if(!weatherDetectedByCam.isEmpty()) {
            response = new GenericResponse<>(Constants.ResponseCodes.OK, weatherDetectedByCam);
            UtilMethods.logSuccess(response, LOG);
        }
        else {
            response = new GenericResponse<>(Constants.ResponseCodes.WEATHER_DETECTIOM_NOT_FOUND);
            UtilMethods.logFailed(response, LOG);
        }
    } catch (Exception e) {
        LOG.error("Exception in findDetectedWeatherByCam: {}", e.toString());
        response = new GenericResponse<>(Constants.ResponseCodes.UNABLE_TO_PROCESS);
        UtilMethods.logFailed(response, LOG);
    }

    LOG.info("OUT - findDetectedWeatherByCam - Date: {}", new Date());
    return response;
  }
}