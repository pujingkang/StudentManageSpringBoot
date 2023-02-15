package com.souls.test;

import java.util.Comparator;

public class Demo implements IDemo {

    @Override
    public void say() {
        // TODO Auto-generated method stub
        System.out.println("AOP JDK say");
    }

    @Override
    public void hello() {
        // TODO Auto-generated method stub
        System.out.println("AOP JDK method hello");
    }

    public void print() {
        System.out.println("just a test");
    }
}
