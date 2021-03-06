package com.challenge.repository;

import com.challenge.dbobjects.ItemDB;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ItemRepository extends JpaRepository<ItemDB, Long> {
}
