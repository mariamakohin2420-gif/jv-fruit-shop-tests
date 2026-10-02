package core.basesyntax.service.operation;

import core.basesyntax.db.Storage;
import core.basesyntax.model.FruitTransaction;

public class SupplyOperation implements OperationHandler {
    @Override
    public void handle(FruitTransaction transaction) {
        String fruit = transaction.getFruit();
        int newQuantity = Storage.fruits.getOrDefault(fruit, 0) + transaction.getQuantity();
        Storage.fruits.put(fruit, newQuantity);
    }
}
