package christmas.benefit.benefitcondition;

public interface BenefitCondition {
    void checkDiscountCondition(int date, int totalPrice);
    int calculateBenefit(int date);
    boolean isSatisfied();
}
