package com.tests.task1.ex2;

import java.util.*;

public class Task {

    public static List<String> finder(List<String> strings) {
        if(strings == null)
            return Collections.emptyList();
        Map<String, Integer> hashtags = new HashMap<>();
        List<String> result = new ArrayList<>();
        for (String str: strings) {
            Arrays.stream(str.split(" "))
                    .filter(x -> x.startsWith("#"))
                    .distinct()
                    .forEach(x -> hashtags.put(x, hashtags.getOrDefault(x, 0)+1));
        }
        hashtags.entrySet().stream()
                .sorted(Map.Entry.comparingByValue())
                .skip(hashtags.size() < 5 ? 0 : hashtags.size() - 5)
                .forEach(x -> result.add(x.getKey()));
        Collections.reverse(result);
        return result;
    }

    public static void main(String[] args) {
        List<String> strings = List.of("#1 #1 #1 1 #1 #2 #2 #2 #4", "#4 #2", "#1 #2 #3 #4 #5 #6" , " #1 #2 #3 #4 #5 234 24 4 4 4 #2", "4 #2");
        System.out.println(finder(strings));
    }

}
