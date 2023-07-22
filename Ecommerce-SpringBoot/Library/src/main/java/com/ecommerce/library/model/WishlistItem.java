package com.ecommerce.library.model;
import lombok.*;

import javax.persistence.*;


@Getter @Setter
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "wishlist_item", schema = "shopcart")
@Entity
public class WishlistItem {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "wishlist_item_id")
    private Long id;

    @ManyToOne(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    @JoinColumn(name = "wishlist_id", referencedColumnName = "wishlist_id")
    private Wishlist wishlist;

    @OneToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "product_id", referencedColumnName = "product_id")
    private Product product;
}
