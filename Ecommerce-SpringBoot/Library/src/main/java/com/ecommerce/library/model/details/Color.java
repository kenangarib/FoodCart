package com.ecommerce.library.model.details;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Data @AllArgsConstructor @NoArgsConstructor
@Table(name = "colors", schema = "shopcart", uniqueConstraints = @UniqueConstraint(columnNames = "name"))
public class Color {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "color_id")
    private Integer id;
    private String name;
}
