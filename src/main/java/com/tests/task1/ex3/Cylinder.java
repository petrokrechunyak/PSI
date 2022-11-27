package com.tests.task1.ex3;

public class Cylinder implements Shape {

    private final double baseSquare;

    private final double height;

    public Cylinder(double baseSquare, double height) {
        this.baseSquare = baseSquare;
        this.height = height;
    }

    @Override
    public double getVolume() {
        return baseSquare * height;
    }

    public double getBaseSquare() {
        return baseSquare;
    }

    public double getHeight() {
        return height;
    }
}
