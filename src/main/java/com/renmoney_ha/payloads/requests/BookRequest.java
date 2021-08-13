package com.renmoney_ha.payloads.requests;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.NotBlank;
import java.io.Serializable;
import java.time.LocalDate;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class BookRequest implements Serializable {

    @NotBlank(message = "This field should not be empty")
    private String title;
    @NotBlank(message = "This field should not be empty")
    private String isbn;

    @NotBlank(message = "This field should not be empty")
    private String author;

    @NotBlank(message = "This field should not be empty")
    private String publisher;

    @NotBlank(message = "This field should not be empty")
    private String language;

    @NotBlank(message = "This field should not be empty")
    private LocalDate publicationDate;

    @NotBlank(message = "This field should not be empty")
    private Integer length;

    @NotBlank(message = "This field should not be empty")
    private Double price;

}
