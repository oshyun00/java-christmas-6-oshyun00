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
        outputView.printWelcomeMessage();
        int date = inputView.readDate();
        Map<Product, Integer> orderMenu = inputView.readMenu();
        outputView.printEventPreviewMessage(date);
        Order order = new Order(orderMenu);

        outputView.printOrderMenu(order.printOrder());
        int totalPriceBeforeEvent = order.calculateTotalPriceBeforeEvent();
        outputView.printTotalPriceBeforeBenefit(totalPriceBeforeEvent);
        if (totalPriceBeforeEvent > MINIMUM_PRICE_FOR_BENEFIT) {
            benefit.checkBenefitCondition(date, order);
        }
        outputView.printFreeMenu(benefit);
        outputView.printBenefit(date, order, benefit);

        int totalBenefit = benefit.getTotalBenefit(date, order);
        outputView.printTotalAmountOfBenefit(totalBenefit);

        int finalBenefitAmount = benefit.getFinalBenefitAmount(date, order);
        int finalPaymentAmount = totalPriceBeforeEvent + finalBenefitAmount;
        outputView.printExpectedPaymentAmount(finalPaymentAmount);
        String badgeTitle = badge.issueBadge(totalBenefit);
        outputView.printBadge(badgeTitle);
    }
}
