package core.basesyntax;

import core.basesyntax.db.Storage;
import core.basesyntax.service.ReportGenerator;
import core.basesyntax.service.ReportGeneratorImpl;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertTrue;

public class ReportGeneratorTest {
    private ReportGenerator reportGenerator;

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
        assertTrue(actualReport.contains("fruit,quantity"));
        assertTrue(actualReport.contains("banana,20"));
        assertTrue(actualReport.contains("apple,100"));
    }
}
