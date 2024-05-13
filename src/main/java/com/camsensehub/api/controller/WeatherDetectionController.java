package com.camsensehub.api.controller;

import com.camsensehub.api.ApiController;
import com.camsensehub.entity.WeatherDetection;
import com.camsensehub.model.response.GenericResponse;
import com.camsensehub.service.WeatherDetectionService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.List;

@RestController
@RequestMapping(path = ApiController.Water_ENDPOINT)
@CrossOrigin(origins = "${allow.origin.endpoint}", allowedHeaders = "*")
public class WeatherDetectionController{
    private static final Logger LOG = LoggerFactory.getLogger(WeatherDetectionController.class);

    private final WeatherDetectionService weatherDetectionService;

    public WeatherDetectionController(WeatherDetectionService weatherDetectionService) {
        this.weatherDetectionService = weatherDetectionService;
    }

    @GetMapping(path = "/allWeatherDetection")
    public GenericResponse<List<WeatherDetection>> getAllWeatherDetection() {
        return weatherDetectionService.getAllWeatherDetection();
    }
    @GetMapping(path = "/weatherDetectionByCam")
    public GenericResponse<List<WeatherDetection>> findDetectedWeatherByCam(@RequestParam("camera") String camera) {
        return weatherDetectionService.findDetectedWeatherByCam(camera);
    }
}
