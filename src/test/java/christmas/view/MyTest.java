package christmas.view;

import java.io.ByteArrayOutputStream;
import java.io.OutputStream;
import java.io.PrintStream;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;

public class MyTest {
    private final PrintStream originalOut = System.out;
    private OutputStream printedResult;

    @BeforeEach
    protected final void setUp() {
        printedResult = new ByteArrayOutputStream();
        System.setOut(new PrintStream(printedResult));
    }

    @AfterEach
    protected final void tearDown() {
        System.setOut(originalOut);
    }

    protected final String output() {
        return printedResult.toString();
    }
}
