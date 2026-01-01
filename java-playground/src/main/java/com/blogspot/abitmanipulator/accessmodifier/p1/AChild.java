package com.blogspot.abitmanipulator.accessmodifier.p1;

class AChild extends A {

    public AChild(int a, int b, int c, int d) {
        super(a, b, c, d);
    }

    static void main() {

        AChild a1 = new AChild(2,3,4,5);
        System.out.println("-----------------------------------------");
        System.out.println(a1.getClass().getName() + " <extends> " + a1.getClass().getSuperclass());
        System.out.println("privateVariable "+ "has private access in '*.p1.A'");
        System.out.println("defaultVariable   : "+ a1.defaultVariable);
        System.out.println("protectedVariable : "+ a1.protectedVariable);
        System.out.println("publicVariable    : "+ a1.publicVariable);
    }
}
