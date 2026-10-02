package core.basesyntax;

import core.basesyntax.model.FruitTransaction;
import core.basesyntax.service.DataConverter;
import core.basesyntax.service.DataConverterImpl;
import java.util.List;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class DataConverterTest {
    private DataConverter dataConverter;

    @BeforeEach
    void setUp() {
        dataConverter = new DataConverterImpl();
    }

    @Test
    void convertToTransaction_validInput_ok() {
        List<String> inputLines = List.of(
                "type,fruit,quantity",
                "b,banana,20",
                "s,apple,100"
        );
        List<FruitTransaction> transactions = dataConverter.convertToTransaction(inputLines);
        Assertions.assertEquals(2, transactions.size());
        Assertions.assertEquals(FruitTransaction.Operation.BALANCE, transactions.get(0).getOperation());
        Assertions.assertEquals("banana", transactions.get(0).getFruit());
        Assertions.assertEquals(20, transactions.get(0).getQuantity());
        Assertions.assertEquals(FruitTransaction.Operation.SUPPLY, transactions.get(1).getOperation());
        Assertions.assertEquals("apple", transactions.get(1).getFruit());
        Assertions.assertEquals(100, transactions.get(1).getQuantity());
    }

    @Test
    void convertToTransaction_invalid_notOk() {
        List<String> inputLines = List.of("b,banana");
        Assertions.assertThrows(IllegalArgumentException.class,
                () -> dataConverter.convertToTransaction(inputLines));
    }
}
