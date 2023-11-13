package christmas.domain.benefit.benefitcondition;

import christmas.domain.Order;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class SpecialCondition implements BenefitCondition {
    private boolean isSatisfied = false;
    private static final int BENEFIT_VALUE = -1000;
    private static final int[] SPECIAL_DAYS = new int[]{3, 10, 17, 24, 25, 31};
    private static final String DEFAULT_MESSAGE = "특별 할인: ";

    @Override
    public void checkDiscountCondition(int date, Order order) {
        List<Integer> specialDay = new ArrayList<>(Arrays.stream(SPECIAL_DAYS).boxed().toList());

        if (specialDay.contains(date)) {
            setSatisfied(true);
        }
    }

    @Override
    public int calculateBenefit(int date, Order order) {
        return BENEFIT_VALUE;
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
