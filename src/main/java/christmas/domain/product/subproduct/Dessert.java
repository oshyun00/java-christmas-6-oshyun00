package christmas.domain.product.subproduct;

import christmas.domain.product.Product;

public class Dessert extends Product {
    private Dessert(String name, int price) {
        super(name, price);
    }

    public static Dessert of(String name, int price) {
        return new Dessert(name, price);
    }
}
