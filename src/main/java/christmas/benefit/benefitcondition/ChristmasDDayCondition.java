package christmas.benefit.benefitcondition;

import christmas.domain.Order;
import java.text.DecimalFormat;

public class ChristmasDDayCondition implements BenefitCondition {
    private boolean isSatisfied = false;
    DecimalFormat decimalFormat = new DecimalFormat("###,###");

    @Override
    public void checkDiscountCondition(int date, int totalPrice, Order order) {
        if (date < 26) {
            setSatisfied(true);
        }
    }

    @Override
    public int calculateBenefit(int date, Order order) {
        return -(1000 + 100 * (date - 1));
    }

    public String printBenefit(int date, Order order) {
        return decimalFormat.format(calculateBenefit(date, order));
    }

    @Override
    public String printDefaultMessage() {
        return "크리스마스 디데이 할인: ";
    }

    @Override
    public boolean isSatisfied() {
        return isSatisfied;
    }

    private void setSatisfied(boolean satisfied) {
        isSatisfied = satisfied;
    }
}
