package com.challenge.dbobjects;

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
@Table(name = "item")
public class ItemDB {

	@Id
	@GeneratedValue
	@Column(name = "id", nullable = false)
	protected Long id;

	@Column(name = "name")
	protected String name;

	@Column(name = "description")
	protected String description;

	@Column(name = "price")
	protected Double price;

}
