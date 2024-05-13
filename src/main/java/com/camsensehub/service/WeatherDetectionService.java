package com.camsensehub.service;

import com.camsensehub.entity.WeatherDetection;
import com.camsensehub.model.response.GenericResponse;

import java.util.Date;
import java.util.List;

public interface WeatherDetectionService {
    public GenericResponse<List<WeatherDetection>> getAllWeatherDetection();
    public GenericResponse<List<WeatherDetection>> findDetectedWeatherByCam(String camera);
}
