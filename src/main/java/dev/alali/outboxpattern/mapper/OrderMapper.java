package dev.alali.outboxpattern.mapper;

import dev.alali.outboxpattern.dto.CreateOrderRequest;
import dev.alali.outboxpattern.dto.OrderResponse;
import dev.alali.outboxpattern.model.Order;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface OrderMapper {

    Order toOrder(CreateOrderRequest createOrderRequest);

    OrderResponse toOrderResponse(Order order);

}
