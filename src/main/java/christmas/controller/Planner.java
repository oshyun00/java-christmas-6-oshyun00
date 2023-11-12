package christmas.controller;

import christmas.benefit.benefitcondition.ChristmasDDayCondition;
import christmas.benefit.benefitcondition.FreeMenuCondition;
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

    public void start(){
        outputView.printWelcomeMessage();
        int date = inputView.readDate();
        Map<Product, Integer> orderMenu = inputView.readMenu();
        System.out.println(orderMenu);
        outputView.printEventPreviewMessage(date);
        Order order = new Order(orderMenu);
        outputView.printOrderMenu(order.printOrder());
        int totalPriceBeforeEvent = order.calculateTotalPriceBeforeEvent();
        outputView.printTotalPriceBeforeBenefit(totalPriceBeforeEvent);
        freeMenuCondition.checkDiscountCondition(date,totalPriceBeforeEvent);
        outputView.printFreeMenu(freeMenuCondition);
        if(totalPriceBeforeEvent > 1000) {
            christmasDDayCondition.checkDiscountCondition(date, totalPriceBeforeEvent);
            outputView.printBenefit(date, christmasDDayCondition, freeMenuCondition);
        }
    }
}
