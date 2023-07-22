package com.ecommerce.library.model.details;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "stroges", schema = "shopcart", uniqueConstraints = @UniqueConstraint(columnNames = "name"))
public class Stroge {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "stroge_id")
    private Integer id;
    private String name;
}
