package com.blogspot.abitmanipulator.java8;

import java.util.List;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;
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
        // 1. Separate Odd and Even Numbers
        Map<Boolean, List<Integer>> oddEvenMap = singleUseIntStream.collect(Collectors.partitioningBy(x -> x % 2 == 0));
        System.out.println(oddEvenMap);

        List<Integer> integerList = List.of(1,2,3,4,5,6,7,8,9);

        List<Boolean> even = integerList.stream()
                                        .map(i -> i % 2 == 0)
                                        .collect(Collectors.toList());
        even.forEach(System.out::println);

        // 2. Frequency of each Char in a String
        String str = "a quick brown fox jumps over the lazy dog";

        // Standard Approach (grouping by)
        Map<Character, Long> freqMap = str.chars()
                        .mapToObj(ch -> (char) ch) // Box int to Character
                        .collect(Collectors.groupingBy(
                            Function.identity(),  // Key : the character itself
                            Collectors.counting() // Value : count occurences
                        ));
        System.out.println(freqMap);

//        Map<Character, Integer> freqMap = str.chars().
//        Stream<Character> characterStream = str.chars().mapToObj(x -> (char) x);




    }
}
