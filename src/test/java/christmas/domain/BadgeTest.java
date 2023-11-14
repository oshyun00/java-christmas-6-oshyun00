package christmas.domain;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

public class BadgeTest {
    Badge badge;

    @BeforeEach
    void init() {
        badge = new Badge();
    }

    @DisplayName("혜택 금액에 따라 올바른 배지 이름을 출력한다.")
    @ParameterizedTest
    @CsvSource(value = {"-20000,산타", "-10000,트리", "-5000,별", "-3000,없음"})
    void testIssueBadge(int benefitPrice, String expectedBadge) {
        String issuedBadge = badge.issueBadge(benefitPrice);
        assertEquals(issuedBadge, expectedBadge);
    }
}
