package com.wis.libraryservice.dto.request;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;

import com.fasterxml.jackson.annotation.JsonAlias;

import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class UpdateCategoryRequest extends CreateCategoryRequest{

	@NotNull
	@Positive
	@JsonAlias("categoryID")
    @ApiModelProperty(name = "categoryId", value = "The unique ID of the Category", dataType = "Long", example = "1", required = true)
	private Long categoryId;
}
