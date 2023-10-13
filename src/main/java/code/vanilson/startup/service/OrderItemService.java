package code.vanilson.startup.service;

import code.vanilson.startup.model.OrderItem;

import java.util.List;
import java.util.Optional;

public interface OrderItemService {
    /**
     * Returns the orderItem with the specified id.
     *
     * @param id ID of the orderItem to retrieve.
     * @return The requested orderItem if found.
     */
    Optional<OrderItem> findOrderItemById(Long id);

    /**
     * Returns all orderItems in the database.
     *
     * @return All orderItems in the database.
     */
    List<OrderItem> findAllOrderItems();

    /**
     * Updates the specified orderItem, identified by its id.
     *
     * @param orderItem The orderItem to update.
     * @return True if the update succeeded, otherwise false.
     */
//    OrderItem updateOrderItem(long id, OrderItem orderItem);

    /**
     * Saves the specified orderItem to the database.
     *
     * @param orderItem The orderItem to save to the database.
     * @return The saved orderItem.
     */
    OrderItem saveOrderItem(OrderItem orderItem);

    /**
     * Deletes the orderItem with the specified id.
     *
     * @param id The id of the orderItem to delete.
     * @return True if the operation was successful.
     */
    boolean deleteOrderItemById(long id);
}
