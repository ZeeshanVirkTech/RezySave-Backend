package com.camsensehub.service;

import com.camsensehub.entity.MoldDetection;
import com.camsensehub.model.response.GenericResponse;
import com.camsensehub.repository.MoldDetectionRepository;
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

public class MoldDetectionServiceImplTest {

    @InjectMocks
    private MoldDetectionServiceImpl moldDetectionService;

    @Mock
    private MoldDetectionRepository moldDetectionRepository;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    /* -------------------------------------- Start : getAllDetectedMolds -------------------------------------- */

    @Test
    @Order(1)
    @DisplayName("getAllDetectedMolds - whenSuccess - returnsProcessedOK")
    void getAllDetectedMolds_whenSuccess_returnsProcessedOK() {
        // Arrange
        MoldDetection mold1 = new MoldDetection();
        mold1.setCamera("Camera 1");
        mold1.setMoldDetection(true);
        mold1.setStampDate(new Date());

        MoldDetection mold2 = new MoldDetection();
        mold2.setCamera("Camera 2");
        mold2.setMoldDetection(false);
        mold2.setStampDate(new Date());

        List<MoldDetection> moldList = new ArrayList<>(Arrays.asList(mold1, mold2));

        when(moldDetectionRepository.findAll()).thenReturn(moldList);

        // Act
        GenericResponse<List<MoldDetection>> response = moldDetectionService.getAllDetectedMolds();

        // Assert
        Assertions.assertEquals(Constants.ResponseCodes.OK, response.getResponseCode());
    }

    @Test
    @Order(2)
    @DisplayName("getAllDetectedMolds - whenNoDataPresentInDB - returnsMoldDetectionEndpointNotFound")
    void getAllDetectedMolds_whenNoDataPresentInDB_returnsMoldDetectionEndpointNotFound() {
        // Arrange
        List<MoldDetection> moldList = new ArrayList<>();
        when(moldDetectionRepository.findAll()).thenReturn(moldList);

        // Act
        GenericResponse<List<MoldDetection>> response = moldDetectionService.getAllDetectedMolds();

        // Assert
        Assertions.assertEquals(Constants.ResponseCodes.MOLD_DETECTIOM_ENDPOINT_NOT_FOUND, response.getResponseCode());
    }

    @Test
    @Order(3)
    @DisplayName("getAllDetectedMolds - whenThrownAnyException - returnsUnableToProcess")
    void getAllDetectedMolds_whenThrownAnyException_returnsUnableToProcess() {
        // Arrange
        when(moldDetectionRepository.findAll()).thenThrow(new RuntimeException("Exception"));

        // Act
        GenericResponse<List<MoldDetection>> response = moldDetectionService.getAllDetectedMolds();

        // Assert
        Assertions.assertEquals(Constants.ResponseCodes.UNABLE_TO_PROCESS, response.getResponseCode());
    }

    /* -------------------------------------- End : getAllDetectedMolds -------------------------------------- */

    /* -------------------------------------- Start : findDetectedMoldByCam -------------------------------------- */

    @Test
    @Order(4)
    @DisplayName("findDetectedMoldByCam - whenSuccess - returnsProcessedOK")
    void findDetectedMoldByCam_whenSuccess_returnsProcessedOK() {
        // Arrange
        MoldDetection mold1 = new MoldDetection();
        mold1.setCamera("Camera 1");
        mold1.setMoldDetection(true);
        mold1.setStampDate(new Date());

        MoldDetection mold2 = new MoldDetection();
        mold2.setCamera("Camera 1");
        mold2.setMoldDetection(false);
        mold2.setStampDate(new Date());

        List<MoldDetection> moldList = new ArrayList<>(Arrays.asList(mold1, mold2));

        when(moldDetectionRepository.findByCamera(anyString())).thenReturn(moldList);

        // Act
        GenericResponse<List<MoldDetection>> response = moldDetectionService.findDetectedMoldByCam("Camera 1");

        // Assert
        Assertions.assertEquals(Constants.ResponseCodes.OK, response.getResponseCode());
    }

    @Test
    @Order(5)
    @DisplayName("findDetectedMoldByCam - whenNoDataPresentInDB - returnsCameraDetailsNotFound")
    void findDetectedMoldByCam_whenNoDataPresentInDB_returnsCameraDetailsNotFound() {
        // Arrange
        List<MoldDetection> moldList = new ArrayList<>();
        when(moldDetectionRepository.findByCamera(anyString())).thenReturn(moldList);

        // Act
        GenericResponse<List<MoldDetection>> response = moldDetectionService.findDetectedMoldByCam("Camera 1");

        // Assert
        Assertions.assertEquals(Constants.ResponseCodes.CAMERA_DETAILS_NOT_FOUND, response.getResponseCode());
    }

    @Test
    @Order(6)
    @DisplayName("findDetectedMoldByCam - whenThrownAnyException - returnsUnableToProcess")
    void findDetectedMoldByCam_whenThrownAnyException_returnsUnableToProcess() {
        // Arrange
        when(moldDetectionRepository.findByCamera(anyString())).thenThrow(new RuntimeException("Exception"));

        // Act
        GenericResponse<List<MoldDetection>> response = moldDetectionService.findDetectedMoldByCam("Camera 1");

        // Assert
        Assertions.assertEquals(Constants.ResponseCodes.UNABLE_TO_PROCESS, response.getResponseCode());
    }

    /* -------------------------------------- End : findDetectedMoldByCam -------------------------------------- */
}
