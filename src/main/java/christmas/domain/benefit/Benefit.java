package christmas.domain.benefit;

import christmas.domain.Order;
import christmas.domain.benefit.benefitcondition.BenefitCondition;
import christmas.domain.benefit.benefitcondition.FreeMenuCondition;

public class Benefit {
    BenefitCondition[] benefitConditions;

    public Benefit(BenefitCondition[] benefitConditions) {
        this.benefitConditions = benefitConditions;
    }

    public boolean checkNoBenefit() {
        for (BenefitCondition benefitCondition : benefitConditions) {
            if (benefitCondition.isSatisfied()) {
                return false;
            }
        }
        return true;
    }

    public void checkBenefitCondition(int date, Order order) {
        for (BenefitCondition benefitCondition : benefitConditions) {
            benefitCondition.checkDiscountCondition(date, order);
        }
    }

    public int getTotalBenefit(int date, Order order) {
        int totalBenefit = 0;
        for (BenefitCondition benefitCondition : benefitConditions) {
            if (benefitCondition.isSatisfied()) {
                totalBenefit += benefitCondition.calculateBenefit(date, order);
            }
        }
        return totalBenefit;
    }

    public int getFinalBenefitAmount(int date, Order order) {
        int totalBenefit = getTotalBenefit(date, order);
        if (hasFreeMenu()) {
            totalBenefit -= FreeMenuCondition.BENEFIT_VALUE;
        }
        return totalBenefit;
    }

    public boolean hasFreeMenu() {
        for (BenefitCondition benefitCondition : benefitConditions) {
            if (benefitCondition instanceof FreeMenuCondition) {
                return benefitCondition.isSatisfied();
            }
        }
        return false;
    }

    public String printBenefits(int date, Order order) {
        StringBuilder stringBuilder = new StringBuilder();
        for (BenefitCondition benefitCondition : benefitConditions) {
            if (benefitCondition.isSatisfied()) {
                stringBuilder.append(benefitCondition.printDefaultMessage())
                        .append(benefitCondition.printBenefit(date, order))
                        .append("원\n");
            }
        }
        return stringBuilder.toString();
    }
}
