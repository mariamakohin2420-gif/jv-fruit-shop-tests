package core.basesyntax;

import core.basesyntax.db.Storage;
import core.basesyntax.service.ReportGenerator;
import core.basesyntax.service.ReportGeneratorImpl;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class ReportGeneratorTest {
    private ReportGenerator reportGenerator;

    @BeforeEach
    void setUp() {
        Storage.fruits.clear();
        reportGenerator =  new ReportGeneratorImpl();
    }

    @Test
    void getReport_ok() {
        Storage.fruits.put("banana", 152);
        Storage.fruits.put("apple", 90);
        String expected = "fruit,quantity" + System.lineSeparator()
                + "banana,152" + System.lineSeparator()
                + "apple,90" + System.lineSeparator();
        String actual = reportGenerator.getReport();
        Assertions.assertEquals(expected, actual);
    }
}
