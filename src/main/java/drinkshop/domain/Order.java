package drinkshop.domain;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class Order implements Serializable {

    private int id;
    private List<OrderItem> items;
    private double totalPrice;

    public Order(int id) {
        this.id = id;
        this.items = new ArrayList<>();
        this.totalPrice = 0.0;
    }

    public Order(int id, List<OrderItem> items) {
        this.id = id;
        this.items = new ArrayList<>(items);
        this.totalPrice = totalPrice;
        computeTotalPrice();
    }

    public int getId() {
        return id;
    }

    public List<OrderItem> getItems() {
        return new ArrayList<>(items);
    }

    public double getTotalPrice() {
        return totalPrice;
    }

    public void setItems(List<OrderItem> items) {
        this.items = new ArrayList<>(items);
        computeTotalPrice();
    }

    public void addItem(OrderItem item) {
        this.items.add(item);
        computeTotalPrice();
    }

    public void removeItem(OrderItem item) {
        this.items.remove(item);
        computeTotalPrice();
    }

    @Override
    public String toString() {
        return "Order{" +
                "id=" + id +
                ", items=" + items +
                ", totalPrice=" + totalPrice +
                '}';
    }

    public double getTotal() {
        return totalPrice;
    }

    public void computeTotalPrice() {
        this.totalPrice=items.stream().mapToDouble(OrderItem::getTotal).sum();
    }
}