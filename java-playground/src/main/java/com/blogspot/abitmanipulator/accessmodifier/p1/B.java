package com.blogspot.abitmanipulator.accessmodifier.p1;

/*
Package-private class (Hidden from outside)
Class visible only within its own package.
import com.blogspot.abitmanipulator.accessmodifier.p1.B from outside this package gives Compilation error
cannot perform B b = new B() outside this package.
warning : '*p1.B' is not public in '*.p1'. Cannot be accessed from outside
*/
class B {
    private   int privateVariable = 0; // visile only in this class (incl inner class)
              int defaultVariable = 1; // visible to all class placed in this package(p1)

    // import p1 required in the classes wanting to access below fields
    protected int protectedVariable = 2; // all subclass have access to this field
    public    int publicVariable = 3; // visible to the World -

    public B(int pvt, int def, int i, int pub) {
        this.privateVariable = pvt;
        this.defaultVariable = def;
        this.protectedVariable = i;
        this.publicVariable = pub;
    }

    static void main() {
        B b = new B(11,12,13,14);
        System.out.println("-----------------------------------------");
        System.out.println(b.getClass().getName() + " <extends> " + b.getClass().getSuperclass());
        System.out.println("privateVariable   : "+ b.privateVariable);
        System.out.println("defaultVariable   : "+ b.defaultVariable);
        System.out.println("protectedVariable : "+ b.protectedVariable);
        System.out.println("publicVariable    : "+ b.publicVariable);
    }

}
