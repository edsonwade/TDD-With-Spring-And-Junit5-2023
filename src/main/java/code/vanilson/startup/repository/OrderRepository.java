package code.vanilson.startup.repository;

import code.vanilson.startup.model.Order;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface OrderRepository extends JpaRepository<Order, Long> {

    @Query("SELECT o FROM Order o JOIN FETCH o.customer JOIN FETCH o.orderItems")
    List<Order> findAllOrdersWithDetails();


}
