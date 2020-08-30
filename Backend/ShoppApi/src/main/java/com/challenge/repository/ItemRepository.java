package net.javaguides.springboot.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import net.javaguides.springboot.model.Item;

public interface ItemRepository extends JpaRepository<Item, Long> {

}
