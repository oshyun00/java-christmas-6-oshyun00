package christmas.controller;

import christmas.view.InputView;
import christmas.view.OutputView;

public class Planner {
    OutputView outputView = new OutputView();
    InputView inputView = new InputView();

    public void start(){
        outputView.printWelcomeMessage();
        inputView.readDate();
    }
}
