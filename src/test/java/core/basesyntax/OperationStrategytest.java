package core.basesyntax;

import core.basesyntax.model.FruitTransaction;
import core.basesyntax.service.operation.BalanceOperation;
import core.basesyntax.service.operation.OperationHandler;
import core.basesyntax.service.strategy.OperationStrategy;
import core.basesyntax.service.strategy.OperationStrategyImpl;
import java.util.HashMap;
import java.util.Map;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class OperationStrategytest {
    private OperationStrategy operationStrategy;
    private OperationHandler balanceHandler;

    @BeforeEach
    void setUp() {
        balanceHandler = new BalanceOperation();
        Map<FruitTransaction.Operation, OperationHandler> handlers = new HashMap<>();
        handlers.put(FruitTransaction.Operation.BALANCE, balanceHandler);
        operationStrategy = new OperationStrategyImpl(handlers);
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
