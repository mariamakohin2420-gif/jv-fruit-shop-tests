package core.basesyntax;

import core.basesyntax.db.Storage;
import core.basesyntax.model.FruitTransaction;
import core.basesyntax.service.operation.BalanceOperation;
import core.basesyntax.service.operation.OperationHandler;
import core.basesyntax.service.operation.PurchaseOperation;
import core.basesyntax.service.operation.ReturnOperation;
import core.basesyntax.service.operation.SupplyOperation;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class OperationHandlersTest {
    @BeforeEach
    @AfterEach
    void clearStorage() {
        Storage.fruits.clear();
    }

    @Test
    void balanceOperation_ok() {
        OperationHandler handler = new BalanceOperation();
        FruitTransaction transaction = new FruitTransaction(FruitTransaction
                .Operation.BALANCE, "apple", 50);
        handler.handle(transaction);
        Assertions.assertEquals(50, Storage.fruits.get("apple"));
    }

    @Test
    void supplyOperation_ok() {
        Storage.fruits.put("apple", 20);
        OperationHandler handler = new SupplyOperation();
        FruitTransaction transaction = new FruitTransaction(FruitTransaction
                .Operation.SUPPLY, "apple", 30);
        handler.handle(transaction);
        Assertions.assertEquals(50, Storage.fruits.get("apple"));
    }

    @Test
    void returnOperation_ok() {
        Storage.fruits.put("banana", 10);
        OperationHandler handler = new ReturnOperation();
        FruitTransaction transaction = new FruitTransaction(FruitTransaction
                .Operation.RETURN, "banana", 15);
        handler.handle(transaction);
        Assertions.assertEquals(15, Storage.fruits.get("banana"));
    }

    @Test
    void purchaseOperation_validQuantity_ok() {
        Storage.fruits.put("banana", 20);
        OperationHandler handler = new PurchaseOperation();
        FruitTransaction transaction = new FruitTransaction(FruitTransaction
                .Operation.PURCHASE, "banana", 15);
        handler.handle(transaction);
        Assertions.assertEquals(5, Storage.fruits.get("banana"));
    }

    @Test
    void purchaseOperation_notEnoughStock_notOk() {
        Storage.fruits.put("banana", 10);
        OperationHandler handler = new PurchaseOperation();
        FruitTransaction transaction = new FruitTransaction(FruitTransaction
                .Operation.PURCHASE, "banana", 15);
        Assertions.assertThrows(RuntimeException.class, () -> handler.handle(transaction));
    }
}
