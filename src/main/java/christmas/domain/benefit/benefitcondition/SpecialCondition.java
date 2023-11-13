package christmas.domain.benefit.benefitcondition;

import christmas.domain.Order;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class SpecialCondition implements BenefitCondition {
    private boolean isSatisfied = false;
    DecimalFormat decimalFormat = new DecimalFormat("###,###");

    @Override
    public void checkDiscountCondition(int date, Order order) {
        int[] specialDays = new int[]{3,10,17,24,25,31};
        List<Integer> specialDay = new ArrayList<>(Arrays.stream(specialDays).boxed().toList());

        if(specialDay.contains(date)){
            setSatisfied(true);
        }
    }

    @Override
    public int calculateBenefit(int date, Order order) {
        return -1000;
    }

    public String printBenefit(int date, Order order) {
        return decimalFormat.format(calculateBenefit(date, order));
    }

    @Override
    public String printDefaultMessage() {
        return "특별 할인: ";
    }

    @Override
    public boolean isSatisfied() {
        return isSatisfied;
    }

    private void setSatisfied(boolean satisfied) {
        isSatisfied = satisfied;
    }
}
