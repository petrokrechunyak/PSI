package com.tests.task1.ex1;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertArrayEquals;

class TaskTest {

    @Test
    void sorterTest() {
        int[] arr = {-2, 3, 0, -1, 4, 2, 0};
        int[] expected = {4, 3, 2, 0, 0};
        int[] actual = Task.sorter(arr);
        assertArrayEquals(expected, actual);
    }

    @Test
    void sorterOneElementTest() {
        int[] arr = {2};
        int[] expected = {2};
        int[] actual = Task.sorter(arr);
        assertArrayEquals(expected, actual);
    }

    @Test
    void sorterNullTest() {
        int[] arr = null;
        int[] expected = {};
        int[] actual = Task.sorter(arr);
        assertArrayEquals(expected, actual);
    }

    @Test
    void sorterEmptyArrayTest() {
        int[] arr = {};
        int[] expected = {};
        int[] actual = Task.sorter(arr);
        assertArrayEquals(expected, actual);
    }

    @Test
    void sorterOnlyNegativesTest() {
        int[] arr = {-2, -3, -10};
        int[] expected = {};
        int[] actual = Task.sorter(arr);
        assertArrayEquals(expected, actual);
    }
}