package christmas.util;

import christmas.domain.product.Product;
import christmas.domain.product.ProductRepository;
import christmas.domain.product.subproduct.Drink;
import java.util.Map;
import java.util.Set;

public class Validator {
    private final ProductRepository productRepository;
    private final Parser parser;

    public Validator(ProductRepository productRepository, Parser parser) {
        this.productRepository = productRepository;
        this.parser = parser;
    }

    public int validDate(String input) {
        int parsedDate = parser.parseNumeric(input);
        if (parsedDate < 1 || parsedDate > 31) {
            throw new IllegalArgumentException("유효하지 않은 날짜입니다. 다시 입력해 주세요.");
        }
        return parsedDate;
    }

    public void validMenu(Map<Product, Integer> orderProducts, String input) {
        checkSpace(input);
        String[] splitInput = input.split(",");
        int orderMenuCount = splitInput.length;
        for (String menu : splitInput) {
            checkAppropriateMenu(orderProducts, menu);
        }
        checkAllDrink(orderProducts);
        checkDuplicateMenu(orderProducts, orderMenuCount);
        checkTotalMenuCount(orderProducts);
    }

    private void checkSpace(String input) {
        if (input.contains(" ")) {
            throw new IllegalArgumentException("공백 포함 불가");
        }
    }

    private void checkAppropriateMenu(Map<Product, Integer> orderMap, String menu) {
        String[] pair = menu.split("-");
        if (pair.length != 2) {
            throw new IllegalArgumentException("메뉴 혹은 주문수량이 잘못됨");
        }
        Product product = productRepository.findByName(pair[0]);
        if (product == null) {
            throw new IllegalArgumentException("메뉴판에 없음");
        }
        int amount = parser.parseAmount(pair[1]);
        if (amount < 1 || amount > 20) {
            throw new IllegalArgumentException("메뉴는 1이상 20미만이어야함");
        }
        orderMap.put(product, amount);
    }

    private void checkDuplicateMenu(Map<Product, Integer> orderProducts, int orderMenuCount) {
        if (orderProducts.size() < orderMenuCount) {
            throw new IllegalArgumentException("중복된 메뉴 불가");
        }
    }

    private void checkTotalMenuCount(Map<Product, Integer> orderProducts) {
        int totalMenuCount = orderProducts.values().stream().mapToInt(a -> a).sum();
        if (totalMenuCount > 20) {
            throw new IllegalArgumentException("메뉴 20개 이상 주문 불가");
        }
    }

    private void checkAllDrink(Map<Product, Integer> orderProducts) {
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
}
