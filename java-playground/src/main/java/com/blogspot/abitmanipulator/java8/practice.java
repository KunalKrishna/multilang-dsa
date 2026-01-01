package com.blogspot.abitmanipulator.java8;

import java.util.*;
import java.util.function.Function;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import java.util.stream.Stream;

/***
 *  Objective :
 *  1. Different ways to create string
 *      a. data source : Array, Collections(List, Set, Map), String(char stream),
 *      b. directly creating - for experiment
 *      c. from files
 *
 */
public class practice {

    static void main() {
        Stream<Integer> singleUseIntStream = List.of(1,2,3,4,5,6,7,8,9).stream(); // can only use once
        //  1. Separate Odd and Even Numbers
        Map<Boolean, List<Integer>> oddEvenMap = singleUseIntStream.collect(Collectors.partitioningBy(x -> x % 2 == 0));
        System.out.println(oddEvenMap);

        List<Integer> integerList = List.of(1, 2,2, 3,3,3, 4,4,4,4, 5,5,5,5,5, 6,6,6,6,6,6, 7,7,7,7,7,7,7, 8,9);

        List<Boolean> even = integerList.stream()
                                        .map(i -> i % 2 == 0)
                                        .collect(Collectors.toList());
        even.add(true); // can modify this list?
        even.forEach(System.out::println);

        //  2. Frequency of each Char in a String
        String str = "a quick brown fox jumps over the lazy dog";

        //  2a. Standard Approach (groupingBy)
        Map<Character, Long> freqMap = str.chars()
                                        .mapToObj(ch -> (char) ch) // Box int to Character
                                        .collect(Collectors.groupingBy(
                                            Function.identity(),  // Key : the character itself
                                            Collectors.counting() // Value : count occurences
                                        ));
        System.out.println(freqMap);
        //  2b. Control Approach (toMap) : Integer instead of Long
        Map<Character, Integer> freqMap2 = str.chars()
                                        .mapToObj(ch -> (char) ch)
                                        .collect(Collectors.toMap(
                                                k -> k, // Key
                                                v -> 1, // Initial value
                                                Integer::sum  // Merger function: (oldVal, newVal) ->oldVal + newVal
                                        ));
        System.out.println(freqMap2);
        //  2c. Preserving Order (LinkedHashMap) : "Find the first non-repeating char"
        LinkedHashMap<Character, Long> orderedFreqMap = str.chars().mapToObj(ch -> (char) ch)
                .collect(Collectors.groupingBy(
                        Function.identity(),
                        LinkedHashMap::new, //Specific Map Factory
                        Collectors.counting()
                ));
        System.out.println(orderedFreqMap);

        //  3. Sort the List in reverse order.
        System.out.println("Reverse Sorted List : ");
        List<Integer> reverseSortedList = integerList.stream()
                .sorted(Collections.reverseOrder()) //Comparator.reverseOrder()
                .collect(Collectors.toList());
        reverseSortedList.forEach(System.out::println);

        //  Multiples of 5
        System.out.println("Multiples of 5 : ");
        List<Integer> intList = List.of(0,1,2,3,5,12,10,15,20,25,30,35,40,45);
        intList.stream()
                .filter(integer ->  integer % 5 == 0)
                .forEach(System.out::println);

        //  Merge two unsorted Arrays into a sorted Array
        System.out.println("Merge two unsorted Arrays into a sorted Array : ");
        int[] a = {7,2,5,1,19,12}, b = {40,1,3,0,12,4,2,7,91};
        int[] mergedArray = IntStream.concat(Arrays.stream(a), Arrays.stream(b))
                .sorted()
                .toArray(); // or .forEach(System.out::println);
        Arrays.stream(mergedArray)
                .forEach(System.out::println);

        //  Merge two unsorted Arrays into Single Sorted Array w/o Duplicates
        System.out.println("Merge two unsorted Arrays into Single Sorted Array w/o Duplicates : ");
        IntStream.concat(Arrays.stream(a), Arrays.stream(b))
                    .sorted() // primitive stream doesn't allow argument - only sorts in natural ascending order
                    .distinct()
                    .forEach(System.out::println);

        //  Three Max and Min numbers from the List
        System.out.println("Three Max and Min numbers from the List : ");
        System.out.println("Array : "+ Arrays.toString(a));
        System.out.println("first 3 - use .limit()");
        Arrays.stream(a)
                    .limit(3)
                    .forEach(System.out::println);
        //  Min 3
        System.out.println("Min 3 : ");
        Arrays.stream(a)
                .sorted()
                .limit(3) //first three elements
                .forEach(System.out::println);
        //  max 3
        System.out.println("Max 3 : ");
        Arrays.stream(a)
                .boxed() // sorted(argument) works only on Object Stream, not Primitive Stream
                .sorted(Comparator.reverseOrder()) // The sorted() method on an IntStream does not accept arguments; it only sorts in natural ascending order.
                .limit(3)
                .forEach(System.out::println);

        // PrimitiveStream --> ObjStream : boxed()
        // ObjStream --> PrimitiveStream : mapToXxx() and flatMapToXxx()

        /**
         * Learning : PrimitiveStream vs ObjectStream wrt sorted()
         *    Arrays.stream(a).
         *          .sorted(Comparator.reverseOrder()) // Error
         *          .limit(3)
         *          .forEach(System.out::println);
         * IntStream, LongStream, and DoubleStream.
         * This error happens because your array a is likely a primitive int[] (or long[]/double[]).
         * Arrays.stream(int[]) returns an IntStream. The sorted() method on an IntStream does not accept arguments; it only sorts in natural ascending order.
         * To use a Comparator, you must convert the primitive stream to an object stream (Stream<Integer>).
         */

        //  Sort Strings in Ascending order of their length
        System.out.println("Sort Strings in Ascending order of their length : ");
        String[] words = {"ate", "fate", "rotten", "climax", "end", "far", "love","i", "87", "3een"};
        Arrays.stream(words)
                .sorted(Comparator.comparing(String::length) // The primary sort key.
                        .thenComparing(Comparator.naturalOrder())) // Applied only when the primary keys are equal (i.e., same word length).
                    // Uses the String class's built-in comparison : compareTo() that defines its "natural" sorting order.
                .forEach(System.out::println);

        //  Sum & Avg of all Elements of an Array
        System.out.println("Sum & Avg of all Elements of an Array : ");
        System.out.println(Arrays.toString(a));
        int sum = Arrays.stream(a).sum();
        OptionalDouble average = Arrays.stream(a).average();
        System.out.println("sum ="+ sum + " average = " + average.getAsDouble());

        //  Reverse an integer array.
        System.out.println("Reverse an integer array : ");
        System.out.println(Arrays.toString(a));//int[] a = {7,2,5,1,19,12} N=6
        int[] reversedArr = IntStream.rangeClosed(1, a.length)
                .map(i -> a[a.length - i]) // n-1, n-2, n-3, ...3,2,1
                .toArray();
        System.out.println(Arrays.toString(reversedArr));
        /*
        * i    a.length-i          Value Mapped (a[index])
        * -------------------------------------------------
        * 1     6-1 = 5              12
        * 2     6-2 = 4              19
        * 3     6-3 = 3              1
        * 4     6-2 = 2              5
        * 5     6-1 = 1              2
        * 6     6-0 = 0              7
        * */
        int[] reversed = IntStream.range(0, a.length)  // Generates 0, 1, ..., N-1
                .map(i -> a[a.length - 1 - i])     // Maps 0 to (N-1), 1 to (N-2)...
                .toArray();
        System.out.println(Arrays.toString(reversed));

        //  Palindrome
        System.out.println("Palindrome : ");
        String w = "racacar";
        boolean b1 = IntStream.range(1, w.length() / 2)
                        .noneMatch(i -> w.charAt(w.length() - i) != w.charAt(i-1));
            //Returns whether no elements of this stream match the provided predicate
        System.out.println(w +" palindrome ? "+ b1);

        System.out.println("---");

        //  Last element of an array
        System.out.println(Arrays.toString(a));
        System.out.println("Last element of an array : " + Arrays.stream(a)
                                                            .skip(a.length-1)
                                                            .findFirst().getAsInt());
        System.out.println("Second Last element of an array : " + Arrays.stream(a)
                                                            .skip(a.length-2)
                                                            .findFirst().getAsInt());

        //  Remove Duplicates from list
        System.out.println("Remove Duplicates from list : ");
        integerList.stream()
                .distinct()
                .forEach(System.out::println);

        //  Frequency of Each Element in An Array
        System.out.println("Frequency of Each Element in An Array : ");
        Map<Integer, Long> intFreqMap = integerList.stream()
                .collect(Collectors.groupingBy(
                        Function.identity(),
                        Collectors.counting()
                ));
        System.out.println(intFreqMap);

        //  Join list of strings with Prefix, Suffix and Delimiter
        System.out.println("Join list of strings with Prefix, Suffix and Delimiter : ");
        String collect = Arrays.stream(words)
                .collect(Collectors.joining("|", "-", "-end"));
        System.out.println(collect);

        // Sum of All Digits of a Number
        System.out.println("Sum of All Digits of a Number : ");
        int num = 1234567;
        int sumOfDigits = IntStream.range(0, String.valueOf(num).length())
                .map(i -> Character.getNumericValue(String.valueOf(num).charAt(i)))
                .sum();
        System.out.println(sumOfDigits);
        int sumD = Stream.of(String.valueOf(num).split(""))
                .collect(Collectors.summingInt(Integer::parseInt));


        // Common Elements Between Tow Arrays/Lists
        System.out.println("Common Elements Between Two Arrays/Lists : ");
        List<Integer> aList = List.of(2,4,6,8,10,12,14,16,18,20,22,24,26,28,30,32,34,36,38,40);
        List<Integer> bList = List.of(5,10,15,20,25,30,35,40,45,50);
        System.out.println(aList);
        System.out.println(bList);
        aList.stream()
                .filter(bList::contains)
                .forEach(System.out::println);

        // Reverse each Word of A String
        String sentence = "A quick brown fox jumped over the lazy dog";//"I ate card drac eta I"
        System.out.println("Reverse each Word of A String : \n"+ sentence);
        String sentenceRev = Arrays.stream(sentence.split(" "))
                .map(String::toLowerCase) // (optional)
                .map(word -> new StringBuilder(word).reverse())// prefer Builder(thread-safe,slower) over Buffer for local vars
                .collect(Collectors.joining(" "));
        System.out.println(sentenceRev);

        // Two Alternative Approaches

        // 1. Functional Approach(reduce) : by accumulating characters in reverse order: (accumulator, newChar) -> newChar + accumulator.
        String res = Arrays.stream(sentence.split(" "))
                .map(word -> word.chars()
                        .mapToObj(c -> Character.toString((char) c))
                        .reduce("", (s1, s2) -> s2 + s1)  // Reverse logic: Prepend new char
                )
                .collect(Collectors.joining(" "));
        System.out.println(res);

        // 2. Index Approach(IntStream)
        String res2 = Arrays.stream(sentence.split(" "))
                .map(word -> IntStream.range(0, word.length())
                        .mapToObj(i-> String.valueOf(word.charAt(word.length()-i-1)))
                        .collect(Collectors.joining())
                )
                .collect(Collectors.joining(" "));
        System.out.println(res2);

        String res3 = Arrays.stream(sentence.split(" "))
                .map(word -> IntStream.rangeClosed(1, word.length())
                        .mapToObj(i-> String.valueOf(word.charAt(word.length()-i)))
                        .collect(Collectors.joining())
                )
                .collect(Collectors.joining(" "));
        System.out.println(res3);

        //  Find Strings which start with Number
        System.out.println("Find Strings which start with Number : ");
        Arrays.stream(words)
                .filter(word -> Character.isDigit(word.charAt(0)))
                .forEach(System.out::println);

        //  Find duplicates elements from an array
        System.out.println("Find duplicates elements from an array : ");
        // 1. Set.add() Approach
        int[] nums = {1, 2, 3, 1, 4, 2};
        Set<Integer> seen = new HashSet<>();

        Set<Integer> duplicates = Arrays.stream(nums)
                .boxed() // Essential: convert int to Integer so it can interact with the HashSet.
                .filter(n -> !seen.add(n)) // If add returns false, it's a duplicate
                .collect(Collectors.toSet()); // Output: [1, 2]
        System.out.println(duplicates);


        // 2. Frequency Map Approach (Pure Stream) .groupingBy()
        String[] fruits = {"apple", "banana", "apple", "orange", "banana","asd","asd","asd","asd"};
        List<String> duplicateFruits = Arrays.stream(fruits)
                .collect(Collectors.groupingBy(
                        Function.identity(),
                        Collectors.counting()
                )) // Map<String, Long> -> {apple=2, banana=2, orange=1}
                .entrySet().stream()
                .filter(entry -> entry.getValue() > 1) // Filter counts > 1
                .map(Map.Entry::getKey)
                .collect(Collectors.toList());
                // Output: [apple, banana]
        System.out.println(duplicateFruits);

        //  Fibonacci Series
        System.out.println("Fibonacci Series : ");
        Stream.iterate(new int[] {0,1}, f -> new int[] {f[1], f[0]+f[1]})// Since streams are usually stateless, we cheat by passing a state container : new int[] { current, next }
                /** iterate(final T seed, final UnaryOperator<T> f)
                 * seed – the initial element
                 * f – a function to be applied to the previous element to produce a new element
                 */
                .limit(10)
                .map(f-> f[0])
                .forEach(i-> System.out.print(i + " "));


    }
}
