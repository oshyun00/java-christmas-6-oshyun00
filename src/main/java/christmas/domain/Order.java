package christmas.domain;

import christmas.domain.product.Product;
import java.util.Map;
import java.util.StringJoiner;

public class Order {
    private final Map<Product, Integer> orders;

    public Order(Map<Product, Integer> orders) {
        this.orders = orders;
    }

    // 주문한 메뉴 목록 출력
    public String printOrder(){
        StringJoiner stringJoiner = new StringJoiner("\n");
        for (Map.Entry<Product, Integer> entry : orders.entrySet()) {
            Product product = entry.getKey();
            int quantity = entry.getValue();

            String productName = product.getName();

            stringJoiner.add(productName + " " + quantity + "개");
        }
        return stringJoiner.toString();
    }
}
