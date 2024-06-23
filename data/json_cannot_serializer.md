This warning typically arises due to Jackson's inability to handle bidirectional references properly, especially when
managed/back references are misconfigured or not applied correctly.

### Understanding `@JsonManagedReference` and `@JsonBackReference`

- **`@JsonManagedReference`**: Used on the parent side of a bidirectional relationship to indicate that it manages the
  serialization of the child entity. Jackson will include this reference during serialization.

- **`@JsonBackReference`**: Used on the child side of a bidirectional relationship to ignore the serialization of the
  parent entity. This annotation helps break the circular reference during serialization.

### Resolving the Issue

In your `Order` and `OrderItem` classes, adjustments were made to correctly apply these annotations:

#### Order.java

```java

@Entity
public class Order {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long orderId;

    private LocalDateTime localDateTime;

    @ManyToOne
    @JoinColumn(name = "customer_id")
    private Customer customer;

    @OneToMany(mappedBy = "order", cascade = CascadeType.ALL, orphanRemoval = true)
    @JsonManagedReference // Indicates that Order manages serialization of OrderItem
    private Set<OrderItem> orderItems = new HashSet<>();

    // Getters and setters
}

```

## Explanation of Changes

### Correct Annotation Usage:

- **`@JsonManagedReference`**: was placed on the orderItems field in Order class to indicate that Order manages the
  serialization of OrderItem.
- **`@JsonBackReference`**: was placed on the order field in OrderItem class to ignore the serialization of Order, thus
  preventing circular reference issues.

### Breaking Circular References:

- **`By using @JsonBackReference`**, you ensured that Jackson does not attempt to serialize Order when serializing
  OrderItem. This prevents the infinite loop that can occur when serializing bidirectional relationships.
  Resulting Outcome:

After applying these annotations correctly, Jackson was able to serialize and deserialize your Order and OrderItem
entities without encountering the previous warning related to managed/back references.