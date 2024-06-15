package code.vanilson.marketplace.service;

import code.vanilson.marketplace.model.Order;

import java.util.List;
import java.util.Optional;

public interface OrderService {
    /**
     * Returns the order with the specified id.
     *
     * @param id ID of the order to retrieve.
     * @return The requested order if found.
     */
    Optional<Order> findOrderById(Long id);

    /**
     * Returns all orders in the database.
     *
     * @return All orders in the database.
     */
    List<Order> findAllOrders();

    /**
     * Updates the specified order, identified by its id.
     *
     * @param order The order to update.
     * @return True if the update succeeded, otherwise false.
     */
    Order updateOrder(long id, Order order);

    /**
     * Saves the specified order to the database.
     *
     * @param order The order to save to the database.
     * @return The saved order.
     */
    Order saveOrder(Order order);

    /**
     * Deletes the order with the specified id.
     *
     * @param id The id of the order to delete.
     * @return True if the operation was successful.
     */
    boolean deleteOrderById(long id);
}
