package christmas.domain.product.subproduct;

import christmas.domain.product.Product;

public class MainDish extends Product {
    private MainDish(String name, int price) {
        super(name, price);
    }

    public static MainDish of(String name, int price) {
        return new MainDish(name, price);
    }
}
