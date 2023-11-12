package christmas.domain;

import christmas.domain.product.Product;
import christmas.domain.product.subproduct.Dessert;
import christmas.domain.product.subproduct.MainDish;
import java.util.Map;
import java.util.StringJoiner;

public class Order {
    private final Map<Product, Integer> orders;

    public Order(Map<Product, Integer> orders) {
        this.orders = orders;
    }

    // 주문한 메뉴 목록 출력
    public String printOrder() {
        StringJoiner stringJoiner = new StringJoiner("\n");
        for (Map.Entry<Product, Integer> entry : orders.entrySet()) {
            Product product = entry.getKey();
            int quantity = entry.getValue();

            String productName = product.getName();

            stringJoiner.add(productName + " " + quantity + "개");
        }
        return stringJoiner.toString();
    }

    public int calculateTotalPriceBeforeEvent() {
        int price = 0;
        for (Map.Entry<Product, Integer> entry : orders.entrySet()) {
            Product product = entry.getKey();
            int quantity = entry.getValue();

            int eachPrice = product.getPrice();
            price += eachPrice * quantity;
        }
        return price;
    }

    public int countMainDish() {
        int count = 0;
        for (Map.Entry<Product, Integer> entry : orders.entrySet()) {
            Product product = entry.getKey();
            if (product instanceof MainDish) {
                count += entry.getValue();
            }
        }
        return count;
    }

    public int countDessert() {
        int count = 0;
        for (Map.Entry<Product, Integer> entry : orders.entrySet()) {
            Product product = entry.getKey();
            if (product instanceof Dessert) {
                count += entry.getValue();
            }
        }
        return count;
    }
}
