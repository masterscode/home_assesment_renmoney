package com.renmoney_ha.models;

import lombok.Getter;
import lombok.Setter;
import org.springframework.lang.Nullable;

import javax.persistence.Entity;
import javax.persistence.OneToOne;
import java.util.Date;

@Entity
@Setter
@Getter
public class BorrowedBooks extends BaseEntity {
    @OneToOne(targetEntity = Book.class)
    private Book book;

    @OneToOne(targetEntity = User.class)
    private User user;

    @Nullable
    private Date returnedDate;

    private Date borrowDate;

    private Boolean isBorrowed;
}
