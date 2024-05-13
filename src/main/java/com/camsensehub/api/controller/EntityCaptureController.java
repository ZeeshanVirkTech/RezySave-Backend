package com.camsensehub.api.controller;

import com.camsensehub.api.ApiController;
import com.camsensehub.entity.EntityCapture;
import com.camsensehub.model.response.GenericResponse;
import com.camsensehub.service.EntityCaptureService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.List;

@RestController
@RequestMapping(path = ApiController.Entity_ENDPOINT)
@CrossOrigin(origins = "${allow.origin.endpoint}", allowedHeaders = "*")
public class EntityCaptureController {
    private static final Logger LOG = LoggerFactory.getLogger(EntityCaptureController.class);

    private final EntityCaptureService entityCaptureService;

    public EntityCaptureController(EntityCaptureService entityCaptureService) {
        this.entityCaptureService = entityCaptureService;
    }

    @GetMapping(path = "/getAllCapturedEntites")
    public GenericResponse<List<EntityCapture>> getAllDetectedEntites() {
        return entityCaptureService.getAllDetectedEntites();
    }
    @GetMapping(path = "/entityDetectionByCamera")
    public GenericResponse<List<EntityCapture>> findEntityDetectionByCamera(@RequestParam("camera") String camera) {
        return entityCaptureService.findEntityDetectionByCamera(camera);
    }
}
