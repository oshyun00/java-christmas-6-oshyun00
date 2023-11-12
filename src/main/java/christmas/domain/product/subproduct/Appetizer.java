package christmas.domain.product.subproduct;

import christmas.domain.product.Product;

public class Appetizer extends Product {
    private Appetizer(String name, int price) {
        super(name, price);
    }

    public static Appetizer of(String name, int price) {
        return new Appetizer(name, price);
    }
}
