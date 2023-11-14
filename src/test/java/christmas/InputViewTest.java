package christmas;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import camp.nextstep.edu.missionutils.Console;
import christmas.domain.product.Product;
import christmas.domain.product.ProductRepository;
import christmas.domain.product.subproduct.Dessert;
import christmas.domain.product.subproduct.MainDish;
import christmas.util.Parser;
import christmas.util.Validator;
import christmas.view.InputView;
import java.io.ByteArrayInputStream;
import java.util.Map;
import java.util.stream.Collectors;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class InputViewTest extends MyTest {
    private InputView inputView;

    @BeforeEach
    void init() {
        inputView = new InputView(new Validator(new ProductRepository(), new Parser()));
    }

    @AfterEach
    void exit() {
        Console.close();
    }

    private void setInput(String input) {
        System.setIn(new ByteArrayInputStream(input.getBytes()));
    }

    @DisplayName("입력할 날짜를 올바르게 반환하는지 확인한다.")
    @Test
    void testReadDate() {
        setInput("13");
        int result = inputView.readDate();
        assertEquals(13, result);
    }

    @DisplayName("날짜가 숫자가 아니거나 범위에 맞지 않는 경우 [ERROR]로 시작하는 에러 메세지를 출력하고 올바른 값을 입력받을때까지 입력받는다.")
    @Test
    void testReadDateWithInvalidInput() {
        setInput("invalid\n가나\n32\n5");

        inputView.readDate();
        assertTrue(output().contains("[ERROR]"));
    }

    @DisplayName("입력한 메뉴 갯수를 올바르게 반환하는지 확인한다.")
    @Test
    void testReadMenu() {
        setInput("티본스테이크-1,초코케이크-2");
        Product steak = MainDish.of("티본스테이크", 55000);
        Product cake = Dessert.of("초코케이크", 15000);

        Map<Product, Integer> inputProducts = inputView.readMenu();
        assertThat(inputProducts).hasSize(2);

        Map<Integer, Integer> hash = getHash(inputProducts);

        assertThat(hash.get(steak.getName().hashCode())).isEqualTo(1);
        assertThat(hash.get(cake.getName().hashCode())).isEqualTo(2);

    }

    @DisplayName("올바르지 않은 주문을 입력한 경우 [ERROR]로 시작하는 에러 메세지를 출력하고 올바른 값을 입력받을때까지 입력받는다.")
    @Test
    void testReadMenuWithInvalidInput() {
        setInput("invalid\n가나\n32\n티본스테이크-1");

        inputView.readMenu();
        assertTrue(output().contains("[ERROR]"));
    }

    Map<Integer, Integer> getHash(Map<Product, Integer> inputProducts) {
        return inputProducts.entrySet()
                .stream()
                .collect(Collectors.toMap(
                        entry -> entry.getKey().getName().hashCode(),
                        Map.Entry::getValue
                ));
    }
}
