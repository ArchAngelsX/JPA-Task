package tugas1.kelas;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "order_tbl")
public class Order {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "order_id", nullable = false)
    private int id;

    @OneToMany
    private List<OrderItem> items = new ArrayList<>();

    // Default constructor
    public Order() {
    }

    public void addItem(Product product, int quantity) {
        for (OrderItem item : items) {
            if (item.getProduct().getId() == product.getId()) {
                // Update quantity if product already exists in the order
                item.setQuantity(item.getQuantity() + quantity);
                return;
            }
        }
        // Add new product to the order
        items.add(new OrderItem(product, quantity, this));
    }

    public List<OrderItem> getItems() {
        return items;
    }

    public double getTotal() {
        double total = 0;
        for (OrderItem item : items) {
            total += item.getProduct().getPrice() * item.getQuantity();
        }
        return total;
    }

    public void clear() {
        items.clear();
    }

    // Getters and Setters...
}