package com.supinfo.model;

import java.io.Serializable;
import java.util.Objects;

public class MyClass implements Serializable {
    private int number;
    public static int Counter;

    public MyClass() {
        MyClass.Counter++;
        number = 0;
    }
}
