package com.zhou.reader.entity.selector;

public enum SelectorEnum {
    LIEWEN(1);

    private int id;

    SelectorEnum(int id) {
        this.id = id;
    }

    public int getValue() {
        return id;
    }
}
