package com.wis.libraryservice.dto.request;

import javax.validation.constraints.NotBlank;

import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class BookRequest {

	@NotBlank
    @ApiModelProperty(name = "isbn", value = "The ISBN of the book", dataType = "String", example = "978-3-16-148410-0", required = true)
	private String isbn;

	@NotBlank
    @ApiModelProperty(name = "title", value = "The Title of the book", dataType = "String", example = "Things fall apart", required = true)
	private String title;

	@NotBlank
    @ApiModelProperty(name = "author", value = "Author of the book", dataType = "String", example = "Chinua Achebe", required = true)
	private String author;

    @ApiModelProperty(name = "edition", value = "Edition of the book", dataType = "String", example = "second")
	private String edition;

    @ApiModelProperty(name = "totalCopy", value = "Number of books in stock", dataType = "Long", example = "5")
	private Long totalCopy;

    @ApiModelProperty(name = "yearOfPublication", value = "The first year of publication", dataType = "String", example = "1958")
	private String yearOfPublication;
    
    @ApiModelProperty(name = "categoryId", value = "The category ID the book belongs to", dataType = "String", example = "1")
    private Long categoryId;
}
