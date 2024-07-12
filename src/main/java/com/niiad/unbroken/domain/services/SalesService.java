package com.niiad.unbroken.domain.services;

import com.niiad.unbroken.domain.entities.local.Sales;

import java.util.List;
import java.util.Map;
import java.util.function.Predicate;
import java.util.stream.Collectors;

public class SalesService {
    public static List<Sales> retrieveSales(List<Sales> sales, Predicate<Sales> predicate) {
        return sales.stream().filter(predicate).collect(Collectors.<Sales>toList());
    }

    public static Sales findById(Map<Integer, Sales> sales, Integer id) {
        return sales.get(id);
    }
}
