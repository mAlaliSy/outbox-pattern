package dev.alali.outboxpattern.model;

import dev.alali.outboxpattern.exception.OrderDomainException;
import jakarta.persistence.*;

import java.math.BigDecimal;

@Entity
@Table(name = "order_line")
public class OrderLine {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "order_line_ids")
    @SequenceGenerator(name = "order_line_ids", sequenceName = "seq_order_line")
    private Long id;

    @ManyToOne
    @JoinColumn(name = "order_id")
    private Order order;

    private String item;

    private int quantity;

    private BigDecimal totalPrice;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Order getOrder() {
        return order;
    }

    public void setOrder(Order order) {
        this.order = order;
    }

    public String getItem() {
        return item;
    }

    public void setItem(String item) {
        this.item = item;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public BigDecimal getTotalPrice() {
        return totalPrice;
    }

    public void setTotalPrice(BigDecimal totalPrice) {
        this.totalPrice = totalPrice;
    }

    public void validate() {
        if (quantity <= 0) {
            throw new OrderDomainException("Invalid order line, quantity should be greater than or equals to 1");
        }
        if (totalPrice == null || BigDecimal.ZERO.compareTo(totalPrice) >= 0) {
            throw new OrderDomainException("Invalid order line, total price should be more than 0");
        }
    }
}