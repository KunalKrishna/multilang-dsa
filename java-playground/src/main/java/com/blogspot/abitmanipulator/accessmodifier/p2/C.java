package com.blogspot.abitmanipulator.accessmodifier.p2;

import com.blogspot.abitmanipulator.accessmodifier.p1.A;
//import com.blogspot.abitmanipulator.accessmodifier.p1.B;// COMPILE ERROR: B is not visible

public class C {

    static void main() {
        A a = new A(100,101,102,103);
//        B b = new B(35,36,3,38);

        System.out.println("-----------------------------------------");
        System.out.println(a.getClass().getName() + " : ");
        System.out.println("privateVariable   : "+ "has private access in '*.p1.A'");
        System.out.println("defaultVariable   : "+ "is not public in '*.p1.A'.Cannot be accessed from outside package");
        System.out.println("protectedVariable : "+ "has protected access in '*.p1.A'");
        System.out.println("publicVariable    : "+ a.publicVariable);
    }
}
