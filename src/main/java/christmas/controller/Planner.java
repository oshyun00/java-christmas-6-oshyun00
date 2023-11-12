package christmas.controller;

import christmas.benefit.benefitcondition.ChristmasDDayCondition;
import christmas.benefit.benefitcondition.FreeMenuCondition;
import christmas.benefit.benefitcondition.SpecialCondition;
import christmas.benefit.benefitcondition.WeekdayCondition;
import christmas.benefit.benefitcondition.WeekendCondition;
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
    ChristmasDDayCondition christmasDDayCondition = new ChristmasDDayCondition();
    WeekdayCondition weekdayCondition = new WeekdayCondition();
    WeekendCondition weekendCondition = new WeekendCondition();
    SpecialCondition specialCondition = new SpecialCondition();

    public void start() {
        outputView.printWelcomeMessage();
        int date = inputView.readDate();
        Map<Product, Integer> orderMenu = inputView.readMenu();
        System.out.println(orderMenu);
        outputView.printEventPreviewMessage(date);
        Order order = new Order(orderMenu);
        outputView.printOrderMenu(order.printOrder());
        int totalPriceBeforeEvent = order.calculateTotalPriceBeforeEvent();
        outputView.printTotalPriceBeforeBenefit(totalPriceBeforeEvent);
        freeMenuCondition.checkDiscountCondition(date, totalPriceBeforeEvent, order);
        outputView.printFreeMenu(freeMenuCondition);
        if (totalPriceBeforeEvent > 10000) {
            christmasDDayCondition.checkDiscountCondition(date, totalPriceBeforeEvent, order);
            weekdayCondition.checkDiscountCondition(date, totalPriceBeforeEvent, order);
            weekendCondition.checkDiscountCondition(date, totalPriceBeforeEvent, order);
            specialCondition.checkDiscountCondition(date, totalPriceBeforeEvent, order);
        }
        outputView.printBenefit(date, order, christmasDDayCondition, weekdayCondition, weekendCondition,
                specialCondition, freeMenuCondition);

    }
}
