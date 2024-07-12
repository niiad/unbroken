package com.niiad.unbroken.domain.entities.local;

import java.util.function.Predicate;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class Expenditure {
    private Long id;
    private String category;
    private String description;
    private String timestamp;
    private String amountSpent;

    public static Predicate<Expenditure> getCategory(String category) {
        return result -> result.getCategory().equals(category);
    }
}

