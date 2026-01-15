package com.blogspot.abitmanipulator.oops.inheritance;

class Vehicle {
    String modelName;
    int brandName;
    int speed;
    int topSpeed;
    int brake;

    public Vehicle(){}

    public Vehicle(String modelName, int brandName, int speed, int topSpeed, int brake) {
        this.modelName = modelName;
        this.brandName = brandName;
        this.speed = speed;
        this.topSpeed = topSpeed;
        this.brake = brake;
    }

    // Getters & Setters
    public String getModelName() {
        return modelName;
    }

    public void setModelName(String modelName) {
        this.modelName = modelName;
    }

    public int getBrandName() {
        return brandName;
    }

    public void setBrandName(int brandName) {
        this.brandName = brandName;
    }

    public int getSpeed() {
        return speed;
    }

    public void setSpeed(int speed) {
        this.speed = speed;
    }

    public int getTopSpeed() {
        return topSpeed;
    }

    public void setTopSpeed(int topSpeed) {
        this.topSpeed = topSpeed;
    }

    // Behaviours
    public void applyBrakes(int t) {
        speed = speed - brake*t;
        speed = Math.max(speed, 0);
    }
    public void accelerate(int gear, int t) {
        speed = speed + gear*t;
        speed = Math.max(speed, topSpeed);
    }
}

class Car extends Vehicle {
    int seatingCapacity;

}

class Bike extends Vehicle {
}

public class Main {
    public static void main(String[] args) {
//        Car car = new Car("");

    }
}
