package christmas.util;

import christmas.domain.product.Product;
import christmas.domain.product.ProductRepository;
import christmas.domain.product.subproduct.Drink;
import christmas.exception.BusinessLogicException;
import christmas.exception.ExceptionMessage;
import java.util.Map;
import java.util.Set;

public class Validator {
    private static final int MIN_ORDER_QUANTITY = 1;
    private static final int MAX_ORDER_QUANTITY = 20;
    private final ProductRepository productRepository;
    private final Parser parser;

    public Validator(ProductRepository productRepository, Parser parser) {
        this.productRepository = productRepository;
        this.parser = parser;
    }

    public int validDate(String input) {
        int parsedDate = parser.parseNumeric(input);
        if (parsedDate < 1 || parsedDate > 31) {
            throw new BusinessLogicException(ExceptionMessage.INVALID_DATE);
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
            throw new BusinessLogicException(ExceptionMessage.INVALID_ORDER);
        }
    }

    private void checkAppropriateMenu(Map<Product, Integer> orderMap, String menu) {
        String[] pair = menu.split("-");
        if (pair.length != 2) {
            throw new BusinessLogicException(ExceptionMessage.INVALID_ORDER);
        }
        Product product = productRepository.findByName(pair[0]);
        if (product == null) {
            throw new BusinessLogicException(ExceptionMessage.INVALID_ORDER);
        }
        int amount = parser.parseAmount(pair[1]);
        if (amount < MIN_ORDER_QUANTITY) {
            throw new BusinessLogicException(ExceptionMessage.INVALID_ORDER);
        }
        orderMap.put(product, amount);
    }

    private void checkDuplicateMenu(Map<Product, Integer> orderProducts, int orderMenuCount) {
        if (orderProducts.size() < orderMenuCount) {
            throw new BusinessLogicException(ExceptionMessage.INVALID_ORDER);
        }
    }

    private void checkTotalMenuCount(Map<Product, Integer> orderProducts) {
        int totalMenuCount = orderProducts.values().stream().mapToInt(a -> a).sum();
        if (totalMenuCount > MAX_ORDER_QUANTITY) {
            throw new BusinessLogicException(ExceptionMessage.OVER_MAX_ORDER_QUANTITY);
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
            throw new BusinessLogicException(ExceptionMessage.DRINKS_ONLY_ORDER_NOT_ALLOWED);
        }
    }
}
