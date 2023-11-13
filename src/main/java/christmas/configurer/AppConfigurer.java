package christmas.configurer;

import christmas.domain.Badge;
import christmas.domain.benefit.Benefit;
import christmas.domain.benefit.benefitcondition.BenefitCondition;
import christmas.domain.benefit.benefitcondition.ChristmasDDayCondition;
import christmas.domain.benefit.benefitcondition.FreeMenuCondition;
import christmas.domain.benefit.benefitcondition.SpecialCondition;
import christmas.domain.benefit.benefitcondition.WeekdayCondition;
import christmas.domain.benefit.benefitcondition.WeekendCondition;
import christmas.domain.product.ProductRepository;
import christmas.util.Parser;
import christmas.util.Validator;
import christmas.view.InputView;
import christmas.view.OutputView;

public class AppConfigurer {
    public OutputView outputView() {
        return new OutputView();
    }

    public InputView inputView() {
        return new InputView(
                new Validator(new ProductRepository(), new Parser()));
    }

    public Badge badge() {
        return new Badge();
    }

    public Benefit benefit() {
        return new Benefit(
                new BenefitCondition[]{
                        new ChristmasDDayCondition(),
                        new WeekdayCondition(),
                        new WeekendCondition(),
                        new SpecialCondition(),
                        new FreeMenuCondition()
                }
        );
    }
}
