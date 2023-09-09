package dev.alali.outboxpattern.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import dev.alali.outboxpattern.dto.CreateOrderRequest;
import dev.alali.outboxpattern.dto.OrderResponse;
import dev.alali.outboxpattern.mapper.OrderMapper;
import dev.alali.outboxpattern.model.Order;
import dev.alali.outboxpattern.model.OrderLine;
import dev.alali.outboxpattern.model.OutboxEvent;
import dev.alali.outboxpattern.repository.OrderRepository;
import dev.alali.outboxpattern.repository.OutboxRepository;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
public class OrderService {

    private final OrderRepository orderRepository;

    private final OutboxRepository outboxRepository;

    private final ObjectMapper objectMapper;

    private final OrderMapper orderMapper;


    public OrderService(OrderRepository orderRepository,
                        OutboxRepository outboxRepository,
                        ObjectMapper objectMapper,
                        OrderMapper orderMapper) {
        this.orderRepository = orderRepository;
        this.outboxRepository = outboxRepository;
        this.objectMapper = objectMapper;
        this.orderMapper = orderMapper;
    }

    @Transactional
    public OrderResponse create(CreateOrderRequest createOrderRequest) {
        Order order = orderMapper.toOrder(createOrderRequest);
        for (OrderLine lineItem : order.getLineItems()) {
            lineItem.setOrder(order);
        }
        order.validateAndInitialize();
        orderRepository.save(order);
        OrderResponse orderResponse = orderMapper.toOrderResponse(order);
        OutboxEvent outboxEvent = toOutboxEvent(orderResponse);
        outboxRepository.save(outboxEvent);
        outboxRepository.delete(outboxEvent);
        return orderResponse;
    }

    private OutboxEvent toOutboxEvent(OrderResponse order) {
        try {
            return OutboxEvent.of("Order", String.valueOf(order.getId()), "OrderCreated", objectMapper.writeValueAsString(order));
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }
    }

    private void initializeOrder(Order order) {
        order.setOrderDate(LocalDateTime.now());
    }


}
