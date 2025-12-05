package com.blogspot.abitmanipulator.accessmodifier.p1;

class A1 extends A {

    // if parent class has no-arg constructor then child class also must have
    public A1(int a, int b, int c, int d) {
        super(a, b, c, d);
    }

    static void main() {

        A1 a1 = new A1(2,3,4,5);
        System.out.println("-----------------------------------------");
        System.out.println(a1.getClass().getName() + " <extends> " + a1.getClass().getSuperclass());
        System.out.println("privateVariable "+ "has private access in '*.p1.A'");
        System.out.println("defaultVariable   : "+ a1.defaultVariable);
        System.out.println("protectedVariable : "+ a1.protectedVariable);
        System.out.println("publicVariable    : "+ a1.publicVariable);
    }
}
