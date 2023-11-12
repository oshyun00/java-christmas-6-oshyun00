package christmas.benefit.benefitcondition;

import christmas.domain.Order;

public interface BenefitCondition {
    void checkDiscountCondition(int date, int totalPrice, Order order);

    int calculateBenefit(int date, Order order);

    boolean isSatisfied();
}
