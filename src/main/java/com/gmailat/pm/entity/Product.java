package com.gmailat.pm.entity;

import lombok.*;

import javax.persistence.*;

@Entity
@Setter
@Getter
@EqualsAndHashCode
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "products")
public class Product {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String name;
    private String category;
    private float price;
    @Column(name = "fk_discount")
    private int discount;


    public float getDiscountBenefit() {
        if (discount == 0) {
            return discount;
        }
        return price * discount / 100;
    }

}
