package christmas.domain.benefit.benefitcondition;

import christmas.domain.Order;
import java.text.DecimalFormat;
import java.time.LocalDate;

public class WeekendCondition implements BenefitCondition {
    private boolean isSatisfied = false;
    private static final int BENEFIT_VALUE = 2023;

    @Override
    public void checkDiscountCondition(int date, Order order) {
        LocalDate localDate = LocalDate.of(2023, 12, date);
        int dayOfWeekNumber = localDate.getDayOfWeek().getValue();

        if (order.countMainDish() > 0 && (dayOfWeekNumber == 5 || dayOfWeekNumber == 6)) {
            setSatisfied(true);
        }
    }

    @Override
    public int calculateBenefit(int date, Order order) {
        int numberOfMainDish = order.countMainDish();
        return -(numberOfMainDish * BENEFIT_VALUE);
    }

    @Override
    public boolean isSatisfied() {
        return isSatisfied;
    }

    @Override
    public void setSatisfied(boolean satisfied) {
        isSatisfied = satisfied;
    }

    public String printBenefit(int date, Order order) {
        DecimalFormat decimalFormat = new DecimalFormat("###,###");
        return decimalFormat.format(calculateBenefit(date, order));
    }

    @Override
    public String printDefaultMessage() {
        return "주말 할인: ";
    }
}
