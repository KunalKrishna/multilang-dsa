package com.blogspot.abitmanipulator.topics.string;

public class StringDemo {

    static void main() {
        String lit1 = "Nachiketa";
        String lit2 = "Nachiketa";

        if ( lit1 == lit2 ) {
            System.out.println("The two strings are same literals in SCP");
        } else {
            System.out.println("The two literals are not SAME");
        }


        String str1 = new String("Nachiketa");
        String str2 = new String("Nachiketa");

        if ( lit1 == str1 ) {
            System.out.println("New String obj reuses string literal");
        } else {
            System.out.println("Fresh String object is created. Literal not accessed when creating obj using new keyword");
        }

        if ( str1 == str2 ) {
            System.out.println(str1 == str2);
        } else {
            System.out.println("The two string are different objects in heap");
        }


    }
}
