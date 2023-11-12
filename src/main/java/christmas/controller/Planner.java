package christmas.controller;

import christmas.domain.product.Product;
import christmas.domain.product.ProductRepository;
import christmas.view.InputView;
import christmas.view.OutputView;
import java.util.Map;

public class Planner {
    OutputView outputView = new OutputView();
    InputView inputView = new InputView(new ProductRepository());

    public void start(){
        outputView.printWelcomeMessage();
        int date = inputView.readDate();
        Map<Product, Integer> orderMenu = inputView.readMenu();
        System.out.println(orderMenu);
        outputView.printEventPreviewMessage(date);
    }
}
