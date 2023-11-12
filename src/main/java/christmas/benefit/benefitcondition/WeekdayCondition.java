package christmas.benefit.benefitcondition;

import christmas.domain.Order;
import java.text.DecimalFormat;
import java.time.LocalDate;

public class WeekdayCondition implements BenefitCondition {
    private boolean isSatisfied = false;
    DecimalFormat decimalFormat = new DecimalFormat("###,###");

    @Override
    public void checkDiscountCondition(int date, int totalPrice, Order order) {
        LocalDate localDate = LocalDate.of(2023, 12, date);
        int dayOfWeekNumber = localDate.getDayOfWeek().getValue();

        if (order.countDessert() >0 &&(dayOfWeekNumber == 7 || dayOfWeekNumber < 5 )) {
            setSatisfied(true);
        }
    }

    @Override
    public int calculateBenefit(int date, Order order) {
        int numberOfDessert = order.countDessert();
        return -(numberOfDessert * 2023);
    }

    public String printBenefit(int date, Order order) {
        return decimalFormat.format(calculateBenefit(date, order));
    }

    @Override
    public boolean isSatisfied() {
        return isSatisfied;
    }

    private void setSatisfied(boolean satisfied) {
        isSatisfied = satisfied;
    }
}
