package com.blogspot.abitmanipulator.accessmodifier.p1;

public class B1 extends B {

    public B1(int pvt, int def, int i, int pub) {
        super(pvt, def, i, pub);
    }

    static void main() {
        B1 b1 = new B1(11,12,13,14);
        System.out.println("-----------------------------------------");
        System.out.println(b1.getClass().getName() + " <extends> " + b1.getClass().getSuperclass());
        System.out.println("privateVariable "+ "has private access in  '*.p1.B'");
        System.out.println("defaultVariable   : "+ b1.defaultVariable);
        System.out.println("protectedVariable : "+ b1.protectedVariable);
        System.out.println("publicVariable    : "+ b1.publicVariable);
    }

}
