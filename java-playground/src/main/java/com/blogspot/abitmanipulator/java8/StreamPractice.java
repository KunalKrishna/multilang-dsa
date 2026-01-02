package com.blogspot.abitmanipulator.java8;

import java.util.*;
import java.util.stream.*;

public class StreamPractice {

    // ==========================================
    // 1. DATA MODELS
    // ==========================================

    static class Employee {
        String name;
        String dept;
        double salary;
        int age;

        public Employee(String name, String dept, double salary, int age) {
            this.name = name;
            this.dept = dept;
            this.salary = salary;
            this.age = age;
        }

        @Override
        public String toString() { return name + "(" + dept + ")"; }
        // Getters assumed for practice
        public String getName() { return name; }
        public String getDept() { return dept; }
        public double getSalary() { return salary; }
        public int getAge() { return age; }
    }

    static class Person {
        String name;
        String email;

        public Person(String name, String email) {
            this.name = name;
            this.email = email;
        }
        public String getName() { return name; }
        @Override public String toString() { return name; }
    }

    // ==========================================
    // 2. INPUT DATA SOURCES
    // ==========================================

    public static void main(String[] args) {

        // --- For Q1 (Flattening) ---
        List<List<Integer>> nestedList = Arrays.asList(
                Arrays.asList(1, 3, 5),
                Arrays.asList(2, 4),
                Arrays.asList(6, 8, 7)
        );

        // --- For Q2 (Unique Words) ---
        List<String> sentences = Arrays.asList(
                "Hello world",
                "Java streams are cool",
                "Hello Java world"
        );

        // --- For Q3, Q4, Q18, Q25 (Pairs, Intersection, Zip) ---
        List<Integer> list1 = Arrays.asList(1, 2, 3, 4, 5);
        List<Integer> list2 = Arrays.asList(4, 5, 6, 7, 8);

        // --- For Q6 - Q12, Q21 (Employee Questions) ---
        List<Employee> employees = Arrays.asList(
                new Employee("Alice", "IT", 75000, 28),
                new Employee("Bob", "IT", 60000, 24),
                new Employee("Charlie", "HR", 50000, 35),
                new Employee("David", "HR", 55000, 40),
                new Employee("Eve", "Sales", 85000, 30),
                new Employee("Frank", "Sales", 45000, 22)
        );

        // --- For Q13, Q17, Q19 (Strings/Chars) ---
        String str = "banana";
        String palindromeCheck = "racecar";

        // --- For Q14, Q28, Q29 (Numbers/Arrays) ---
        int[] numbersArray = {5, 2, 9, 1, 6, 3, 9}; // Note duplicates
        List<Integer> numbersList = Arrays.asList(1, 2, 3, 4, 5);

        // --- For Q15 (Longest String) ---
        List<String> wordsList = Arrays.asList("apple", "banana", "kiwi", "pineapple", "pear");

        // --- For Q20, Q23 (Maps) ---
        Map<String, Integer> map1 = new HashMap<>();
        map1.put("A", 10); map1.put("B", 20); map1.put("C", 5);

        Map<String, Integer> map2 = new HashMap<>();
        map2.put("B", 15); map2.put("C", 25); map2.put("D", 30);

        // --- For Q24 (Custom Distinct) ---
        List<Person> people = Arrays.asList(
                new Person("John", "john@gmail.com"),
                new Person("Jane", "jane@yahoo.com"),
                new Person("John", "john@outlook.com") // Duplicate name, diff email
        );

        // ==========================================
        // YOUR CODE GOES BELOW
        // ==========================================

        // Example: Solving Q1
        System.out.println("--- Q1: Flatten List ---");
        // nestedList.stream()...

    }
}