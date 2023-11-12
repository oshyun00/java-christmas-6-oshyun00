package christmas.view;

import camp.nextstep.edu.missionutils.Console;
import christmas.product.Product;
import christmas.product.ProductRepository;
import christmas.util.ConstantUtils;

public class InputView {
    ProductRepository productRepository;
    public int readDate() {
        int date;
        while (true) {
            try {
                System.out.println(ConstantUtils.ASK_EXPECTED_VISIT_DATE_MESSAGE);

                date = parseNumeric(Console.readLine());
                validDate(date);
                break;
            } catch (IllegalArgumentException error) {
                printError(error);
            }
        }
        return date;
    }

    public void readMenu() {
        Product[] items = new Product[0];
        while(true) {
//            try{
                System.out.println(ConstantUtils.ASK_ORDER_DETAILS_MESSAGE);
                String input = Console.readLine();
                break;
//            } catch
        }
    }

    private static void printError(IllegalArgumentException error) {
        System.out.println("[ERROR] " + error.getMessage());
    }

    private int parseNumeric(String input) {
        try {
            return Integer.parseInt(input);
        } catch (NumberFormatException error) {
            throw new IllegalArgumentException("유효하지 않은 날짜입니다. 다시 입력해 주세요.");
        }
    }

    private void validDate(int date) {
        if (date < 1 || date > 31) {
            throw new IllegalArgumentException("유효하지 않은 날짜입니다. 다시 입력해 주세요.");
        }
    }
}
