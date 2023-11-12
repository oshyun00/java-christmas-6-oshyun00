package christmas.benefit.benefitcondition;

import christmas.domain.Order;
import java.text.DecimalFormat;
import java.time.LocalDate;

public class WeekendCondition implements BenefitCondition {
    private boolean isSatisfied = false;
    DecimalFormat decimalFormat = new DecimalFormat("###,###");

    @Override
    public void checkDiscountCondition(int date, int totalPrice, Order order) {
        LocalDate localDate = LocalDate.of(2023, 12, date);
        int dayOfWeekNumber = localDate.getDayOfWeek().getValue();

        if (order.countMainDish() > 0 && (dayOfWeekNumber == 5 || dayOfWeekNumber == 6)) {
            setSatisfied(true);
        }
    }

    @Override
    public int calculateBenefit(int date, Order order) {
        int numberOfMainDish = order.countMainDish();
        return -(numberOfMainDish * 2023);
    }

    public String printBenefit(int date, Order order) {
        return decimalFormat.format(calculateBenefit(date, order));
    }

    @Override
    public String printDefaultMessage() {
        return "주말 할인: ";
    }

    @Override
    public boolean isSatisfied() {
        return isSatisfied;
    }

    private void setSatisfied(boolean satisfied) {
        isSatisfied = satisfied;
    }
}
