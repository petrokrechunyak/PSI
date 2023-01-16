package com.tests.task1_2.ex3;

public class Sphere implements Shape {

    private final double radius;

    public Sphere(double radius) {
        this.radius = radius;
    }

    @Override
    public double getVolume() {
        return (4 * Math.PI * Math.pow(radius, 3)) / 3;
    }

    public double getRadius() {
        return radius;
    }
}
