package christmas.controller;

import christmas.domain.benefit.Benefit;
import christmas.domain.benefit.benefitcondition.BenefitCondition;
import christmas.domain.benefit.benefitcondition.ChristmasDDayCondition;
import christmas.domain.benefit.benefitcondition.FreeMenuCondition;
import christmas.domain.benefit.benefitcondition.SpecialCondition;
import christmas.domain.benefit.benefitcondition.WeekdayCondition;
import christmas.domain.benefit.benefitcondition.WeekendCondition;
import christmas.domain.Badge;
import christmas.domain.Order;
import christmas.domain.product.Product;
import christmas.domain.product.ProductRepository;
import christmas.view.InputView;
import christmas.view.OutputView;
import java.util.Map;

public class Planner {
    OutputView outputView = new OutputView();
    InputView inputView = new InputView(new ProductRepository());
    FreeMenuCondition freeMenuCondition = new FreeMenuCondition();
    Badge badge = new Badge();
    Benefit benefit = new Benefit(
            new BenefitCondition[]{new ChristmasDDayCondition(), new WeekdayCondition(), new WeekendCondition(),
                    new SpecialCondition(), new FreeMenuCondition()});

    public void start() {
        outputView.printWelcomeMessage();
        int date = inputView.readDate();
        Map<Product, Integer> orderMenu = inputView.readMenu();
        outputView.printEventPreviewMessage(date);
        Order order = new Order(orderMenu);
        outputView.printOrderMenu(order.printOrder());
        int totalPriceBeforeEvent = order.calculateTotalPriceBeforeEvent();
        outputView.printTotalPriceBeforeBenefit(totalPriceBeforeEvent);
        freeMenuCondition.checkDiscountCondition(date, totalPriceBeforeEvent, order);
        outputView.printFreeMenu(freeMenuCondition);
        if (totalPriceBeforeEvent > 10000) {
            benefit.checkBenefit(date, totalPriceBeforeEvent, order);
        }
        outputView.printBenefit(date, order, benefit);
        int totalBenefit = benefit.getTotalBenefit(date, order);
        outputView.printTotalAmountOfBenefit(totalBenefit);
        int totalBenefitPaymentAmount = benefit.getFinalBenefitAmount(date, order);
        int finalPaymentAmount = totalPriceBeforeEvent + totalBenefitPaymentAmount;
        outputView.printExpectedPaymentAmount(finalPaymentAmount);
        String badgeTitle = badge.issueBadge(totalBenefit);
        outputView.printBadge(badgeTitle);
    }
}
