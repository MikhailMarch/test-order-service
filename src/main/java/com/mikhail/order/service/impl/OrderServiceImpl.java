package com.mikhail.order.service.impl;

import com.mikhail.order.dao.OrderDao;
import com.mikhail.order.dto.OrderCreateDto;
import com.mikhail.order.dto.OrderDto;
import com.mikhail.order.dto.OrderListDto;
import com.mikhail.order.exception.OrderNotFoundException;
import com.mikhail.order.model.Order;
import com.mikhail.order.service.OrderService;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

import static com.mikhail.order.model.Order.fromDto;

@Service
public class OrderServiceImpl implements OrderService {

    private final OrderDao orderDao;

    public OrderServiceImpl(OrderDao orderDao) {
        this.orderDao = orderDao;
    }

    @Override
    public OrderDto createOrder(OrderCreateDto orderCreateDto) {
        var order = fromDto(orderCreateDto);
        orderDao.save(order);
        return order.toDto();
    }

    @Override
    public OrderDto getOrder(Long orderId) {
        return orderDao.findById(orderId)
                .orElseThrow(() -> new OrderNotFoundException(String.format("Order %s not found", orderId)))
                .toDto();
    }

    @Override
    public OrderListDto getOrders(int page, int size) {
        var orderIds = orderDao.findAllOrderByDateCreated(PageRequest.of(page, size)).getContent();
        return new OrderListDto(orderIds);
    }


}
