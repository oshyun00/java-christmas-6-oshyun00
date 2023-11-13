package christmas.util;

public class Parser {
    public int parseNumeric(String input) {
        try {
            return Integer.parseInt(input);
        } catch (NumberFormatException error) {
            throw new IllegalArgumentException("유효하지 않은 날짜입니다. 다시 입력해 주세요.");
        }
    }

    public int parseAmount(String input) {
        try {
            return Integer.parseInt(input);
        } catch (NumberFormatException error) {
            throw new IllegalArgumentException("유효하지 않은 주문입니다. 다시 입력해 주세요.");
        }
    }
}
