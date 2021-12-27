package com.mikhail.order.service;

import com.mikhail.order.dto.OrderCreateDto;
import com.mikhail.order.dto.OrderDto;
import com.mikhail.order.dto.OrderListDto;

import java.util.List;

public interface OrderService {

    /**
     * @param orderCreateDto - validated dto to create order
     * @return - dto of created order
     */
    OrderDto createOrder(OrderCreateDto orderCreateDto);

    /**
     * @param orderId - id of required order
     * @return - dto that contains all order information
     */
    OrderDto getOrder(Long orderId);

    /**
     * @param page - page number (offset)
     * @param size - size of the page
     * @return - dto which contains list of ids of requests orders
     */
    OrderListDto getOrders(int page, int size);
}
