package com.blogspot.abitmanipulator.accessmodifier.p2;

import com.blogspot.abitmanipulator.accessmodifier.p1.A;

public class D extends A {

    public D(int pvt, int def, int i, int pub) {
        super(pvt, def, i, pub);
    }

    static void main() {
        A a = new A(100,101,102,103);
//        B b = new B(35,36,3,38);
        System.out.println("-----------------------------------------");
        System.out.println(a.getClass().getName() + " : ");
        System.out.println("privateVariable   : "+ "has private access in '*.p1.A'");
        System.out.println("defaultVariable   : "+ "is not public in '*.p1.A'.Cannot be accessed from outside package");
        System.out.println("protectedVariable : "+ "has protected access in '*.p1.A'");
        System.out.println("publicVariable    : "+ a.publicVariable);

        A ad = new D(1,2,3,4);
        System.out.println("-----------------------------------------");
        System.out.println("A ad = new D(1,2,3,4);");
        System.out.println(ad.getClass().getName() + " : ");
        System.out.println("privateVariable   : "+ "has private access in '*.p1.A'");
        System.out.println("defaultVariable   : "+ "is not public in '*.p1.A'.Cannot be accessed from outside package");
        System.out.println("protectedVariable : "+ "has protected access in '*.p1.A'");
        System.out.println("publicVariable    : "+ ad.publicVariable);


        D d = new D(11,21,31,41);
        System.out.println("-----------------------------------------");
        System.out.println("D d = new D(1,2,3,4);");
        System.out.println(d.getClass().getName() + " : ");
        System.out.println("privateVariable   : "+ "has private access in '*.p1.A'");
        System.out.println("defaultVariable   : "+ "is not public in '*.p1.A'.Cannot be accessed from outside package");
        System.out.println("protectedVariable : "+ d.protectedVariable);
        System.out.println("publicVariable    : "+ d.publicVariable);
    }
}
