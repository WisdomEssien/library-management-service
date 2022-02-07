package com.wis.libraryservice.dto.response;

import static com.wis.libraryservice.util.ResponseConstant.SUCCESS_CODE;
import static com.wis.libraryservice.util.ResponseConstant.SUCCESS_MESSAGE;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper = true)
@JsonInclude(Include.NON_NULL)
public class BaseStandardResponse<T> extends BaseResponse{

	private T data;
	
	public BaseStandardResponse(T data) {
		super(SUCCESS_CODE, SUCCESS_MESSAGE);
		this.data = data;
	}
	
	public BaseStandardResponse(String responseCode, String responseMessage) {
		super(responseCode, responseMessage);
	}
}
