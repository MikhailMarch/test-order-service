package com.mikhail.order.dto;

import java.util.List;

public class OrderListDto {

    List<Long> orderIds;

    public List<Long> getOrderIds() {
        return orderIds;
    }

    public void setOrderIds(List<Long> orderIds) {
        this.orderIds = orderIds;
    }

    public OrderListDto(List<Long> orderIds) {
        this.orderIds = orderIds;
    }
}
