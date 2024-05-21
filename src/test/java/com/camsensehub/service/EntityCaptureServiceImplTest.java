package com.camsensehub.service;

import com.camsensehub.entity.EntityCapture;
import com.camsensehub.model.response.GenericResponse;
import com.camsensehub.repository.EntityDetectionRepository;
import com.camsensehub.utils.Constants;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.doThrow;
import static org.mockito.Mockito.when;

public class EntityCaptureServiceImplTest {

    @InjectMocks
    private EntityCaptureServiceImpl entityCaptureService;

    @Mock
    private EntityDetectionRepository entityDetectionRepository;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    /* -------------------------------------- Start : getAllDetectedEntites -------------------------------------- */

    @Test
    @Order(1)
    @DisplayName("getAllDetectedEntites - whenSuccess - returnsProcessedOK")
    void getAllDetectedEntites_whenSuccess_returnsProcessedOK() {
        // Arrange
        EntityCapture entity1 = new EntityCapture();
        entity1.setCamera("Camera 1");
        entity1.setEntityDetection(true);
        entity1.setStampDate(new Date());

        EntityCapture entity2 = new EntityCapture();
        entity2.setCamera("Camera 2");
        entity2.setEntityDetection(false);
        entity2.setStampDate(new Date());

        List<EntityCapture> entityList = new ArrayList<>(Arrays.asList(entity1, entity2));

        when(entityDetectionRepository.findAll()).thenReturn(entityList);

        // Act
        GenericResponse<List<EntityCapture>> response = entityCaptureService.getAllDetectedEntites();

        // Assert
        Assertions.assertEquals(Constants.ResponseCodes.OK, response.getResponseCode());
    }

    @Test
    @Order(2)
    @DisplayName("getAllDetectedEntites - whenNoDataPresentInDB - returnsEntityEndpointNotFound")
    void getAllDetectedEntites_whenNoDataPresentInDB_returnsEntityEndpointNotFound() {
        // Arrange
        List<EntityCapture> entityList = new ArrayList<>();
        when(entityDetectionRepository.findAll()).thenReturn(entityList);

        // Act
        GenericResponse<List<EntityCapture>> response = entityCaptureService.getAllDetectedEntites();

        // Assert
        Assertions.assertEquals(Constants.ResponseCodes.ENTITY_ENDPOINT_NOT_FOUND, response.getResponseCode());
    }

    @Test
    @Order(3)
    @DisplayName("getAllDetectedEntites - whenThrownAnyException - returnsUnableToProcess")
    void getAllDetectedEntites_whenThrownAnyException_returnsUnableToProcess() {
        // Arrange
        when(entityDetectionRepository.findAll()).thenThrow(new RuntimeException("Exception"));

        // Act
        GenericResponse<List<EntityCapture>> response = entityCaptureService.getAllDetectedEntites();

        // Assert
        Assertions.assertEquals(Constants.ResponseCodes.UNABLE_TO_PROCESS, response.getResponseCode());
    }

    /* -------------------------------------- End : getAllDetectedEntites -------------------------------------- */

    /* -------------------------------------- Start : findEntityDetectionByCamera -------------------------------------- */

    @Test
    @Order(4)
    @DisplayName("findEntityDetectionByCamera - whenSuccess - returnsProcessedOK")
    void findEntityDetectionByCamera_whenSuccess_returnsProcessedOK() {
        // Arrange
        EntityCapture entity1 = new EntityCapture();
        entity1.setCamera("Camera 1");
        entity1.setEntityDetection(true);
        entity1.setStampDate(new Date());

        EntityCapture entity2 = new EntityCapture();
        entity2.setCamera("Camera 1");
        entity2.setEntityDetection(false);
        entity2.setStampDate(new Date());

        List<EntityCapture> entityList = new ArrayList<>(Arrays.asList(entity1, entity2));

        when(entityDetectionRepository.findByCamera(anyString())).thenReturn(entityList);

        // Act
        GenericResponse<List<EntityCapture>> response = entityCaptureService.findEntityDetectionByCamera("Camera 1");

        // Assert
        Assertions.assertEquals(Constants.ResponseCodes.OK, response.getResponseCode());
    }

    @Test
    @Order(5)
    @DisplayName("findEntityDetectionByCamera - whenNoDataPresentInDB - returnsCameraDetailsNotFound")
    void findEntityDetectionByCamera_whenNoDataPresentInDB_returnsCameraDetailsNotFound() {
        // Arrange
        List<EntityCapture> entityList = new ArrayList<>();
        when(entityDetectionRepository.findByCamera(anyString())).thenReturn(entityList);

        // Act
        GenericResponse<List<EntityCapture>> response = entityCaptureService.findEntityDetectionByCamera("Camera 1");

        // Assert
        Assertions.assertEquals(Constants.ResponseCodes.CAMERA_DETAILS_NOT_FOUND, response.getResponseCode());
    }

    @Test
    @Order(6)
    @DisplayName("findEntityDetectionByCamera - whenThrownAnyException - returnsUnableToProcess")
    void findEntityDetectionByCamera_whenThrownAnyException_returnsUnableToProcess() {
        // Arrange
        when(entityDetectionRepository.findByCamera(anyString())).thenThrow(new RuntimeException("Exception"));

        // Act
        GenericResponse<List<EntityCapture>> response = entityCaptureService.findEntityDetectionByCamera("Camera 1");

        // Assert
        Assertions.assertEquals(Constants.ResponseCodes.UNABLE_TO_PROCESS, response.getResponseCode());
    }

    /* -------------------------------------- End : findEntityDetectionByCamera -------------------------------------- */
}
