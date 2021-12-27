package com.mikhail.order.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

import javax.persistence.EntityNotFoundException;

@ResponseStatus(HttpStatus.EXPECTATION_FAILED)
public class OrderNotFoundException extends EntityNotFoundException {

    public OrderNotFoundException() {
    }

    public OrderNotFoundException(String message) {
        super(message);
    }
}
