package christmas.product.subproduct;

import christmas.product.Product;

public class Drink extends Product {
    private Drink(String name, int price) {
        super(name, price);
    }

    public static Drink of(String name, int price) {
        return new Drink(name, price);
    }
}
