package core.basesyntax;

import core.basesyntax.model.FruitTransaction;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class FruitTransactionTest {
    @Test
    void fruitTransaction_gettersAndSetters_ok() {
        FruitTransaction transaction = new FruitTransaction();
        transaction.setOperation(FruitTransaction.Operation.BALANCE);
        transaction.setFruit("apple");
        transaction.setQuantity(50);
        Assertions.assertEquals(FruitTransaction.Operation.BALANCE, transaction.getOperation());
        Assertions.assertEquals("apple", transaction.getFruit());
        Assertions.assertEquals(50, transaction.getQuantity());
    }
}
