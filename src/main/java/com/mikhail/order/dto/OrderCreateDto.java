package com.mikhail.order.dto;

import javax.validation.constraints.*;

public class OrderCreateDto {

    @NotNull
    @Size(min = 1, max = 30)
    private String username;

    @NotNull
    @Pattern(regexp = "^\\d{10}$")
    private String phoneNumber;

    @NotNull
    @Email
    private String email;

    @NotNull
    @Size(min = 1)
    private String address;

    public String getUsername() {
        return username;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public String getEmail() {
        return email;
    }

    public String getAddress() {
        return address;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public OrderCreateDto() {
    }
}
