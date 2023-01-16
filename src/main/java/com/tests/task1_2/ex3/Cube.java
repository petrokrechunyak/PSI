package com.tests.task1_2.ex3;

public class Cube implements Shape {

    private final double border;

    public Cube(double border) {
        this.border = border;
    }

    @Override
    public double getVolume() {
        return Math.pow(border, 3);
    }

    public double getBorder() {
        return border;
    }

}
