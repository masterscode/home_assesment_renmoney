package com.renmoney_ha.services;

import com.renmoney_ha.models.BorrowedBooks;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BorrowBookService extends JpaRepository<BorrowedBooks, Long> {
}
