package christmas.view;

import static christmas.util.ConstantUtils.*;

import christmas.domain.Order;
import christmas.domain.benefit.Benefit;
import java.text.DecimalFormat;

public class OutputView {
    DecimalFormat decimalFormat = new DecimalFormat("###,###");

    public void printWelcomeMessage() {
        System.out.println(WELCOME_MESSAGE);
    }

    public void printEventPreviewMessage(int date) {
        System.out.printf(EVENT_PREVIEW_MESSAGE, date);
        System.out.printf(NEW_LINE);
    }

    public void printOrderMenu(String orders) {
        System.out.println(ORDER_MENU_TITLE);
        System.out.println(orders);
        System.out.printf(NEW_LINE);
    }

    public void printTotalPriceBeforeBenefit(int calculateTotalPriceBeforeEvent) {
        System.out.println(TOTAL_PRICE_BEFORE_BENEFIT_TITLE);
        String totalPrice = decimalFormat.format(calculateTotalPriceBeforeEvent);
        System.out.printf(PRICE_UNIT, totalPrice);
        System.out.printf(NEW_LINE);
    }

    public void printFreeMenu(Benefit benefit) {
        System.out.println(FREE_MENU_TITLE);
        if (benefit.hasFreeMenu()) {
            System.out.println(FREE_MENU);
            System.out.printf(NEW_LINE);
        }
        if (!benefit.hasFreeMenu()) {
            System.out.println(NO_BENEFIT);
            System.out.printf(NEW_LINE);
        }
    }

    public void printBenefit(int date, Order order, Benefit benefit) {
        System.out.println(DISCOUNT_TITLE);
        if (benefit.checkNoBenefit()) {
            System.out.println(NO_BENEFIT);
        }
        System.out.println(benefit.printBenefits(date, order));
    }

    public void printTotalAmountOfBenefit(int benefit) {
        String totalAmountOfBenefit = decimalFormat.format(benefit);
        System.out.println(TOTAL_BENEFIT_TITLE);
        System.out.printf(PRICE_UNIT, totalAmountOfBenefit);
        System.out.printf(NEW_LINE);
    }

    public void printExpectedPaymentAmount(int price) {
        System.out.println(TOTAL_PRICE_AFTER_BENEFIT_TITLE);
        String ExpectedPaymentAmount = decimalFormat.format(price);
        System.out.printf(PRICE_UNIT, ExpectedPaymentAmount);
        System.out.printf(NEW_LINE);
    }

    public void printBadge(String badgeName) {
        System.out.println(EVENT_BADGE_TITLE);
        System.out.println(badgeName);
    }
}
