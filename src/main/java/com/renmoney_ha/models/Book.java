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

//    @Override
//    public boolean equals(Object o) {
//        if (this == o) return true;
//        if (o == null || Hibernate.getClass(this) != Hibernate.getClass(o)) return false;
//        Book book = (Book) o;
//
//        return Objects.equals(getId(), book.getId());
//    }
//
//    @Override
//    public int hashCode() {
//        return 967762358;
//    }
}
