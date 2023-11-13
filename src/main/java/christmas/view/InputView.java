package christmas.view;

import camp.nextstep.edu.missionutils.Console;
import christmas.domain.product.Product;
import christmas.util.ConstantUtils;
import christmas.util.Validator;
import java.util.HashMap;
import java.util.Map;

public class InputView {
    Validator validator;

    public InputView(Validator validator) {
        this.validator = validator;
    }

    public Integer readDate() {
        int date;
        while (true) {
            try {
                System.out.println(ConstantUtils.ASK_EXPECTED_VISIT_DATE_MESSAGE);
                String input = Console.readLine();
                date = validator.validDate(input);
                break;
            } catch (IllegalArgumentException error) {
                printError(error);
            }
        }
        return date;
    }

    public Map<Product, Integer> readMenu() {
        Map<Product, Integer> orderProducts = new HashMap<>();
        while (true) {
            orderProducts.clear();
            try {
                System.out.println(ConstantUtils.ASK_ORDER_DETAILS_MESSAGE);
                String input = Console.readLine();
                validator.validMenu(orderProducts, input);
                break;
            } catch (IllegalArgumentException error) {
                printError(error);
            }
        }
        return orderProducts;
    }

    private static void printError(IllegalArgumentException error) {
        System.out.println("[ERROR] " + error.getMessage());
    }
}
