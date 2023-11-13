package christmas.domain.benefit.benefitcondition;

import christmas.domain.Order;
import java.text.DecimalFormat;

public class FreeMenuCondition implements BenefitCondition {
    private boolean isSatisfied = false;
    private static final int MINIMUM_PRICE_FOR_BENEFIT = 120000;
    public static final int BENEFIT_VALUE = -25000;
    private static final String DEFAULT_MESSAGE = "증정 이벤트: ";

    @Override
    public void checkDiscountCondition(int date, Order order) {
        if (order.calculateTotalPriceBeforeEvent() >= MINIMUM_PRICE_FOR_BENEFIT) {
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
        return DEFAULT_MESSAGE;
    }
}
