package christmas.domain.benefit.benefitcondition;

import christmas.domain.Order;
import java.text.DecimalFormat;

public class FreeMenuCondition implements BenefitCondition {
    private boolean isSatisfied = false;

    @Override
    public void checkDiscountCondition(int date, int totalPrice, Order order) {

        if (totalPrice >= 120000) {
            setSatisfied(true);
        }
    }

    @Override
    public int calculateBenefit(int date, Order order) {
        if (isSatisfied) {
            return -25000;
        }
        return 0;
    }

    public String printBenefit(int date, Order order) {
        DecimalFormat decimalFormat = new DecimalFormat("###,###");
        return decimalFormat.format(calculateBenefit(date, order));
    }

    @Override
    public String printDefaultMessage() {
        return "증정 이벤트: ";
    }

    @Override
    public boolean isSatisfied() {
        return isSatisfied;
    }

    private void setSatisfied(boolean satisfied) {
        isSatisfied = satisfied;
    }

}
