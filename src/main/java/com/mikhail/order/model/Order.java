package com.mikhail.order.model;

import com.mikhail.order.dto.OrderCreateDto;
import com.mikhail.order.dto.OrderDto;
import org.hibernate.annotations.CreationTimestamp;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name="\"order\"")
public class Order {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String username;

    @Column(nullable = false)
    private String phoneNumber;

    @Column(nullable = false)
    private String email;

    @Column(nullable = false)
    private String address;

    @CreationTimestamp
    private LocalDateTime dateCreated;

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

    public LocalDateTime getDateCreated() {
        return dateCreated;
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

    public void setDateCreated(LocalDateTime dateCreated) {
        this.dateCreated = dateCreated;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getId() {
        return id;
    }

    public static Order fromDto(OrderCreateDto orderCreateDto) {
        var order = new Order();
        order.setAddress(orderCreateDto.getAddress());
        order.setUsername(orderCreateDto.getUsername());
        order.setPhoneNumber(orderCreateDto.getPhoneNumber());
        order.setEmail(orderCreateDto.getEmail());
        return order;
    }

    public OrderDto toDto() {
        var dto = new OrderDto();
        dto.setId(this.id);
        dto.setAddress(this.address);
        dto.setPhoneNumber(this.phoneNumber);
        dto.setUsername(this.username);
        dto.setEmail(this.email);
        dto.setDateCreated(this.dateCreated);
        return dto;
    }
}
