package dev.alali.outboxpattern.dto;

import java.util.List;

public class CreateOrderRequest {

    private long customerId;

    private List<CreateOrderLine> lineItems;

    public long getCustomerId() {
        return customerId;
    }

    public void setCustomerId(long customerId) {
        this.customerId = customerId;
    }

    public List<CreateOrderLine> getLineItems() {
        return lineItems;
    }

    public void setLineItems(List<CreateOrderLine> lineItems) {
        this.lineItems = lineItems;
    }
}
