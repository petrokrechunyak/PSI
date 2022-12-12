package com.tests.task1_2.ex1;

import java.util.Arrays;

public class Task {
    public static int[] sorter(int[] arr) {
        if (arr == null)
            return new int[]{};
        return Arrays.stream(arr).filter(x -> x >= 0)
                .map(i -> -i).sorted().map(i -> -i)
                .toArray();
    }
}
