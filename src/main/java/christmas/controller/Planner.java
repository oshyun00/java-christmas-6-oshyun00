package christmas.controller;

import christmas.domain.Badge;
import christmas.domain.Order;
import christmas.domain.benefit.Benefit;
import christmas.domain.product.Product;
import christmas.view.InputView;
import christmas.view.OutputView;
import java.util.Map;

public class Planner {
    private final static int MINIMUM_PRICE_FOR_BENEFIT = 10000;
    OutputView outputView;
    InputView inputView;
    Badge badge;
    Benefit benefit;

    public Planner(OutputView outputView, InputView inputView, Badge badge, Benefit benefit) {
        this.outputView = outputView;
        this.inputView = inputView;
        this.badge = badge;
        this.benefit = benefit;
    }

    public void start() {
        int date = getDate();
        Order order = getOrder(date);

        int totalPriceBeforeEvent = order.calculateTotalPriceBeforeEvent();
        checkBenefitConditions(date, order, totalPriceBeforeEvent);

        int totalBenefit = benefit.getTotalBenefit(date, order);
        int finalPaymentAmount = getFinalPaymentAmount(date, order, totalPriceBeforeEvent);
        String badgeTitle = badge.issueBadge(totalBenefit);

        printResults(date, order, totalPriceBeforeEvent, totalBenefit, finalPaymentAmount, badgeTitle);
    }

    private void checkBenefitConditions(int date, Order order, int totalPriceBeforeEvent) {
        if (totalPriceBeforeEvent > MINIMUM_PRICE_FOR_BENEFIT) {
            benefit.checkBenefitCondition(date, order);
        }
    }

    private void printResults(int date, Order order, int totalPriceBeforeEvent,
                              int totalBenefit, int finalPaymentAmount,
                              String badgeTitle) {
        outputView.printOrderMenu(order.printOrder());
        outputView.printTotalPriceBeforeBenefit(totalPriceBeforeEvent);
        outputView.printFreeMenu(benefit);
        outputView.printBenefit(date, order, benefit);
        outputView.printTotalAmountOfBenefit(totalBenefit);
        outputView.printExpectedPaymentAmount(finalPaymentAmount);
        outputView.printBadge(badgeTitle);
    }

    private int getFinalPaymentAmount(int date, Order order, int totalPriceBeforeEvent) {
        int finalBenefitAmount = benefit.getFinalBenefitAmount(date, order);
        return totalPriceBeforeEvent + finalBenefitAmount;
    }

    private Order getOrder(int date) {
        Map<Product, Integer> orderMenu = inputView.readMenu();
        outputView.printEventPreviewMessage(date);
        return new Order(orderMenu);
    }

    private int getDate() {
        outputView.printWelcomeMessage();
        return inputView.readDate();
    }
}
