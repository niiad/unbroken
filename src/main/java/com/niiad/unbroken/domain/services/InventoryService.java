package com.niiad.unbroken.domain.services;

import com.niiad.unbroken.domain.entities.local.Inventory;

import java.util.List;
import java.util.Map;
import java.util.function.Predicate;
import java.util.stream.Collectors;

public class InventoryService {
    public static List<Inventory> retrieveInventories(List<Inventory> inventories, Predicate<Inventory> predicate) {
        return inventories.stream().filter(predicate).collect(Collectors.<Inventory>toList());
    }

    public static Inventory findById(Map<Integer, Inventory> inventories, Integer id) {
        return inventories.get(id);
    }
}
