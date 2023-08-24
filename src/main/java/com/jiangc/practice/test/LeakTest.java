package com.jiangc.practice.test;

import java.util.Arrays;
import java.util.EmptyStackException;

public class LeakTest {
    public static void main(String[] args) throws Exception {
        // stack1
        Stack stack1 = new Stack();
        // stack1入栈30个元素
        addItem(stack1);
        // stack2
        Stack stack2 = new Stack();
        // stack2入栈30个元素
        addItem(stack2);
        // stack2出栈20元素
        for (int i = 0; i < 20; i++) {
            stack2.pop();
        }
        System.out.println("----Over----");
        // 通知full gc, 若是jvm心情不错，能够拿到dump
        System.gc();
        Thread.sleep(5000);
    }

    private static void addItem(Stack stack1) {
        for (int i = 0; i < 30; i++) {
            stack1.push(new Object());
        }
    }
}

class Stack {
    private Object[] elements;
    private int size = 0;
    private static final int DEFAULT_INITIAL_CAPACITY = 16;

    public Stack() {
        elements = new Object[DEFAULT_INITIAL_CAPACITY];
    }

    public void push(Object e) {
        ensureCapacity();
        elements[size++] = e;
    }

    public Object pop() {
        if (size == 0) {
            throw new EmptyStackException();
        }
        return elements[--size];
    }

    /**
     * Ensure space for at least one more element, roughly
     * doubling the capacity each time the array needs to grow.
     */
    private void ensureCapacity() {
        if (elements.length == size) {
            elements = Arrays.copyOf(elements, 2 * size + 1);
        }
    }
}
