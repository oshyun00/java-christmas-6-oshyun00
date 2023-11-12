package christmas.benefit.benefitcondition;

public interface BenefitCondition {
    void checkDiscountCondition(int date, int totalPrice);
    int applyDiscount(int totalPrice);
    boolean isSatisfied();
}
