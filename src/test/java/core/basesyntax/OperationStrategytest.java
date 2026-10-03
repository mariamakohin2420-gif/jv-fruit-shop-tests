package core.basesyntax;

import core.basesyntax.db.Storage;
import core.basesyntax.model.FruitTransaction;
import core.basesyntax.service.operation.OperationHandler;
import core.basesyntax.service.strategy.OperationStrategy;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class OperationStrategytest {
    private OperationStrategy operationStrategy;
    private OperationHandler balanceHandler;

    @BeforeEach
    @AfterEach
    void setUp() {
        Storage.fruits.clear();
    }

    @Test
    void getHandler_validOperation_ok() {
        OperationHandler handler = operationStrategy.getHandler(FruitTransaction.Operation.BALANCE);
        Assertions.assertEquals(balanceHandler, handler);
    }

    @Test
    void gethandler_unregisteredOperatio_notOk() {
        Assertions.assertThrows(IllegalArgumentException.class,
                () -> operationStrategy.getHandler(FruitTransaction.Operation.PURCHASE));
    }
}
