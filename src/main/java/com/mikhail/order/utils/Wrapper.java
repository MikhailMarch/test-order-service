package com.mikhail.order.utils;

public class Wrapper<T> {

    private T payload;

    private String error;

    public T getPayload() {
        return payload;
    }

    public void setPayload(T payload) {
        this.payload = payload;
    }

    public String getError() {
        return error;
    }

    public void setError(String error) {
        this.error = error;
    }

    public Wrapper(T payload) {
        this.payload = payload;
    }

    public Wrapper(String error) {
        this.error = error;
    }

    public static <T> Wrapper<T> from(T payload) {
        return new Wrapper(payload);
    }

    public static <T> Wrapper<T> error(String error) {
        return new Wrapper(error);
    }
}
