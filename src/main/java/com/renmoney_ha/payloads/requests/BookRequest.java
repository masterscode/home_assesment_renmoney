package com.renmoney_ha.payloads.requests;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serializable;
import java.time.LocalDate;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class BookRequest implements Serializable {
    private String title;
    private String isbn;
    private String author;
    private String publisher;
    private String language;
    private LocalDate publicationDate;
    private String dimension;
    private Integer length;
    private Double price;
}
