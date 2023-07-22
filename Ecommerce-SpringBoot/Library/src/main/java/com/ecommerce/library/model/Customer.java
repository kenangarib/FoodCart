package com.ecommerce.library.model;
import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.Size;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;


@Entity
@Getter
@Setter
@AllArgsConstructor
@Table(name = "customers", schema = "shopcart" , uniqueConstraints =  @UniqueConstraint(columnNames = {"email", "username", "image", "phone_number"}))
public class Customer {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "customer_id")
    private Long id;

    @Size(min = 3, max = 15, message = "First name should have 3-15 characters")
    private String firstName;
    @Size(min = 3, max = 15, message = "Last name should have 3-15 characters")
    private String lastName;
    private String username;
    private String email;
    @Column(name = "phone_number")
    private String phoneNumber;
    private String address;

    private String password;

    @Lob
    @Column(name = "image", columnDefinition = "TEXT")
    private String image;

    @OneToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "name", referencedColumnName = "id")
    private City city;

    private String country;

    @OneToOne(mappedBy = "customer", cascade = CascadeType.ALL)
    private ShoppingCart shoppingCart;

    @OneToOne(mappedBy = "customer", cascade = CascadeType.ALL)
    private Wishlist wishlist;

    @OneToMany(mappedBy = "customer", cascade = CascadeType.ALL)
    private List<Order> orders;

    @ManyToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    @JoinTable( name = "customers_roles",
            joinColumns = @JoinColumn(name = "customer_id", referencedColumnName = "customer_id"),
            inverseJoinColumns = @JoinColumn(name = "role_id", referencedColumnName = "role_id"))
    private Collection<Role> roles;

    public Customer() {
        this.country = "VN";
        this.shoppingCart = new ShoppingCart();
        this.wishlist = new Wishlist();
        this.orders = new ArrayList<>();
    }

    @Override
    public String toString() {
        return "Customer{" +
                "id=" + id +
                ", firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", username='" + username + '\'' +
                ", password='" + password + '\'' +
                ", phoneNumber='" + phoneNumber + '\'' +
                ", address='" + address + '\'' +
                ", city=" + city.getName() +
                ", country='" + country + '\'' +
                ", roles=" + roles +
                ", cart=" + shoppingCart.getId() +
                ", wishlist=" + wishlist.getId() +
                ", orders=" + orders.size() +
                '}';
    }
}
