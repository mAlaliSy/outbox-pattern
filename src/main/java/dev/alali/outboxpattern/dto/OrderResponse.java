package dev.alali.outboxpattern.dto;

import java.time.LocalDateTime;
import java.util.List;

public class OrderResponse {

    private long id;

    private long customerId;

    private LocalDateTime orderDate;

    private List<OrderLineResponse> lineItems;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public long getCustomerId() {
        return customerId;
    }

    public void setCustomerId(long customerId) {
        this.customerId = customerId;
    }

    public List<OrderLineResponse> getLineItems() {
        return lineItems;
    }

    public void setLineItems(List<OrderLineResponse> lineItems) {
        this.lineItems = lineItems;
    }

    public LocalDateTime getOrderDate() {
        return orderDate;
    }

    public void setOrderDate(LocalDateTime orderDate) {
        this.orderDate = orderDate;
    }
}
