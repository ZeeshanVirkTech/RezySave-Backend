package com.camsensehub.api;

public abstract class ApiController {
    private static final String API_PATH = "/api/v1/";

    public static final String CAMERA_ENDPOINT = API_PATH + "camera";
    public static final String Entity_ENDPOINT = API_PATH + "entitydetection";
    public static final String Mold_ENDPOINT = API_PATH + "molddetection";
    public static final String Water_ENDPOINT = API_PATH + "waterdetection";
    public static final String Weather_ENDPOINT = API_PATH + "weatherdetection";
}
