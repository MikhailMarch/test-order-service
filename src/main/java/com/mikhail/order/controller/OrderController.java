package com.mikhail.order.controller;

import com.fasterxml.jackson.core.type.TypeReference;
import com.mikhail.order.dto.OrderCreateDto;
import com.mikhail.order.dto.OrderDto;
import com.mikhail.order.dto.OrderListDto;
import com.mikhail.order.exception.OrderNotFoundException;
import com.mikhail.order.service.OrderService;
import com.mikhail.order.utils.Wrapper;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("/order")
@Validated
public class OrderController {

    private final OrderService orderService;

    public OrderController(OrderService orderService) {
        this.orderService = orderService;
    }

    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Order created"),
            @ApiResponse(responseCode = "400", description = "Invalid body params supplied",
                    content = @Content),
            @ApiResponse(responseCode = "500", description = "Error creating order",
                    content = @Content)
    })
    @PostMapping("/create")
    public Wrapper<OrderDto> createOrder(@RequestBody @Valid OrderCreateDto orderCreateDto) {
        return Wrapper.from(orderService.createOrder(orderCreateDto));
    }

    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Order found"),
            @ApiResponse(responseCode = "500", description = "Entity not found",
                    content = @Content)
    })
    @GetMapping(value = "/{orderId}",  produces={"application/json"})
    public Wrapper<OrderDto> getOrder(@PathVariable("orderId") Long orderId) {
        return Wrapper.from(orderService.getOrder(orderId));
    }

    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Order found"),
    })
    @GetMapping(value = "/all")
    public Wrapper<OrderListDto> getOrder(@RequestParam(defaultValue = "0") int page,
                                          @RequestParam(defaultValue = "10") int size) {
        return Wrapper.from(orderService.getOrders(page, size));
    }

    @ExceptionHandler
    public Wrapper<?> handleException(OrderNotFoundException exception) {
        return Wrapper.error(exception.getMessage());
    }
}
