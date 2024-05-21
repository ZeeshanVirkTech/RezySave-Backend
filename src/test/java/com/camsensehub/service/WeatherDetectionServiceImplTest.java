package com.camsensehub.service;

import com.camsensehub.entity.WeatherDetection;
import com.camsensehub.model.response.GenericResponse;
import com.camsensehub.repository.WeatherDetectionRepository;
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

public class WeatherDetectionServiceImplTest {

    @InjectMocks
    private WeatherDetectionServiceImpl weatherDetectionService;

    @Mock
    private WeatherDetectionRepository weatherDetectionRepository;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    @Order(1)
    @DisplayName("getAllWeatherDetection - whenSuccess - returnsProcessedOK")
    void getAllWeatherDetection_whenSuccess_returnsProcessedOK() {
        // Arrange
        WeatherDetection weatherDetection = new WeatherDetection();
        weatherDetection.setCamera(new ObjectId("605c72fffc13ae1426000000"));
        weatherDetection.setWeatherDetection(true);
        weatherDetection.setStampDate(new Date());

        List<WeatherDetection> weatherDetectionList = new ArrayList<>();
        weatherDetectionList.add(weatherDetection);

        when(weatherDetectionRepository.findAll()).thenReturn(weatherDetectionList);

        // Act
        GenericResponse<List<WeatherDetection>> response = weatherDetectionService.getAllWeatherDetection();

        // Assert
        Assertions.assertEquals(Constants.ResponseCodes.OK, response.getResponseCode(), response.getResponseDescription());
    }

    @Test
    @Order(2)
    @DisplayName("getAllWeatherDetection - whenNoDataPresentInDB - returnsWeatherDetectionNotFound")
    void getAllWeatherDetection_whenNoDataPresentInDB_returnsWeatherDetectionNotFound() {
        // Arrange
        List<WeatherDetection> weatherDetectionList = new ArrayList<>();
        when(weatherDetectionRepository.findAll()).thenReturn(weatherDetectionList);

        // Act
        GenericResponse<List<WeatherDetection>> response = weatherDetectionService.getAllWeatherDetection();

        // Assert
        Assertions.assertEquals(Constants.ResponseCodes.WEATHER_DETECTIOM_NOT_FOUND, response.getResponseCode(), response.getResponseDescription());
    }

    @Test
    @Order(3)
    @DisplayName("getAllWeatherDetection - whenThrownAnyException - returnsUnableToProcess")
    void getAllWeatherDetection_whenThrownAnyException_returnsUnableToProcess() {
        // Arrange
        when(weatherDetectionRepository.findAll()).thenThrow(new RuntimeException("Exception"));

        // Act
        GenericResponse<List<WeatherDetection>> response = weatherDetectionService.getAllWeatherDetection();

        // Assert
        Assertions.assertEquals(Constants.ResponseCodes.UNABLE_TO_PROCESS, response.getResponseCode(), response.getResponseDescription());
    }

    @Test
    @Order(4)
    @DisplayName("findDetectedWeatherByCam - whenSuccess - returnsProcessedOK")
    void findDetectedWeatherByCam_whenSuccess_returnsProcessedOK() {
        // Arrange
        WeatherDetection weatherDetection = new WeatherDetection();
        weatherDetection.setCamera(new ObjectId("605c72fffc13ae1426000000"));
        weatherDetection.setWeatherDetection(true);
        weatherDetection.setStampDate(new Date());

        List<WeatherDetection> weatherDetectionList = new ArrayList<>();
        weatherDetectionList.add(weatherDetection);

        when(weatherDetectionRepository.findByCamera(anyString())).thenReturn(weatherDetectionList);

        // Act
        GenericResponse<List<WeatherDetection>> response = weatherDetectionService.findDetectedWeatherByCam("605c72fffc13ae1426000000");

        // Assert
        Assertions.assertEquals(Constants.ResponseCodes.OK, response.getResponseCode(), response.getResponseDescription());
    }

    @Test
    @Order(5)
    @DisplayName("findDetectedWeatherByCam - whenNoDataPresentInDB - returnsWeatherDetectionNotFound")
    void findDetectedWeatherByCam_whenNoDataPresentInDB_returnsWeatherDetectionNotFound() {
        // Arrange
        List<WeatherDetection> weatherDetectionList = new ArrayList<>();
        when(weatherDetectionRepository.findByCamera(anyString())).thenReturn(weatherDetectionList);

        // Act
        GenericResponse<List<WeatherDetection>> response = weatherDetectionService.findDetectedWeatherByCam("605c72fffc13ae1426000000");

        // Assert
        Assertions.assertEquals(Constants.ResponseCodes.WEATHER_DETECTIOM_NOT_FOUND, response.getResponseCode(), response.getResponseDescription());
    }

    @Test
    @Order(6)
    @DisplayName("findDetectedWeatherByCam - whenThrownAnyException - returnsUnableToProcess")
    void findDetectedWeatherByCam_whenThrownAnyException_returnsUnableToProcess() {
        // Arrange
        doThrow(new RuntimeException("Exception")).when(weatherDetectionRepository).findByCamera(anyString());

        // Act
        GenericResponse<List<WeatherDetection>> response = weatherDetectionService.findDetectedWeatherByCam("605c72fffc13ae1426000000");

        // Assert
        Assertions.assertEquals(Constants.ResponseCodes.UNABLE_TO_PROCESS, response.getResponseCode(), response.getResponseDescription());
    }
}
