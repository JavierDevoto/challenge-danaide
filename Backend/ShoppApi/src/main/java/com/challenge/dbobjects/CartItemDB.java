package com.challenge.dbobjects;

import com.challenge.model.Item;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "cart_item")
public class CartItemDB {

    @Id
    @GeneratedValue
    @Column(name = "id", nullable = false)
    protected Long id;

    @ManyToOne
    protected Item item;

    @Column(name = "id", nullable = false)
    protected Integer quantity;

}
