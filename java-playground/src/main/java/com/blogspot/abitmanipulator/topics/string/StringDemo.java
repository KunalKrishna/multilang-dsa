package com.blogspot.abitmanipulator.topics.string;

public class StringDemo {

    static void main() {
        System.out.println("======== String Demo ========");

        // -------- String Constant Pool --------
        String lit1 = "Namaste";
        String lit2 = "Namaste"; //Chec

        String hexAdd1 = getHexAddress(lit1);
        String hexAdd2 = getHexAddress(lit2);// ks SCP, if exists, reuse reference.

        if ( lit1 == lit2 ) {
            System.out.println("The two strings are same literals in SCP");
            System.out.println(hexAdd1 == hexAdd2);
        } else {
            System.out.println("The two literals are not SAME");
        }

        // -------- HEAP --------
        String str1 = new String("Namaste"); // new forces new obj creation in main HEAP
        String str2 = new String("Namaste"); // again new obj in heap

        String hexAdd3 = getHexAddress(str1);
        String hexAdd4 = getHexAddress(str2);


        if ( lit1 == str1 ) {
            System.out.println("New String obj reuses string literal");

        } else {
            System.out.println("Fresh String object is created. Literal not accessed when creating obj using new keyword");
            if ( lit1.equals(str1) ) { // true (Content is identical)
                System.out.println("    Content of lit1 and str2 is same : "+ lit1);
                System.out.println("    Their reference are same ? : " + (hexAdd3 == hexAdd4));
            }
        }
        System.out.println("hexAdd1 = " + hexAdd1 );
        System.out.println("hexAdd2 = " + hexAdd2 );
        System.out.println("hexAdd3 = " + hexAdd3 );
        System.out.println("hexAdd4 = " + hexAdd4 );



        // -------- intern() --------

        if ( str1 == str2 ) {
            System.out.println(str1 == str2);
        } else {
            System.out.println("The two string are different objects in heap");
        }


        String heapStr = new String("Test");
        String poolStr = heapStr.intern();

        String literal = "Test";

        System.out.println(heapStr == literal); // false
        System.out.println(poolStr == literal); // true (Both are now the Pool version)




        char[] parts = new char[] {'a','b','c','d','e'};
        String combined = new String(parts);
        System.out.println("Combined String : " + combined);

        String s = "a" + "b" + "c";
        // Compiler optimizes this to  :
        // String s = "abc"; (one object)


    }

    static String getHexAddress(String u) {
        int id = System.identityHashCode(u);
        String hexAddress = Integer.toHexString(id);
        return (u.getClass().getName() + "@" + hexAddress);
    }
}
