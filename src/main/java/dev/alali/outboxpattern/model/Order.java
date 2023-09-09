package dev.alali.outboxpattern.model;

import dev.alali.outboxpattern.exception.OrderDomainException;
import jakarta.persistence.*;
import org.springframework.util.CollectionUtils;

import java.time.LocalDateTime;
import java.util.List;

@Entity
@Table(name = "orders")
public class Order {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "order_ids")
    @SequenceGenerator(name = "order_id", sequenceName = "seq_order", allocationSize = 50)
    private Long id;

    private Long customerId;

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "order", cascade = CascadeType.ALL)
    private List<OrderLine> lineItems;

    private LocalDateTime orderDate;


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getCustomerId() {
        return customerId;
    }

    public void setCustomerId(Long customerId) {
        this.customerId = customerId;
    }

    public List<OrderLine> getLineItems() {
        return lineItems;
    }

    public void setLineItems(List<OrderLine> lineItems) {
        this.lineItems = lineItems;
    }

    public LocalDateTime getOrderDate() {
        return orderDate;
    }

    public void setOrderDate(LocalDateTime orderDate) {
        this.orderDate = orderDate;
    }

    public void validateAndInitialize() {
        if (CollectionUtils.isEmpty(lineItems)) {
            throw new OrderDomainException("Invalid order, order must have at least one line item");
        }
        lineItems.forEach(OrderLine::validate);
        this.orderDate = LocalDateTime.now();
    }
}
