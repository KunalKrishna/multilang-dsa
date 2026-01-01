package com.blogspot.abitmanipulator.finalpkg;
/*
Objective: How to make class unheritable ?
 */

public class NonFinalClass {
    int a;
    public void display() {
        System.out.println("a = " + a);
    }
}

final class FinalClass extends NonFinalClass {
    // This class cannot be extended further
    // If you try to extend FinalClass, it will result in a compilation error.
    // For example:
    // class AnotherClass extends FinalClass { } // This would be a compile-time error

}

