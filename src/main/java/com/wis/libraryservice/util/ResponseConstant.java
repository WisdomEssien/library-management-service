package com.wis.libraryservice.util;

public final class ResponseConstant {
	private ResponseConstant() {}

	public static final String SUCCESS_CODE = "00";
	public static final String SUCCESS_MESSAGE = "Successful";
	
	public static final String FAIL_DATABASE_CODE = "01";
	public static final String FAIL_DATABASE_MESSAGE = "Database error";
	
	public static final String FAIL_RECORD_NOT_EXIST_CODE = "02";
	public static final String FAIL_RECORD_NOT_EXIST_MESSAGE = "Record does not exist";
	
	public static final String ERROR_SAVING_TO_DATABASE = "Error Occurred while saving to the database \n";

	public static final String FAIL_INTERNAL_SERVER_ERROR_CODE = "03";
	public static final String FAIL_INTERNAL_SERVER_ERROR_MESSAGE = "Opss! Something went wrong while trying to process your request";
}
