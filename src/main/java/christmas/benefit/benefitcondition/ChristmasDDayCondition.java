package christmas.benefit.benefitcondition;

import java.text.DecimalFormat;

public class ChristmasDDayCondition implements BenefitCondition {
    private boolean isSatisfied = false;
    DecimalFormat decimalFormat = new DecimalFormat("###,###");

    @Override
    public void checkDiscountCondition(int date, int totalPrice) {
        if (date < 26) {
            setSatisfied(true);
        }
    }

    @Override
    public int calculateBenefit(int date) {
        return - (1000 + 100 * (date - 1));
    }

    public String printBenefit(int date){
        return decimalFormat.format(calculateBenefit(date));
    };

    @Override
    public boolean isSatisfied() {
        return isSatisfied;
    }

    private void setSatisfied(boolean satisfied) {
        isSatisfied = satisfied;
    }
}
