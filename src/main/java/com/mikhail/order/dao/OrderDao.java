package com.mikhail.order.dao;

import com.mikhail.order.model.Order;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface OrderDao extends JpaRepository<Order, Long> {

    @Query(value = "" +
            "SELECT o.id " +
            "FROM Order o " +
            "ORDER BY o.dateCreated desc ")
    Page<Long> findAllOrderByDateCreated(Pageable pageable);
}
