package christmas.domain;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import christmas.domain.benefit.benefitcondition.BenefitCondition;
import christmas.domain.benefit.benefitcondition.ChristmasDDayCondition;
import christmas.domain.benefit.benefitcondition.FreeMenuCondition;
import christmas.domain.benefit.benefitcondition.SpecialCondition;
import christmas.domain.benefit.benefitcondition.WeekdayCondition;
import christmas.domain.benefit.benefitcondition.WeekendCondition;
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
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

public class BenefitConditionTest {
    BenefitCondition benefitCondition;
    Order order;

    @BeforeEach
    void init() {
        Map<Product, Integer> orderMenu = new HashMap<>();
        orderMenu.put(MainDish.of("티본스테이크", 55000), 2);
        orderMenu.put(Appetizer.of("양송이수프", 6000), 1);
        orderMenu.put(Dessert.of("초코케이크", 15000), 3);
        orderMenu.put(Drink.of("제로콜라", 3000), 4);

        order = new Order(orderMenu);
    }

    @DisplayName("날짜별 크리스마스 할인 적용여부 확인")
    @ParameterizedTest
    @CsvSource(value = {"ChristmasDDayCondition"})
    void testChristmasDDayCondition(int date, boolean result) {
        benefitCondition = new ChristmasDDayCondition();
        benefitCondition.checkDiscountCondition(date, order);
        assertEquals(benefitCondition.isSatisfied(), result);
    }

    @DisplayName("주문 금액에 따른 증정 제품 적용여부 확인")
    @Test
    void testFreeMenuCondition() {
        benefitCondition = new FreeMenuCondition();
        benefitCondition.checkDiscountCondition(1, order);
        assertTrue(benefitCondition.isSatisfied());
    }

    @DisplayName("날짜별 특별 할인 적용여부 확인")
    @ParameterizedTest
    @CsvSource(value = {"3,true", "25,true", "26,false"})
    void testSpecialCondition(int date, boolean result) {
        benefitCondition = new SpecialCondition();
        benefitCondition.checkDiscountCondition(date, order);
        assertEquals(benefitCondition.isSatisfied(), result);
    }

    @DisplayName("날짜별 평일 할인 적용여부 확인")
    @ParameterizedTest
    @CsvSource(value = {"1,false", "10,true", "16,false"})
    void testWeekdayCondition(int date, boolean result) {
        benefitCondition = new WeekdayCondition();
        benefitCondition.checkDiscountCondition(date, order);
        assertEquals(benefitCondition.isSatisfied(), result);
    }

    @DisplayName("날짜별 주말 할인 적용여부 확인")
    @ParameterizedTest
    @CsvSource(value = {"1,true", "10,false", "16,true"})
    void testWeekendCondition(int date, boolean result) {
        benefitCondition = new WeekendCondition();
        benefitCondition.checkDiscountCondition(date, order);
        assertEquals(benefitCondition.isSatisfied(), result);
    }
}
