package christmas;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import camp.nextstep.edu.missionutils.Console;
import christmas.domain.product.ProductRepository;
import christmas.view.InputView;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class InputViewTest {
    private InputView inputView;

    @BeforeEach
    void setUp() {
        inputView = new InputView(new ProductRepository());
    }

    @AfterEach
    void tearDown() {
        Console.close();
    }

    private void setInput(String input) {
        System.setIn(new ByteArrayInputStream(input.getBytes()));
    }

    @DisplayName("입력할 날짜를 올바르게 반환하는지 확인한다.")
    @Test
    void testReadDateInvalidInputThenValidInput() {
        setInput("13");
        int result = inputView.readDate();
        assertEquals(13, result);
    }

    @DisplayName("숫자가 아니거나 범위에 맞지 않는 숫자를 입력한 경우 정확한 날짜를 입력할때까지 입력받는다.")
    @Test
    void testReadDateWithInvalidInput() {
        setInput("invalid\nㅁㄴ\n32\n5");

        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        System.setOut(new PrintStream(outputStream));

        int result = inputView.readDate();
        assertTrue(outputStream.toString().contains("[ERROR]"));
        assertEquals(5, result);

        System.setOut(System.out);
    }
}
