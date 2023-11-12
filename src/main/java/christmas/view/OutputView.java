package christmas.view;

import christmas.benefit.benefitcondition.Benefit;
import christmas.benefit.benefitcondition.FreeMenuCondition;
import christmas.domain.Order;
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

    public void printBenefit(int date, Order order, Benefit benefit) {
        System.out.printf(ConstantUtils.NEW_LINE);
        System.out.println(ConstantUtils.DISCOUNT_TITLE);
        if (benefit.noBenefit()) {
            System.out.println("없음");
        }
        System.out.println(benefit.getBenefits(date, order));
    }

    public void printTotalAmountOfBenefit(int benefit) {
        DecimalFormat decimalFormat = new DecimalFormat("###,###");
        String totalAmountOfBenefit = decimalFormat.format(benefit);
        System.out.println(ConstantUtils.TOTAL_BENEFIT_TITLE);
        System.out.printf("%s원\n", totalAmountOfBenefit);
    }

    public void printExpectedPaymentAmount(int price){
        System.out.printf(ConstantUtils.NEW_LINE);
        System.out.println(ConstantUtils.TOTAL_PRICE_AFTER_BENEFIT_TITLE);
        DecimalFormat decimalFormat = new DecimalFormat("###,###");
        String ExpectedPaymentAmount = decimalFormat.format(price);
        System.out.printf("%s원\n", ExpectedPaymentAmount);
    }

    public void printBadge(String badgeName) {
        System.out.printf(ConstantUtils.NEW_LINE);
        System.out.println(ConstantUtils.EVENT_BADGE_TITLE);
        System.out.println(badgeName);
    }
}
