package core.basesyntax;

import core.basesyntax.db.Storage;
import core.basesyntax.service.ReportGenerator;
import core.basesyntax.service.ReportGeneratorImpl;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class ReportGeneratorTest {
    private ReportGenerator reportGenerator;

    @BeforeEach
    @AfterEach
    void setUp() {
        Storage.fruits.clear();
    }

    @Test
    void getReport_ok() {
        reportGenerator = new ReportGeneratorImpl();
        Storage.fruits.put("banana", 20);
        Storage.fruits.put("apple", 100);
        String actualReport = reportGenerator.getReport();
        Assertions.assertTrue(actualReport.contains("fruit,quantity"));
        Assertions.assertTrue(actualReport.contains("banana,20"));
        Assertions.assertTrue(actualReport.contains("apple,100"));
    }
}
