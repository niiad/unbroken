package com.niiad.unbroken.domain.entities.local;

public class CashAccountDisplay {
    private Integer id;
    private String name;

    public CashAccountDisplay(Integer id, String name) {
        this.id = id;
        this.name = name;
    }

    public Integer getId() {
        return this.id;
    }

    public String getName() {
        return this.name;
    }
}
