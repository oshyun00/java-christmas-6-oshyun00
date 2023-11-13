package christmas.domain.benefit.benefitcondition;

import christmas.domain.Order;
import java.text.DecimalFormat;
import java.time.LocalDate;

public class WeekdayCondition implements BenefitCondition {
    private boolean isSatisfied = false;
    private static final int BENEFIT_VALUE = 2023;
    private static final String DEFAULT_MESSAGE = "평일 할인: ";

    @Override
    public void checkDiscountCondition(int date, Order order) {
        LocalDate localDate = LocalDate.of(2023, 12, date);
        int dayOfWeekNumber = localDate.getDayOfWeek().getValue();

        if (order.countDessert() > 0 && (dayOfWeekNumber == 7 || dayOfWeekNumber < 5)) {
            setSatisfied(true);
        }
    }

    @Override
    public int calculateBenefit(int date, Order order) {
        int numberOfDessert = order.countDessert();
        return -(numberOfDessert * BENEFIT_VALUE);
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
