package com.tests.task1_2.ex3;

import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

public class Task {

    public static List<Shape> sorter(List<Shape> shapes) {
        if (shapes == null)
            return Collections.emptyList();
        shapes = shapes.stream()
                .sorted(Comparator.comparingDouble(Shape::getVolume))
                .collect(Collectors.toList());
        return shapes;
    }

}
