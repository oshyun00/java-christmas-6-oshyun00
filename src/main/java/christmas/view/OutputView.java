package christmas.view;

import christmas.benefit.benefitcondition.ChristmasDDayCondition;
import christmas.benefit.benefitcondition.FreeMenuCondition;
import christmas.util.ConstantUtils;
import java.text.DecimalFormat;

public class OutputView {
    public void printWelcomeMessage() {
        System.out.println(ConstantUtils.WELCOME_MESSAGE);
    }

    public void printEventPreviewMessage(int date) {
        System.out.printf(ConstantUtils.EVENT_PREVIEW_MESSAGE, date);
        System.out.printf(ConstantUtils.NEW_LINE);
    }

    public void printOrderMenu(String orders) {
        System.out.println(ConstantUtils.ORDER_MENU_TITLE);
        System.out.println(orders);
        System.out.printf(ConstantUtils.NEW_LINE);
    }

    public void printTotalPriceBeforeBenefit(int calculateTotalPriceBeforeEvent) {
        System.out.println(ConstantUtils.TOTAL_PRICE_BEFORE_BENEFIT_TITLE);
        DecimalFormat decimalFormat = new DecimalFormat("###,###");
        String totalPrice = decimalFormat.format(calculateTotalPriceBeforeEvent);
        System.out.printf(ConstantUtils.TOTAL_PRICE_BEFORE_BENEFIT, totalPrice);
        System.out.printf(ConstantUtils.NEW_LINE);
    }

    public void printFreeMenu(FreeMenuCondition freeMenuCondition) {
        System.out.println(ConstantUtils.FREE_MENU_TITLE);
        if (freeMenuCondition.isSatisfied()) {
            System.out.println(ConstantUtils.FREE_MENU);
        }
        if (!freeMenuCondition.isSatisfied()) {
            System.out.println(ConstantUtils.NOT_FREE_MENU);
        }
    }

    public void printBenefit(int date, ChristmasDDayCondition christmasDDayCondition,
                             FreeMenuCondition freeMenuCondition) {
        System.out.println(ConstantUtils.DISCOUNT_TITLE);
        if (christmasDDayCondition.isSatisfied()) {
            System.out.printf("크리스마스 디데이 할인: %s원\n", christmasDDayCondition.printBenefit(date));
        }
        if (freeMenuCondition.isSatisfied()) {
            System.out.printf("증정 이벤트: %s원\n", freeMenuCondition.calculateBenefit(date));
        }

    }
}
