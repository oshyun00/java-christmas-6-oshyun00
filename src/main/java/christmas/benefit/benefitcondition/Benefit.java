package christmas.benefit.benefitcondition;

import christmas.domain.Order;

public class Benefit {
    BenefitCondition[] benefitConditions;

    public Benefit(BenefitCondition[] benefitConditions) {
        this.benefitConditions = benefitConditions;
    }

    public boolean noBenefit() {
        for (BenefitCondition benefitCondition : benefitConditions) {
            if (benefitCondition.isSatisfied()) {
                return false;
            }
        }
        return true;
    }

    public void checkBenefit(int date, int totalPriceBeforeEvent, Order order) {
        for (BenefitCondition benefitCondition : benefitConditions) {
            benefitCondition.checkDiscountCondition(date, totalPriceBeforeEvent, order);
        }
    }

    public int getTotalBenefit(int date, Order order){
        int totalBenefit = 0;
        for (BenefitCondition benefitCondition : benefitConditions) {
            if (benefitCondition.isSatisfied()) {
                totalBenefit += benefitCondition.calculateBenefit(date, order);
            }
        }
        return totalBenefit;
    }

    public String getBenefits(int date, Order order) {
        StringBuilder stringBuilder = new StringBuilder();
        for (BenefitCondition benefitCondition : benefitConditions) {
            if (benefitCondition.isSatisfied()) {
                stringBuilder.append(benefitCondition.printDefaultMessage()).append(benefitCondition.printBenefit(date, order))
                        .append("원\n");
            }
        }
        return stringBuilder.toString();
    }
}
