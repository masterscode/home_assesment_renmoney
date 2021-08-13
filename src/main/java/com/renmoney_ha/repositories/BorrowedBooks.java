package com.renmoney_ha.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BorrowedBooks extends JpaRepository<BorrowedBooks, Long> {
}
