package code.vanilson.startup.service;

import code.vanilson.startup.exception.IllegalRequestException;
import code.vanilson.startup.exception.ObjectWithIdNotFound;
import code.vanilson.startup.model.OrderItem;
import code.vanilson.startup.repository.OrderItemRepository;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

@Service
public class OrderItemServiceImpl implements OrderItemService {

    public static final String NOT_FOUND = " not found";
    public static final String THE_ORDER_ITEM_OBJECT_MUST_NOT_BE_NULL = "The 'orderItem' object must not be null.";
    private static final Logger logger = LogManager.getLogger(OrderItemServiceImpl.class);
    private final OrderItemRepository orderItemRepository;

    public OrderItemServiceImpl(OrderItemRepository orderItemRepository) {
        this.orderItemRepository = orderItemRepository;
    }

    @Override
    public List<OrderItem> findAllOrderItems() {
        logger.info("All orderItems");
        return orderItemRepository.findAll();
    }

    @Override
    public Optional<OrderItem> findOrderItemById(Long id) {
        Optional<OrderItem> orderItem = orderItemRepository.findById(id);
        if (orderItem.isEmpty()) {
            throw new ObjectWithIdNotFound(String.format("orderItem with id %d not found", id));
        }
        logger.info("OrderItem with id:{}", orderItem.get() + "found");
        return orderItem;
    }

    @Override
    @Transactional
    public OrderItem saveOrderItem(OrderItem orderItem) {
        if (Objects.isNull(orderItem)) {
            logger.error("The 'orderItem' object must not be:{}", orderItem);
            throw new IllegalRequestException(THE_ORDER_ITEM_OBJECT_MUST_NOT_BE_NULL);
        }
        logger.info("OrderItem saved with success:{}", orderItem);
        return orderItemRepository.save(orderItem);
    }

//    @Override
//    @Transactional
//    public OrderItem updateOrderItem(long id, OrderItem updatedOrder) {
//        if (Objects.isNull(updatedOrder)) {
//            logger.error(THE_ORDER_OBJECT_MUST_NOT_BE_NULL);
//            throw new IllegalRequestException(THE_ORDER_OBJECT_MUST_NOT_BE_NULL);
//        }
//        Optional<OrderItem> optionalOrder = orderItemRepository.findById(id);
//        if (optionalOrder.isEmpty()) {
//            logger.error("Order with id {} not found.", id);
//            throw new ObjectWithIdNotFound("Order with id " + id + " not found.");
//        }
//
//        var existingOrder = optionalOrder.get();
//
//        if (updatedOrder.getLocalDateTime() == null || updatedOrder.getCustomer() == null ||
//                updatedOrder.getOrderItems() == null) {
//            logger.error("Updating to null values for 'localDateTime', 'customer', or 'orderItems' is not allowed.");
//            throw new IllegalRequestException(
//                    "Updating to null values for 'localDateTime', 'customer', or 'orderItems' is not allowed.");
//        }
//
//        // Update existing Order properties
//        existingOrder.setLocalDateTime(updatedOrder.getLocalDateTime());
//        existingOrder.setCustomer(updatedOrder.getCustomer());
//
//        // Clear the existing OrderItems
//        existingOrder.getOrderItems().clear();
//
//        // Update existing OrderItems
//        Set<OrderItem> updatedOrderItems = updatedOrder.getOrderItems();
//        for (OrderItem updatedItem : updatedOrderItems) {
//            // Set the back reference to the existing Order
//            updatedItem.setOrder(existingOrder);
//            existingOrder.getOrderItems().add(updatedItem);
//        }
//
//        // Save the updated Order with updated OrderItems
//        Order savedOrder = orderItemRepository.save(existingOrder);
//
//        // Log the updated Order
//        logger.info("Order updated with success: {}", savedOrder);
//
//        return savedOrder;
//    }

    @Override
    @Transactional
    public boolean deleteOrderItemById(long id) {
        Optional<OrderItem> orderItem = orderItemRepository.findById(id);
        if (orderItem.isEmpty()) {
            throw new ObjectWithIdNotFound("orderItem with id " + id + NOT_FOUND);
        }
        orderItem.ifPresent(orderItemRepository::delete);
        logger.info("Delete orderItem with id: {}", id);
        return true;
    }
}
