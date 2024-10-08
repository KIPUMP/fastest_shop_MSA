package com.shop.order_service.dto;

import com.shop.order_service.constant.OrderStatus;
import com.shop.order_service.entity.Order;
import lombok.Getter;
import lombok.Setter;

import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
public class OrderHistDto {
    private Long orderId;
    private String orderDate;
    private OrderStatus orderStatus;
    private int totalPrice;
    private List<OrderItemDto> orderItemDtoList = new ArrayList<>();

    public OrderHistDto(Order order) {
        this.orderId = order.getId();
        this.totalPrice = order.getTotalPrice();
        this.orderDate = order.getOrderDate().format(DateTimeFormatter.ofPattern("yyyy-<MM-dd HH:mm"));
        this.orderStatus = order.getOrderStatus();
    }

    public void addOrderItemDto(OrderItemDto orderItemDto) {
        orderItemDtoList.add(orderItemDto);
    }
}
