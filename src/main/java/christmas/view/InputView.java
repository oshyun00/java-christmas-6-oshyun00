package christmas.view;

import camp.nextstep.edu.missionutils.Console;
import christmas.domain.product.Product;
import christmas.domain.product.ProductRepository;
import christmas.domain.product.subproduct.Drink;
import christmas.util.ConstantUtils;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

public class InputView {
    ProductRepository productRepository;

    public InputView(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

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

    public Map<Product, Integer> readMenu() {
        Map<Product, Integer> orderProducts = new HashMap<>();
        while (true) {
            orderProducts.clear();
            try {
                System.out.println(ConstantUtils.ASK_ORDER_DETAILS_MESSAGE);
                String input = Console.readLine();
                validSpace(input);
                String[] splitInput = input.split(",");
                int orderMenuCount = splitInput.length;
                for (String menu : splitInput) {
                    validMenu(orderProducts, menu);
                }
                validDrink(orderProducts);
                validDuplicateMenu(orderProducts, orderMenuCount);
                validTotalMenuCount(orderProducts);
                break;
            } catch (IllegalArgumentException error) {
                printError(error);
            }
        }
        return orderProducts;
    }

    private static void validDuplicateMenu(Map<Product, Integer> orderProducts, int orderMenuCount) {
        if (orderProducts.size() < orderMenuCount) {
            throw new IllegalArgumentException("중복된 메뉴 불가");
        }
    }

    private static void validTotalMenuCount(Map<Product, Integer> orderProducts) {
        int totalMenuCount = orderProducts.values().stream().mapToInt(a -> a).sum();
        if (totalMenuCount > 20) {
            throw new IllegalArgumentException("메뉴 20개 이상 주문 불가");
        }
    }

    private static void validDrink(Map<Product, Integer> orderProducts) {
        Set<Product> products = orderProducts.keySet();
        int drink = 0;
        int totalMenu = products.size();
        for (Product product : products) {
            if (product instanceof Drink) {
                drink++;
            }
        }
        if (drink == totalMenu) {
            throw new IllegalArgumentException("음료만 주문 불가");
        }
    }

    private void validMenu(Map<Product, Integer> orderMap, String menu) {
        String[] pair = menu.split("-");
        if (pair.length != 2) {
            throw new IllegalArgumentException("메뉴 혹은 주문수량이 잘못됨");
        }
        Product product = productRepository.findByName(pair[0]);
        if (product == null) {
            throw new IllegalArgumentException("메뉴판에 없음");
        }
        int amount = parseAmount(pair[1]);
        if (amount < 1 || amount > 20) {
            throw new IllegalArgumentException("메뉴는 1이상 20미만이어야함");
        }
        orderMap.put(product, amount);
    }

    private static void validSpace(String input) {
        if (input.contains(" ")) {
            throw new IllegalArgumentException("공백 포함 불가");
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

    private int parseAmount(String input) {
        try {
            return Integer.parseInt(input);
        } catch (NumberFormatException error) {
            throw new IllegalArgumentException("유효하지 않은 주문입니다. 다시 입력해 주세요.");
        }
    }

    private void validDate(int date) {
        if (date < 1 || date > 31) {
            throw new IllegalArgumentException("유효하지 않은 날짜입니다. 다시 입력해 주세요.");
        }
    }
}
