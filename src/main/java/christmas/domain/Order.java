package christmas.domain;

import christmas.domain.product.Product;
import java.util.Map;

public class Order {
    private final Map<Product, Integer> orders;

    public Order(Map<Product, Integer> orders) {
        this.orders = orders;
    }
}
