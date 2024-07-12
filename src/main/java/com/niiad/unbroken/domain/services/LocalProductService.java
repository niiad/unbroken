package com.niiad.unbroken.domain.services;

import com.niiad.unbroken.domain.entities.local.LocalProduct;

import java.util.List;
import java.util.Map;
import java.util.function.Predicate;
import java.util.stream.Collectors;

public class LocalProductService {
    public static List<LocalProduct> retrieveProducts(List<LocalProduct> products, Predicate<LocalProduct> predicate) {
        return products.stream().filter(predicate).collect(Collectors.<LocalProduct>toList());
    }

    public static LocalProduct findById(Map<Integer, LocalProduct> products, Integer id) {
        return products.get(id);
    }
}
