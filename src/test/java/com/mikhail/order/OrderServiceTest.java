package com.mikhail.order;

import com.mikhail.order.dao.OrderDao;
import com.mikhail.order.dto.OrderCreateDto;
import com.mikhail.order.exception.OrderNotFoundException;
import com.mikhail.order.model.Order;
import com.mikhail.order.service.OrderService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Random;
import java.util.stream.Collectors;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;
import static org.hamcrest.Matchers.notNullValue;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class OrderServiceTest extends TestOrderServiceApplicationTests{

    @Autowired
    private OrderService orderService;

    @Autowired
    private OrderDao orderDao;

    private final Random random = new Random();

    @BeforeEach
    void flushOrders() {
        orderDao.deleteAll();
    }

    @Test
    void orderCreationTest() {
        var dto = new OrderCreateDto();
        dto.setUsername("Mike");
        dto.setEmail("mike@gmail.com");
        dto.setAddress("Mike street");
        dto.setPhoneNumber("8005553535");

        var createdOrder = orderService.createOrder(dto);

        assertThat(createdOrder.getAddress(), is(dto.getAddress()));
        assertThat(createdOrder.getEmail(), is(dto.getEmail()));
        assertThat(createdOrder.getUsername(), is(dto.getUsername()));
        assertThat(createdOrder.getPhoneNumber(), is(dto.getPhoneNumber()));
        assertThat(createdOrder.getId(), notNullValue());
    }

    @Test
    void ordersRetrievalTest() {

        List<Order> orderList = new ArrayList<>();
        for (int i = 0; i < 20; i++) {
            var order = new Order();
            order.setUsername(randomString());
            order.setEmail(randomString());
            order.setAddress(randomString());
            order.setPhoneNumber(randomString());
            orderDao.save(order);
            orderList.add(order);
        }

        var firstOrdersPage = orderService.getOrders(0, 10).getOrderIds();

        assertThat(firstOrdersPage.size(), is(10));
        assertThat(firstOrdersPage, is(orderList.stream()
                .skip(10)
                .limit(10)
                .sorted(Comparator.comparing(Order::getId).reversed())
                .map(Order::getId)
                .collect(Collectors.toList())));

        var secondOrdersPage = orderService.getOrders(1, 10).getOrderIds();

        assertThat(secondOrdersPage.size(), is(10));
        assertThat(secondOrdersPage, is(orderList.stream()
                .limit(10)
                .sorted(Comparator.comparing(Order::getId).reversed())
                .map(Order::getId)
                .collect(Collectors.toList())));
    }

    @Test
    void orderRetrievalTest() {

        var order = new Order();
        order.setUsername(randomString());
        order.setEmail(randomString());
        order.setAddress(randomString());
        order.setPhoneNumber(randomString());
        orderDao.save(order);

        var orderDto = orderService.getOrder(order.getId());

        assertThat(order.getAddress(), is(orderDto.getAddress()));
        assertThat(order.getEmail(), is(orderDto.getEmail()));
        assertThat(order.getUsername(), is(orderDto.getUsername()));
        assertThat(order.getPhoneNumber(), is(orderDto.getPhoneNumber()));
        assertThat(order.getDateCreated(), is(orderDto.getDateCreated()));
        assertThat(order.getId(), is(orderDto.getId()));
    }

    @Test
    void orderNotFoundTest() {
        var orderId = random.nextLong();

        var exception = assertThrows(
                OrderNotFoundException.class,
                () -> orderService.getOrder(orderId));

        assertThat(exception.getMessage(), is(String.format("Order %s not found", orderId)));
    }

    private String randomString() {
        byte[] array = new byte[random.nextInt(10)];
        random.nextBytes(array);
        return new String(array, Charset.forName("UTF-8"));
    }
}
