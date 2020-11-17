package co.sebd.ssl.ismsplus.test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

/**
 * Copyright &copy; Soft Engine Inc.
 * Created on 24/01/16
 * Created By : Khomeni
 * Edited By : Khomeni &
 * Last Edited on : 24/01/16
 * Version : 1.0
 */

public class Test {
    public static void main(String[] args) {
        int subListCounter = 80;
        int i = subListCounter = 90;
        System.out.println("subListCounter = " + subListCounter);
        System.out.println("i = " + i);
    }
    public static void main3(String[] args) {
        List<Integer> numbers = new ArrayList<Integer>(
                Arrays.asList(1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 13, 14, 15, 16)
        );
        int limit = 3;
        int start, end;
        int total = numbers.size();
        for (int i = 0; i < total; i += limit) {
            start = i;
            end = i + limit;
            List<Integer> subList = numbers.subList(start, end > total ? total : end);
            System.out.println("subList = " + subList);
        }

        /*List<Integer> head = numbers.subList(0, 4);
        List<Integer> tail = numbers.subList(4, 8);
        System.out.println(head); // prints "[5, 3, 1, 2]"
        System.out.println(tail); // prints "[9, 5, 0, 7]"
        Collections.sort(head);
        System.out.println(numbers); // prints "[1, 2, 3, 5, 9, 5, 0, 7]"
        tail.add(-1);
        System.out.println(numbers); // prints "[1, 2, 3, 5, 9, 5, 0, 7, -1]"*/
    }

    public static void main2(String[] args) {
        List<String> numbers = new ArrayList<String>();
        numbers.add("101");
        numbers.add("200");
        numbers.add("301");
        numbers.add("401");
        numbers.add("402");
        numbers.add("403");
        numbers.add("404");
        numbers.add("405");
        numbers.add("406");

        int counter = 0;
        for (String i : numbers) {
            System.out.print(i + ",");
            counter++;
            if (counter % 3 == 0) {
                System.out.println();
                System.out.println("Sending Request....");
            }
        }

    }
}
