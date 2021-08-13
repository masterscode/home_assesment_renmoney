package com.renmoney_ha.models;

import lombok.Getter;
import lombok.Setter;
import org.springframework.lang.Nullable;

import javax.persistence.Entity;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import java.time.LocalDate;
import java.util.Date;
import java.util.List;

@Entity
@Setter
@Getter
public class BorrowedBooks extends BaseEntity {
    @OneToMany(targetEntity = Book.class)
    private List<Book> books;

    @OneToOne(targetEntity = User.class)
    private User user;

    @Nullable
    private Date returnedDate;

    private LocalDate borrowDate;
}
