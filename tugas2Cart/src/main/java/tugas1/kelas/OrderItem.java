package tugas1.kelas;

import javax.persistence.*;

@Entity
@Table(name = "order_item_tbl")
public class OrderItem {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "order_item_id", nullable = false)
    private int id;

    @ManyToOne
    @JoinColumn(name = "product_ordered", nullable = false) // Specify the foreign key column here
    private Product product;

    @ManyToOne
    @JoinColumn(name = "ordered_by", nullable = false) // Specify the foreign key column here
    private Order order;

    private int quantity;

    // Default constructor
    public OrderItem() {
    }

    public OrderItem(Product product, int quantity, Order order) {
        this.product = product;
        this.quantity = quantity;
        this.order = order; // Include the order parameter
    }

    public Product getProduct() {
        return product;
    }

    public Order getOrder() {
        return order; // Added getter for order
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public void setOrder(Order order) { // Added setter for order
        this.order = order;
    }
}