package christmas.domain.benefit.benefitcondition;

import christmas.domain.Order;
import java.text.DecimalFormat;

public class FreeMenuCondition implements BenefitCondition {
    private boolean isSatisfied = false;
    private static final int MIN_BENEFIT_PRICE = 120000;
    private static final int BENEFIT_VALUE = -25000;

    @Override
    public void checkDiscountCondition(int date, Order order) {
        if (order.calculateTotalPriceBeforeEvent() >= MIN_BENEFIT_PRICE) {
            setSatisfied(true);
        }
    }

    @Override
    public int calculateBenefit(int date, Order order) {
        if (isSatisfied) {
            return BENEFIT_VALUE;
        }
        return 0;
    }

    @Override
    public boolean isSatisfied() {
        return isSatisfied;
    }

    @Override
    public void setSatisfied(boolean satisfied) {
        isSatisfied = satisfied;
    }

    @Override
    public String printBenefit(int date, Order order) {
        DecimalFormat decimalFormat = new DecimalFormat("###,###");
        return decimalFormat.format(calculateBenefit(date, order));
    }

    @Override
    public String printDefaultMessage() {
        return "증정 이벤트: ";
    }
}
