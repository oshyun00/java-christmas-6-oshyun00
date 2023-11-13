package christmas.domain.benefit.benefitcondition;

import christmas.domain.Order;
import java.text.DecimalFormat;

public class ChristmasDDayCondition implements BenefitCondition {
    private boolean isSatisfied = false;
    private static final int MINIMUM_BENEFIT_VALUE = -1000;
    private static final int DAY_BENEFIT_VALUE = -100;
    private static final int LAST_DAY_OF_BENEFIT = 25;
    private static final String DEFAULT_MESSAGE = "크리스마스 디데이 할인: ";


    @Override
    public void checkDiscountCondition(int date, Order order) {
        if (date <= LAST_DAY_OF_BENEFIT) {
            setSatisfied(true);
        }
    }

    @Override
    public int calculateBenefit(int date, Order order) {
        return MINIMUM_BENEFIT_VALUE + DAY_BENEFIT_VALUE * (date - 1);
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
