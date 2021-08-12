package com.renmoney_ha.models;


import lombok.*;

import javax.persistence.Entity;
import java.time.LocalDate;

@Entity
@Getter
@Setter
public class Book extends BaseEntity{
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
