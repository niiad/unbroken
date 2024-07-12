package com.niiad.unbroken.domain.services;

import com.niiad.unbroken.domain.entities.local.Expenditure;

import java.util.List;
import java.util.Map;
import java.util.function.Predicate;
import java.util.stream.Collectors;

public class ExpenditureService {
    public static List<Expenditure> retrieveExpenditures(List<Expenditure> expenditures, Predicate<Expenditure> predicate) {
        return expenditures.stream().filter(predicate).collect(Collectors.<Expenditure>toList());
    }

    public static Expenditure findById(Map<Integer, Expenditure> expenditures, Integer id) {
        return expenditures.get(id);
    }
}
