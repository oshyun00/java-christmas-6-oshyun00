package christmas.exception;

public enum ExceptionMessage {
    INVALID_DATE("유효하지 않은 날짜입니다. 다시 입력해 주세요."),
    INVALID_ORDER("유효하지 않은 주문입니다. 다시 입력해 주세요."),
    DRINKS_ONLY_ORDER_NOT_ALLOWED("음료만 주문할 수 없습니다. 다시 입력해 주세요."),
    OVER_MAX_ORDER_QUANTITY("메뉴는 한 번에 최대 20개까지만 주문할 수 있습니다. 다시 입력해 주세요.");
    private final String errorMessage;

    ExceptionMessage(String errorMessage) {
        this.errorMessage = errorMessage;
    }

    public String getErrorMessage() {
        return errorMessage;
    }
}
