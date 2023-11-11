package christmas.product.subproduct;

import christmas.product.Product;

public class MainDish extends Product {
    private MainDish(String name, int price) {
        super(name, price);
    }

    public static MainDish of(String name, int price) {
        return new MainDish(name, price);
    }
}
