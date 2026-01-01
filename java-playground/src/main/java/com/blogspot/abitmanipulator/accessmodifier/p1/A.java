package com.blogspot.abitmanipulator.accessmodifier.p1;
/*
* {[final]} {[package-private]/[public]} class Class_name {
* 	// instance variables
*   [private]/[protected]/[package-private]/[public] data_type field;
*
*   // class Methods
* }
* [package-private] = []
* */

// A class with modifier public is visible to all classes everywhere
public class A {
    private   int privateVariable = 67; // visile only in this class (incl inner class)
              int defaultVariable = 68; // visible to all class placed in this package(p1)

    // import p1 required in the classes wanting to access below fields
    protected int protectedVariable = 69; // all subclass have access to this field
    public    int publicVariable = 70; // visible to the World -

    public A(){}

    public A(int pvt, int def, int i, int pub) {
        this.privateVariable = pvt;
        this.defaultVariable = def;
        this.protectedVariable = i;
        this.publicVariable = pub;
    }

    static void main() {
        A a = new A(1,2,3,4);
        System.out.println("-----------------------------------------");
        System.out.println("public "+ a.getClass().getName() + " : ");
        System.out.println("privateVariable   : "+ a.privateVariable);
        System.out.println("defaultVariable   : "+ a.defaultVariable);
        System.out.println("protectedVariable : "+ a.protectedVariable);
        System.out.println("publicVariable    : "+ a.publicVariable);
    }

}
