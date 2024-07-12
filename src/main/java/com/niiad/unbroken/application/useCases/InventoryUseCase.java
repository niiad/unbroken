package com.niiad.unbroken.application.useCases;

import com.niiad.unbroken.domain.entities.local.Inventory;
import com.niiad.unbroken.domain.entities.local.LocalProduct;

public interface InventoryUseCase {
    LocalProduct addLocalProductToInventory(Integer productId, Inventory inventory);

    LocalProduct removeLocalProductFromInventory(Integer productId, Inventory inventory);
}
