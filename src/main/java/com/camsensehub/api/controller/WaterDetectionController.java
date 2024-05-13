package com.camsensehub.api.controller;

import com.camsensehub.api.ApiController;
import com.camsensehub.entity.WaterDetection;
import com.camsensehub.model.response.GenericResponse;
import com.camsensehub.service.WaterDetectionService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.List;

@RestController
@RequestMapping(path = ApiController.Water_ENDPOINT)
@CrossOrigin(origins = "${allow.origin.endpoint}", allowedHeaders = "*")
public class WaterDetectionController {
    private static final Logger LOG = LoggerFactory.getLogger(WaterDetectionController.class);

    private final WaterDetectionService waterDetectionService;

    public WaterDetectionController(WaterDetectionService waterDetectionService) {
        this.waterDetectionService = waterDetectionService;
    }

    @GetMapping(path = "/allWaterDetection")
    public GenericResponse<List<WaterDetection>> getAllWaterDetection() {
        return waterDetectionService.getAllWaterDetection();
    }
    @GetMapping(path = "/waterDetectionByCam")
    public GenericResponse<List<WaterDetection>> findDetectedWaterByCam(@RequestParam("camera") String camera) {
        return waterDetectionService.findDetectedWaterByCam(camera);
    }
}
