package com.ecommerce.library.model;

import com.ecommerce.library.model.details.Color;
import com.ecommerce.library.model.details.Size;
import com.ecommerce.library.model.details.Stroge;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Data @AllArgsConstructor @NoArgsConstructor
@Entity
@Table(name = "products", uniqueConstraints = @UniqueConstraint(columnNames = {"name", "image"}))
public class Product {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "product_id")
    private Long id;

    private String name;

    private String description;

    private Double costPrice;

    private Double salePrice;

    private Integer currentQuantity;

    @Lob
    @Column(columnDefinition = "TEXT")
    private String image;

    @ManyToOne(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    @JoinColumn(name = "category_id", referencedColumnName = "category_id")
    private Category category;

    @ManyToOne(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    @JoinColumn(name = "color_id", referencedColumnName = "color_id")
    private Color color;

    @ManyToOne(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    @JoinColumn(name = "size_id", referencedColumnName = "size_id")
    private Size size;

    @ManyToOne(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    @JoinColumn(name = "stroge_id", referencedColumnName = "stroge_id")
    private Stroge stroge;

    private boolean is_deleted;

    private boolean is_activated;

    public boolean isDeleted() {
        return is_deleted;
    }

    public void setDeleted(boolean deleted) {
        this.is_activated = is_activated;
    }

}
