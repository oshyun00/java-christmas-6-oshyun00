package christmas.controller;

import christmas.view.OutputView;

public class Planner {
    OutputView outputView = new OutputView();

    public void start(){
        outputView.printWelcomeMessage();
    }
}
