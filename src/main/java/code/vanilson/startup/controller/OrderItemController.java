package code.vanilson.startup.controller;

import code.vanilson.startup.model.OrderItem;
import code.vanilson.startup.service.OrderItemServiceImpl;
import jakarta.validation.Valid;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.Optional;

@RestController
@RequestMapping("/api/orderItems")
public class OrderItemController {

    public static final String ORDER = "/orderItems/";
    private static final Logger logger = LogManager.getLogger(OrderItemController.class);
    private final OrderItemServiceImpl orderItemService;

    public OrderItemController(OrderItemServiceImpl orderItemService) {
        this.orderItemService = orderItemService;
    }

    /**
     * Returns all orderItems in the database.
     *
     * @return All orderItems in the database.
     */
    @GetMapping
    public ResponseEntity<Iterable<OrderItem>> getOrderItems() {
        return ResponseEntity.ok().body(orderItemService.findAllOrderItems());
    }

    /**
     * Returns the orderItem with the specified ID.
     *
     * @param id The ID of the orderItem to retrieve.
     * @return The orderItem with the specified ID.
     */
    @GetMapping("/{id}")
    public ResponseEntity<Optional<OrderItem>> getOrderItemById(@PathVariable Long id) {
        return ResponseEntity.ok()
                .body(orderItemService.findOrderItemById(id));
    }

    /**
     * Creates a new orderItem.
     *
     * @param orderItem The orderItem to create.
     * @return The created orderItem.
     */
    @PostMapping("/create")
    public ResponseEntity<OrderItem> createOrderItem(@Valid @RequestBody OrderItem orderItem) {
        logger.info("Creating new orderItem with Order: {}, product: {}, quantity: {}",
                orderItem.getOrder(),
                orderItem.getProduct(),
                orderItem.getQuantity());

        // Create a new orderItem
        OrderItem newOrderItem = orderItemService.saveOrderItem(orderItem);

        try {
            // Build a created response
            return ResponseEntity
                    .created(new URI(ORDER + newOrderItem.getOrderItemId()))
                    .body(newOrderItem);
        } catch (URISyntaxException e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

//    /**
//     * Updates the fields in the specified order with the specified ID.
//     */
//    @PutMapping("/update/{id}")
//    public ResponseEntity<OrderItem> updateOrderItem(@Valid @RequestBody OrderItem order,
//                                                     @PathVariable Long id) {
//        OrderItem orderItems = orderItemService.updateOrderItem(id, order);
//        return ResponseEntity.ok().body(orderItems);
//    }

    /**
     * Deletes the orderItem with the specified ID.
     *
     * @param id The ID of the orderItem to delete.
     * @return A ResponseEntity with one of the following status codes:
     * 200 OK if to delete was successful
     * 404 Not Found if a orderItem with the specified ID is not found
     * 500 Internal Service Error if an error occurs during deletion
     */
    @DeleteMapping("/delete/{id}")
    public ResponseEntity<?> deleteOrderItem(@PathVariable Long id) {

        logger.info("Deleting orderItem with ID {}", id);

        // Get the existing orderItem
        Optional<OrderItem> existingOrderItem = orderItemService.findOrderItemById(id);

        return existingOrderItem.map(p -> {
            if (orderItemService.deleteOrderItemById(p.getOrderItemId())) {
                return ResponseEntity.ok().build();
            } else {
                return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
            }
        }).orElse(ResponseEntity.notFound().build());
    }

}
