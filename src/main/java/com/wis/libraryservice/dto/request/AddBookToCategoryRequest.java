package com.wis.libraryservice.dto.request;

import java.util.List;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;

import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class AddBookToCategoryRequest {

	@NotNull
    @ApiModelProperty(name = "isbns", value = "A comma-separated list of ISBN of the books to be added to the Category", 
    	dataType = "String", example = "[\"978-3-16-148410-0\", \"1-56619-909-3\"]", required = true)
	private List<String> isbns;

	@NotNull
	@Positive
    @ApiModelProperty(name = "categoryId", value = "The unique ID of the Category", dataType = "Long", example = "1", required = true)
	private Long categoryId;
}
