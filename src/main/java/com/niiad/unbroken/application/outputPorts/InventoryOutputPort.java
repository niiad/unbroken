package com.niiad.unbroken.application.outputPorts;

import com.niiad.unbroken.domain.entities.local.Inventory;

public interface InventoryOutputPort {
    Inventory fetchInventoryId(Integer inventoryId);

    boolean persistInventory(Inventory inventory);
}
