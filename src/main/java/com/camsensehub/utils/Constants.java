package com.camsensehub.utils;

import java.util.HashMap;

public class Constants {

	public static class ResponseCodes {
		public static final String OK = "00";
		public static final String INVALID_PARAMS = "01";
		public static final String UNABLE_TO_PROCESS = "99";
		public static final String CAMERA_DETAILS_NOT_FOUND = "60";
		public static final String ENTITY_ENDPOINT_NOT_FOUND = "60";
		public static final String MOLD_DETECTIOM_ENDPOINT_NOT_FOUND = "60";
		public static final String WATER_DETECTIOM_NOT_FOUND = "60";
		public static final String WEATHER_DETECTIOM_NOT_FOUND = "60";

		public static final String UNABLE_TO_PROCESS_MESSAGE = "Unable to process at this time. Please, try again later";

		public static final HashMap<String, String> RESPONSE_MAP = new HashMap<String, String>() {

			{
				put(OK, "Processed OK");
				put(CAMERA_DETAILS_NOT_FOUND, "Camera Details not found");
				put(INVALID_PARAMS, "Required Parameters Not Provided");
				put(UNABLE_TO_PROCESS, UNABLE_TO_PROCESS_MESSAGE);
				put(ENTITY_ENDPOINT_NOT_FOUND, "Captured Entites Details not found");
				put(MOLD_DETECTIOM_ENDPOINT_NOT_FOUND, "Mold Detection Details not found");
				put(WATER_DETECTIOM_NOT_FOUND, "Water Detection Details not found");
				put(WEATHER_DETECTIOM_NOT_FOUND, "Weather Detection Details not found");
			}
		};


	}

	public static class ACTIVITY {
		public static final String Login = "Login";

	}

	public static class Status {
		public static final String Success = "Success";
		public static final String Fail = "Fail";
		public static final String Processing = "Processing";
		public static final String Pending = "Pending";
	}

}
