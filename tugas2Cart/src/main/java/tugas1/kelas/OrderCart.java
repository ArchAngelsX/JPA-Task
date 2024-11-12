package tugas1.kelas;

import java.util.List;

public class OrderCart {
    private Order order = new Order();

    public void addItem(Product product, int quantity) {
        order.addItem(product, quantity);
    }

    public List<OrderItem> getItems() {
        return order.getItems();
    }

    public double getTotal() {
        return order.getTotal();
    }

    public void clear() {
        order.clear();
    }
}