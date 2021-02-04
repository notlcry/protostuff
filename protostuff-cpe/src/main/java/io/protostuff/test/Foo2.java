package io.protostuff.test;

import io.protostuff.FixedLength;

public class Foo2 extends Foo {

    @FixedLength(6)
    String habit;

    public Foo2(short name, int age, String habit) {
        super(name, age);
        this.habit = habit;
    }

    @Override
    public String toString() {
        return "Foo2{" +
                "habit='" + habit + '\'' +
                ", name=" + name +
                ", age=" + age +
                '}';
    }
}