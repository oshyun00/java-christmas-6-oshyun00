package christmas.util;

import christmas.exception.BusinessLogicException;
import christmas.exception.ExceptionMessage;

public class Parser {
    public int parseNumeric(String input) {
        try {
            return Integer.parseInt(input);
        } catch (NumberFormatException error) {
            throw new BusinessLogicException(ExceptionMessage.INVALID_DATE);
        }
    }

    public int parseAmount(String input) {
        try {
            return Integer.parseInt(input);
        } catch (NumberFormatException error) {
            throw new BusinessLogicException(ExceptionMessage.INVALID_ORDER);
        }
    }
}
