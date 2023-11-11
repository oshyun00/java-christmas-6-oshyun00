package christmas.product;

import christmas.product.subproduct.Appetizer;
import christmas.product.subproduct.Dessert;
import christmas.product.subproduct.Drink;
import christmas.product.subproduct.MainDish;

public class ProductRepository {
    private final Product[] products = {
            Appetizer.of("양송이수프", 6000),
            Appetizer.of("타파스", 5500),
            Appetizer.of("시저샐러드", 8000),
            MainDish.of("티본스테이크", 55000),
            MainDish.of("바비큐립", 54000),
            MainDish.of("해산물파스타", 35000),
            MainDish.of("크리스마스파스타", 25000),
            Dessert.of("초코케이크", 15000),
            Dessert.of("아이스크림", 5000),
            Drink.of("제로콜라", 3000),
            Drink.of("레드와인", 60000),
            Drink.of("샴페인", 25000)
    };
}
