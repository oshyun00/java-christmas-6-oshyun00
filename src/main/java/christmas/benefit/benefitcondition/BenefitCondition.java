package christmas.benefit.benefitcondition;

import christmas.domain.Order;

public interface BenefitCondition {
    static String MESSAGE = null;
    void checkDiscountCondition(int date, int totalPrice, Order order);

    int calculateBenefit(int date, Order order);

    boolean isSatisfied();
    String printBenefit(int date, Order order);
    String printDefaultMessage();
}
