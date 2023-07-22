package com.ecommerce.library.model;
import lombok.*;
import javax.persistence.*;
import java.util.Set;

@Setter @Getter
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "wishlist", schema = "shopcart")
@Entity
public class Wishlist {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "wishlist_id")
    private Long id;

    @OneToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "customer_id", referencedColumnName = "customer_id")
    private Customer customer;

    @OneToMany(mappedBy = "wishlist", cascade = CascadeType.ALL)
    private Set<WishlistItem> item;
}
