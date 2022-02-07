package com.wis.libraryservice.dto.request;

import javax.validation.constraints.NotBlank;

import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class CreateCategoryRequest {

	@NotBlank
    @ApiModelProperty(name = "categoryName", value = "The name of the Category", dataType = "String", example = "Science fiction", required = true)
	private String categoryName;
}
