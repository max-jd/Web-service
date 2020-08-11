package com.gmailat.pm.entity;

import lombok.*;
import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name = "products")
@DynamicUpdate
@DynamicInsert
@Setter
@Getter
@EqualsAndHashCode
@NoArgsConstructor
@AllArgsConstructor
public class Product implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String name;
    private String category;
    private float price;
    /*@Column(name = "fk_discount")
    private int discount;*/

    @ManyToOne
    @JoinColumn(name = "client_id")
    private Client client;

    @OneToOne
    @JoinColumn(name = "fk_discount")
    private Discount discount;

    public float getDiscountBenefit() {
        int discount = this.discount.getPercent();

        if (discount == 0) {
            return discount;
        }
        return price * discount / 100;
    }

}
