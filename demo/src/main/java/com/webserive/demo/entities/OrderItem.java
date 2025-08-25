package com.webserive.demo.entities;

import com.webserive.demo.entities.pk.OrderItemPK;

import jakarta.persistence.Column;
import jakarta.persistence.EmbeddedId;
import jakarta.persistence.Entity;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@Entity
@NoArgsConstructor
@AllArgsConstructor
public class OrderItem {

    @EmbeddedId
    private OrderItemPK id;

    @Column(name = "quantity")
    private Integer quantity;

    @Column(name = "price")
    private Double price;

    public Double subtotal(){
        return quantity * price;
    }

    public void setOrder(Order order) {
        id.setOrder(order);
    }

    public Order getOrder() {
        return id.getOrder();
    }

    public void setProduct(Product product) {
        id.setProduct(product);
    }

    public Product getProduct() {
        return id.getProduct();
    }
}
