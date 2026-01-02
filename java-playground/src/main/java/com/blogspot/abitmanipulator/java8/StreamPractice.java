package com.blogspot.abitmanipulator.java8;

import java.util.*;
import java.util.function.Function;
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

        // I. Mapping & FlatMapping (Handling Nested Data)

        // --- For Q1 (Flattening) ---
        List<List<Integer>> nestedList = Arrays.asList(
                Arrays.asList(1, 3, 5),
                Arrays.asList(2, 4),
                Arrays.asList(6, 8, 7)
        );
        //  Flatten List of Lists: Given List<List<Integer>>, flatten it into a single List<Integer> and sort it.
        System.out.println("--- Q1: Flatten List ---");
        nestedList.stream()
                .flatMap(aStream -> aStream.stream())
                .sorted()
                .forEach(System.out::println);

        // --- For Q2 (Unique Words) ---
        List<String> sentences = Arrays.asList(
                "Hello world",
                "Java streams are cool",
                "Hello Java world"
        );
        //  Unique Words: Given a List<String> of sentences, return a list of all unique words used across all sentences.
        System.out.println("--- Q2: Unique Words ---");
        sentences.stream() //list of words
                .map(sentence -> sentence.split(" "))
                .flatMap(Arrays::stream)
                .distinct()
                .forEach(System.out::println);

        // --- For Q3, Q4, Q18, Q25 (Pairs, Intersection, Zip) ---
        List<Integer> list1 = Arrays.asList(1, 2, 3, 4, 5);
        List<Integer> list2 = Arrays.asList(4, 5, 6, 7, 8);

        // Cartesian Product: Given two lists [1, 2] and [3, 4], return all pairs [(1,3), (1,4), (2,3), (2,4)].
        // Hint : Use the flatMap operation on streams, which effectively creates nested loops.
        System.out.println("--- Q3: Cartesian Product ---");
        List<List<Integer>> cartesianPairs = list1.stream()
                .flatMap(i -> list2.stream()
                        .map(j -> Arrays.asList(i, j)) // map(item2 -> Arrays.asList((Object) item1, (Object) item2))
                )
                .collect(Collectors.toList());
        cartesianPairs.stream().forEach(System.out::println);

        System.out.println("--- Q4: Find Pairs (divisible by 3)---");
        // Find Pairs: Find all pairs of numbers from two different lists whose sum is divisible by 3.
        List<int[]> cartesianPairsArr = list1.stream()
                .flatMap(i -> list2.stream()
                        .map(j -> new int[]{i, j})
                )
                .filter(pair -> (pair[0] + pair[1]) % 3 == 0)
                .collect(Collectors.toList());
        cartesianPairsArr.stream().forEach(arr -> System.out.println(Arrays.toString(arr)) );


        System.out.println("--- Q5: Char Extraction ---");
        //Char Extraction: Convert a List<String> into a List<Character> containing all characters from all strings.
        List<Character> characters = sentences.stream()
                .flatMap(word -> word.chars()
                        .mapToObj(c -> (char) c)
                )
                .collect(Collectors.toList());
        characters.forEach(System.out::println);

        // II. Grouping & Collecting (The "Employee" Questions)

        // --- For Q6 - Q12, Q21 (Employee Questions) ---
        List<Employee> employees = Arrays.asList(
                new Employee("Alice", "IT", 75000, 28),
                new Employee("Bob", "IT", 60000, 24),
                new Employee("John", "IT", 65000, 24),
                new Employee("Charlie", "HR", 50000, 35),
                new Employee("David", "HR", 55000, 40),
                new Employee("Eve", "Sales", 85000, 30),
                new Employee("Frank", "Sales", 45000, 22)
        );

        //Group by Dept: Map employees by Department (Map<String, List<Employee>>).
        System.out.println("--- Q6: Group by Dept ---");
        Map<String, List<Employee>> empByDeptList = employees.stream()
                .collect((Collectors.groupingBy(Employee::getDept)));
        empByDeptList.entrySet().stream().forEach(System.out::println);

        //Count by Dept: Map Dept to employee count (Map<String, Long>).
        System.out.println("--- Q7: Count by Dept ---");
        Map<String, Long> empDeptCount = employees.stream()
                .collect(Collectors.groupingBy(
                        Employee::getDept, // Classifier
                        LinkedHashMap::new, // mapFactory - HashMap(by default),TreeMap,LinkedHashMap
                        Collectors.counting() // Downstream Collector
                ));
        empDeptCount.entrySet().stream().forEach(System.out::println);

        //Sum Salary: Calculate the total salary of all employees in the "IT" department.
        System.out.println("--- Q8: Sum Salary ---");
        double totalSalaryIT = employees.stream()
                .filter(emp -> emp.getDept().equals("IT"))
                .mapToDouble(Employee::getSalary) // emp -> emp.getSalary()
                .sum();
        System.out.println("Total salary IT: " + totalSalaryIT);

        //Max Salary per Dept: Map Dept to the highest-paid employee in that Dept (Map<String, Optional<Employee>>).
        System.out.println("--- Q9: Max Salary per Dept ---");
        // Dept - Employee(highest paid)
        Map<String, Optional<Employee>> highestSalaryByDept = employees.stream()
                .collect(Collectors.groupingBy(
                        Employee::getDept,
                        Collectors.maxBy(Comparator.comparingDouble(Employee::getSalary))
                        //Within each department group, it finds the employee with the maximum salary using a Comparator
                ));
        highestSalaryByDept.entrySet().stream().forEach(System.out::println);

        //Partitioning: Split employees into two lists: those older than 30 and those 30 or younger (Map<Boolean, List<Employee>>).
        System.out.println("--- Q10: Partitioning ---");
        Map<Boolean, List<Employee>> empOldAndYoung = employees.stream()
                .collect(Collectors.partitioningBy(emp -> emp.getAge() > 30));
        empOldAndYoung.entrySet().stream().forEach(entry -> {
            if(entry.getKey()) {
                System.out.println("Employees over 30 : ");
            } else {
                System.out.println("Employees <= 30 : ");
            }
            System.out.println(entry.getValue());
        });
        // TIPS  : Iterate through the entry set and print both key and value
//        map.entrySet().stream().forEach(entry ->
//                System.out.println("Key: " + entry.getKey() + ", Value: " + entry.getValue())
//        )

        System.out.println("--- Q11: Name Mapping ---");
        //Name Mapping: Group by Department, but the value should be a List of Names only, not the whole Employee object.
        Map<String, LinkedList<Employee>> empNameByDept = employees.stream()
                .collect(Collectors.groupingBy(
                        Employee::getDept,
                        Collectors.toCollection(LinkedList::new)
                ));
        empNameByDept.entrySet().stream().forEach(System.out::println);
        empNameByDept.entrySet().stream()
                .forEach(entry -> System.out.println("Dept : "+ entry.getKey() +" -> employees : "+entry.getValue()));

        System.out.println("--- Q12: Complex Key ---");
        //Complex Key: Group employees by Department, and within each Dept, group them by Age (Map<String, Map<Integer, List<Employee>>>).
        Map<String, Map<Integer, List<Employee>>> empGroupedByDeptThenAge = employees.stream()
                .collect(Collectors.groupingBy(
                                Employee::getDept,
                                Collectors.groupingBy(Employee::getAge)
                        )
                );
        empGroupedByDeptThenAge.entrySet().stream().forEach(System.out::println);

        // III. Finding & Matching

        // --- For Q13, Q17, Q19 (Strings/Chars) ---
        String str = "banana";
        String palindromeCheck = "racecar";

        System.out.println("--- Q13: First Non-Repeated ---");
        // First Non-Repeated: Find the first non-repeating character in a string.
        Optional<Map.Entry<Character, Long>> firstDistinctChar = str.chars()
                .mapToObj(c -> (char) c)
                .collect(Collectors.groupingBy(
                                Function.identity(),
                                LinkedHashMap::new,
                                Collectors.counting()
                        )
                )
                .entrySet().stream()
                .filter(e -> e.getValue() == 1)
                .findFirst();
        if(firstDistinctChar.isPresent()) {
            System.out.println("In \""+str+"\" firstNonRepeatingChar: " + firstDistinctChar.get().getKey());
        }
        // v2.0 : get char directly
        Optional<Character> result = str.chars()
                .mapToObj(c -> (char) c)
                .collect(Collectors.groupingBy(
                                Function.identity(),
                                LinkedHashMap::new,
                                Collectors.counting()
                        )
                )
                .entrySet().stream()
                .filter(e -> e.getValue() == 1)
                .map(Map.Entry::getKey)
                .findFirst();
        result.ifPresent(System.out::println);

        String input = "aassddff";
        Optional<Character> result2 = input.chars()
                .mapToObj(c -> (char) c)
                .collect(Collectors.groupingBy(
                                Function.identity(), // c -> c
                                LinkedHashMap::new, // preserves character order
                                Collectors.counting()
                        )
                )
                .entrySet().stream()
                .filter(e -> e.getValue() == 1)
                .map(Map.Entry::getKey)
                .findFirst(); // Optional<Character> result =
            //  .orElse(null); Character result =
        result2.ifPresentOrElse(
                System.out::println,
                () -> System.out.println("\""+input + "\" has No non repeating character")
        );

        Set<Character> seen = new HashSet<>();
        Optional<Character> firstRepeatingChar = str.chars()
                .mapToObj(c -> (char) c)
                .filter(c -> !seen.add(c))
                .findFirst();
        if (firstRepeatingChar.isPresent()) {
            System.out.println("In \""+str+"\" firstRepeatingChar: " + firstRepeatingChar.get());
        }

        System.out.println("--- Q17: Frequency Map --- : "+ str );
        // Frequency Map: Create a map of character frequencies in a string (case-insensitive).
        Map<Character, Long> charFreqMap = str.chars()
                .mapToObj(c -> (char) c)
                .collect(Collectors.groupingBy(
                        c -> c,
                        LinkedHashMap::new,
                        Collectors.counting()
                ));
        charFreqMap.entrySet().stream().forEach(System.out::println);


        System.out.println("--- Q19: Palindrome Check ---");
        // Palindrome Check: Check if a String is a palindrome using streams.
        boolean isPalindrome = IntStream.rangeClosed(1, palindromeCheck.length() / 2)
                .noneMatch(i -> palindromeCheck.charAt(i - 1) != palindromeCheck.charAt(palindromeCheck.length() - i));
        System.out.println(palindromeCheck+ " isPalindrome: " + isPalindrome);


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
        System.out.println("--- Random numbers ---");
        // nestedList.stream()...
        Stream.generate(Math::random)
                .map(n->n*100)
                .map(Math::floor)
                .mapToInt(d-> d.intValue())
                .limit(5)
                .forEach(System.out::println);

        //

    }
}