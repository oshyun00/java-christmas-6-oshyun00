package christmas;

import christmas.configurer.AppConfigurer;
import christmas.controller.Planner;

public class Application {
    public static void main(String[] args) {
        AppConfigurer appConfigurer = new AppConfigurer();

        Planner planner = new Planner(
                appConfigurer.outputView(),
                appConfigurer.inputView(),
                appConfigurer.badge(),
                appConfigurer.benefit()
        );

        planner.start();
    }
}
