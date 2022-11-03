package com.tests.task1.ex3;

public class Cube implements Shape{

    private final double border;

    public Cube(double border) {
        this.border = border;
    }

    @Override
    public double volume() {
        return Math.pow(border, 3);
    }

    public double getBorder() {
        return border;
    }

}