package com.tests.task1_2.ex2;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.Collections;
import java.util.List;

class TaskTest {

    @Test
    void finderTest() {                 // #1 - 3, #2 - 5, #3 - 2, #4 - 4, #5 - 2, #6 - 1
        List<String> strings = List.of("#1 #1 #1 1 #1 #2 #2 #2 #4", "#4 #2", "#1 #2 #3 #4 #5 #6", " #1 #2 #3 #4 #5 234 24 4 4 4 #2", "4 #2");
        List<String> expected = List.of("#2", "#4", "#1", "#5", "#3");
        List<String> actual = Task.finder(strings);
        Assertions.assertIterableEquals(expected, actual);
    }

    @Test
    void finderNullTest() {
        List<String> strings = null;
        List<String> expected = Collections.emptyList();
        List<String> actual = Task.finder(strings);
        Assertions.assertIterableEquals(expected, actual);
    }

    @Test
    void finderLessThanFiveElementsTest() {
        List<String> strings = List.of("#1 2 1 1 0 test #4", "#test 1", "#1");
        List<String> expected = List.of("#1", "#test", "#4");
        List<String> actual = Task.finder(strings);
        Assertions.assertIterableEquals(expected, actual);
    }

    @Test
    void finderEmptyListTest() {
        List<String> strings = Collections.emptyList();
        List<String> expected = Collections.emptyList();
        List<String> actual = Task.finder(strings);
        Assertions.assertIterableEquals(expected, actual);
    }

}