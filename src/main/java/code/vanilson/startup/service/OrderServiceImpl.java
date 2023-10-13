package code.vanilson.startup.service;

import code.vanilson.startup.exception.IllegalRequestException;
import code.vanilson.startup.exception.ObjectWithIdNotFound;
import code.vanilson.startup.model.Order;
import code.vanilson.startup.model.OrderItem;
import code.vanilson.startup.repository.OrderRepository;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.Set;

@Service
public class OrderServiceImpl implements OrderService {

    public static final String NOT_FOUND = " not found";
    public static final String THE_ORDER_OBJECT_MUST_NOT_BE_NULL = "The 'order' object must not be null.";
    private static final Logger logger = LogManager.getLogger(OrderServiceImpl.class);
    private final OrderRepository orderRepository;

    public OrderServiceImpl(OrderRepository orderRepository) {
        this.orderRepository = orderRepository;
    }

    @Override
    public List<Order> findAllOrders() {
        logger.info(" All orders");
        return orderRepository.findAllOrdersWithDetails();
    }

    @Override
    public Optional<Order> findOrderById(Long id) {
        Optional<Order> order = orderRepository.findById(id);
        if (order.isEmpty()) {
            throw new ObjectWithIdNotFound(String.format("order with id %d not found", id));
        }
        logger.info("Order with id:{}", order.get() + "found");
        return order;
    }

    @Override
    @Transactional
    public Order saveOrder(Order order) {
        if (Objects.isNull(order)) {
            logger.error("The 'order' object must not be:{}", order);
            throw new IllegalRequestException(THE_ORDER_OBJECT_MUST_NOT_BE_NULL);
        }
        logger.info("Order saved with success:{}", order);
        return orderRepository.save(order);
    }

    @Override
    @Transactional
    public Order updateOrder(long id, Order updatedOrder) {
        if (Objects.isNull(updatedOrder)) {
            logger.error(THE_ORDER_OBJECT_MUST_NOT_BE_NULL);
            throw new IllegalRequestException(THE_ORDER_OBJECT_MUST_NOT_BE_NULL);
        }
        Optional<Order> optionalOrder = orderRepository.findById(id);
        if (optionalOrder.isEmpty()) {
            logger.error("Order with id {} not found.", id);
            throw new ObjectWithIdNotFound("Order with id " + id + " not found.");
        }

        var existingOrder = optionalOrder.get();

        if (updatedOrder.getLocalDateTime() == null || updatedOrder.getCustomer() == null ||
                updatedOrder.getOrderItems() == null) {
            logger.error("Updating to null values for 'localDateTime', 'customer', or 'orderItems' is not allowed.");
            throw new IllegalRequestException(
                    "Updating to null values for 'localDateTime', 'customer', or 'orderItems' is not allowed.");
        }

        // Update existing Order properties
        existingOrder.setLocalDateTime(updatedOrder.getLocalDateTime());
        existingOrder.setCustomer(updatedOrder.getCustomer());

        // Clear the existing OrderItems
        existingOrder.getOrderItems().clear();

        // Update existing OrderItems
        Set<OrderItem> updatedOrderItems = updatedOrder.getOrderItems();
        for (OrderItem updatedItem : updatedOrderItems) {
            // Set the back reference to the existing Order
            updatedItem.setOrder(existingOrder);
            existingOrder.getOrderItems().add(updatedItem);
        }

        // Save the updated Order with updated OrderItems
        Order savedOrder = orderRepository.save(existingOrder);

        // Log the updated Order
        logger.info("Order updated with success: {}", savedOrder);

        return savedOrder;
    }

    @Override
    @Transactional
    public boolean deleteOrderById(long id) {
        Optional<Order> orders = orderRepository.findById(id);
        if (orders.isEmpty()) {
            throw new ObjectWithIdNotFound("order with id " + id + NOT_FOUND);
        }
        orders.ifPresent(orderRepository::delete);
        logger.info("Delete order with id: {}", id);
        return true;
    }
}
