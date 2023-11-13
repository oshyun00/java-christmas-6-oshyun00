package christmas.domain.benefit.benefitcondition;

import christmas.domain.Order;

public interface BenefitCondition {

    void checkDiscountCondition(int date, Order order);

    int calculateBenefit(int date, Order order);

    boolean isSatisfied();

    void setSatisfied(boolean satisfied);

    String printBenefit(int date, Order order);

    String printDefaultMessage();
}
