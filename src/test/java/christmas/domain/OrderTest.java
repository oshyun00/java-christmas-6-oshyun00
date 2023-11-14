package christmas.domain;

import static org.assertj.core.api.Assertions.assertThat;

import christmas.domain.product.Product;
import christmas.domain.product.subproduct.Appetizer;
import christmas.domain.product.subproduct.Dessert;
import christmas.domain.product.subproduct.Drink;
import christmas.domain.product.subproduct.MainDish;
import java.util.HashMap;
import java.util.Map;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class OrderTest {
    Map<Product, Integer> orderMenu;
    Order order;

    @BeforeEach
    void init() {
        orderMenu = new HashMap<>();
        orderMenu.put(MainDish.of("티본스테이크", 55000), 2);
        orderMenu.put(Appetizer.of("양송이수프", 6000), 1);
        orderMenu.put(Dessert.of("초코케이크", 15000), 3);
        orderMenu.put(Drink.of("제로콜라", 3000), 4);

        order = new Order(orderMenu);
    }

    @DisplayName("주문한 메뉴명과 개수를 출력하는지 확인한다.")
    @Test
    void testPrintOrder() {
        assertThat(order.printOrder())
                .contains(
                        "티본스테이크 2개",
                        "양송이수프 1개",
                        "초코케이크 3개",
                        "제로콜라 4개"
                );
    }

    @DisplayName("할인 전 금액을 올바르게 계산하는지 확인한다.")
    @Test
    void TestCalculateTotalPriceBeforeEvent() {
        assertThat(order.calculateTotalPriceBeforeEvent())
                .isEqualTo(173000);
    }

    @DisplayName("메인요리 개수를 리턴한다.")
    @Test
    void testCountMainDish() {
        assertThat(order.countMainDish())
                .isEqualTo(2);
    }

    @DisplayName("디저트 개수를 리턴한다.")
    @Test
    void testCountDessert() {
        assertThat(order.countDessert())
                .isEqualTo(3);
    }
}