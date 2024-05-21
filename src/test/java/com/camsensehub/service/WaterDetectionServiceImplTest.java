package com.camsensehub.service;

import com.camsensehub.entity.WaterDetection;
import com.camsensehub.model.response.GenericResponse;
import com.camsensehub.repository.WaterDetectionRepository;
import com.camsensehub.utils.Constants;
import org.bson.types.ObjectId;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.data.mongodb.core.MongoDataIntegrityViolationException;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.doThrow;
import static org.mockito.Mockito.when;

public class WaterDetectionServiceImplTest {

    @InjectMocks
    private WaterDetectionServiceImpl waterDetectionService;

    @Mock
    private WaterDetectionRepository waterDetectionRepository;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    @Order(1)
    @DisplayName("getAllWaterDetection - whenSuccess - returnsProcessedOK")
    void getAllWaterDetection_whenSuccess_returnsProcessedOK() {
        // Arrange
        WaterDetection waterDetection = new WaterDetection();
        waterDetection.setCamera(new ObjectId("605c72fffc13ae1426000000"));
        waterDetection.setWaterDetection(true);
        waterDetection.setStampDate(new Date());

        List<WaterDetection> waterDetectionList = new ArrayList<>();
        waterDetectionList.add(waterDetection);

        when(waterDetectionRepository.findAll()).thenReturn(waterDetectionList);

        // Act
        GenericResponse<List<WaterDetection>> response = waterDetectionService.getAllWaterDetection();

        // Assert
        Assertions.assertEquals(Constants.ResponseCodes.OK, response.getResponseCode(), response.getResponseDescription());
    }

    @Test
    @Order(2)
    @DisplayName("getAllWaterDetection - whenNoDataPresentInDB - returnsWaterDetectionNotFound")
    void getAllWaterDetection_whenNoDataPresentInDB_returnsWaterDetectionNotFound() {
        // Arrange
        List<WaterDetection> waterDetectionList = new ArrayList<>();
        when(waterDetectionRepository.findAll()).thenReturn(waterDetectionList);

        // Act
        GenericResponse<List<WaterDetection>> response = waterDetectionService.getAllWaterDetection();

        // Assert
        Assertions.assertEquals(Constants.ResponseCodes.WATER_DETECTIOM_NOT_FOUND, response.getResponseCode(), response.getResponseDescription());
    }

    @Test
    @Order(3)
    @DisplayName("getAllWaterDetection - whenThrownAnyException - returnsUnableToProcess")
    void getAllWaterDetection_whenThrownAnyException_returnsUnableToProcess() {
        // Arrange
        when(waterDetectionRepository.findAll()).thenThrow(new RuntimeException("Exception"));

        // Act
        GenericResponse<List<WaterDetection>> response = waterDetectionService.getAllWaterDetection();

        // Assert
        Assertions.assertEquals(Constants.ResponseCodes.UNABLE_TO_PROCESS, response.getResponseCode(), response.getResponseDescription());
    }

    @Test
    @Order(4)
    @DisplayName("findDetectedWaterByCam - whenSuccess - returnsProcessedOK")
    void findDetectedWaterByCam_whenSuccess_returnsProcessedOK() {
        // Arrange
        WaterDetection waterDetection = new WaterDetection();
        waterDetection.setCamera(new ObjectId("605c72fffc13ae1426000000"));
        waterDetection.setWaterDetection(true);
        waterDetection.setStampDate(new Date());

        List<WaterDetection> waterDetectionList = new ArrayList<>();
        waterDetectionList.add(waterDetection);

        when(waterDetectionRepository.findByCamera(anyString())).thenReturn(waterDetectionList);

        // Act
        GenericResponse<List<WaterDetection>> response = waterDetectionService.findDetectedWaterByCam("605c72fffc13ae1426000000");

        // Assert
        Assertions.assertEquals(Constants.ResponseCodes.OK, response.getResponseCode(), response.getResponseDescription());
    }

    @Test
    @Order(5)
    @DisplayName("findDetectedWaterByCam - whenNoDataPresentInDB - returnsWaterDetectionNotFound")
    void findDetectedWaterByCam_whenNoDataPresentInDB_returnsWaterDetectionNotFound() {
        // Arrange
        List<WaterDetection> waterDetectionList = new ArrayList<>();
        when(waterDetectionRepository.findByCamera(anyString())).thenReturn(waterDetectionList);

        // Act
        GenericResponse<List<WaterDetection>> response = waterDetectionService.findDetectedWaterByCam("605c72fffc13ae1426000000");

        // Assert
        Assertions.assertEquals(Constants.ResponseCodes.WATER_DETECTIOM_NOT_FOUND, response.getResponseCode(), response.getResponseDescription());
    }

    @Test
    @Order(6)
    @DisplayName("findDetectedWaterByCam - whenThrownAnyException - returnsUnableToProcess")
    void findDetectedWaterByCam_whenThrownAnyException_returnsUnableToProcess() {
        // Arrange
        doThrow(new RuntimeException("Exception")).when(waterDetectionRepository).findByCamera(anyString());

        // Act
        GenericResponse<List<WaterDetection>> response = waterDetectionService.findDetectedWaterByCam("605c72fffc13ae1426000000");

        // Assert
        Assertions.assertEquals(Constants.ResponseCodes.UNABLE_TO_PROCESS, response.getResponseCode(), response.getResponseDescription());
    }
}
