package com.blogspot.abitmanipulator.accessmodifier;

public class CodeBlocksDemo {
    private final int num;
    private final String day = "Sunday";
    // Static variable
    static int staticVar;
    // Non-static (instance) variable
    int instanceVar;

    // Static code block (runs once)
    static {
        staticVar = 1;
        System.out.println("Static block executed (once). Static var: " + staticVar);
    }

    // Non-static code block (runs on each new instance)
    {
        instanceVar = staticVar + 1;
        staticVar = instanceVar; // update static var
        System.out.println("Total object of this class: " + instanceVar);
    }

    // Constructor (runs after non-static block)
    public CodeBlocksDemo(int num) {
        this.num = num;
        System.out.println("Constructor executed.");
    }

    public static void main(String[] args) {
        System.out.println("Inside Main Method.");
        new CodeBlocksDemo(10); // Creates the first object
        new CodeBlocksDemo(10); // Creates the second object
        new CodeBlocksDemo(10); // Creates the second object
    }
}
