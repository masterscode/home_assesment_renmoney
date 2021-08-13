package com.renmoney_ha.models;


import lombok.*;

import javax.persistence.Column;
import javax.persistence.Entity;
import java.time.LocalDate;

@Entity
@Getter
@Setter
public class Book extends BaseEntity{
    private String title;

    @Column(unique = true)
    private String isbn;

    private Boolean isDeleted = false;
    private String author;
    private String publisher;
    private String language;
    private LocalDate publicationDate;
    private Long pages;
    private Double price;
}
