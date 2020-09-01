package com.challenge.repository;

import com.challenge.dbobjects.CartItemDB;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CartItemRepository extends JpaRepository<CartItemDB, Long> {
}
