package com.tests.task1.ex3;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.util.Collections;
import java.util.List;

class TaskTest {

    @Test
    void sorterTest() {
        Shape cylinder2 = new Cylinder(2.2, 3); // 6.6
        Shape cube1 = new Cube(2); // 8
        Shape cylinder1 = new Cylinder(4.35, 2.1); // 9.135
        Shape sphere2 = new Sphere(1.35); // 10.305
        Shape cube2 = new Cube(3.2); // 32.768
        Shape sphere1 = new Sphere(2.1); // 38.792

        List<Shape> shapes = List.of(cube1, cube2, cylinder1, cylinder2, sphere1, sphere2);
        List<Shape> expected = List.of(cylinder2, cube1, cylinder1, sphere2, cube2, sphere1);
        List<Shape> actual = Task.sorter(shapes);
        Assertions.assertIterableEquals(expected, actual);
    }

    @Test
    void sorterNullTest() {
        List<Shape> shapes = null;
        List<Shape> expected = Collections.emptyList();
        List<Shape> actual = Task.sorter(shapes);
        Assertions.assertIterableEquals(expected, actual);
    }

    @Test
    void sorterEmptyListTest() {
        List<Shape> shapes = Collections.emptyList();
        List<Shape> expected = Collections.emptyList();
        List<Shape> actual = Task.sorter(shapes);
        Assertions.assertIterableEquals(expected, actual);
    }

}