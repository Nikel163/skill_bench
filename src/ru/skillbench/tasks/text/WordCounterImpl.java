package ru.skillbench.tasks.text;

import java.io.PrintStream;
import java.util.*;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

public class WordCounterImpl implements WordCounter {

    private String text;

    @Override
    public void setText(String text) {
        this.text = text;
    }

    @Override
    public String getText() {
        return this.text;
    }

    @Override
    public Map<String, Long> getWordCounts() {
        if (this.text == null) {
            throw new IllegalStateException();
        }

        List<String> text = Arrays.stream(this.text.toLowerCase().split("\\s"))
                .filter(it -> !it.isEmpty() && !it.startsWith("<") && !it.endsWith(">"))
                .collect(Collectors.toList());


        Map<String, Long> result = new HashMap<>();
        while (text.size() != 0) {
            String current = text.get(0);
            result.computeIfAbsent(current, v -> {
                boolean removed = text.remove(current);
                long counter = 1L;
                while (removed) {
                    removed = text.remove(current);
                    if (removed) counter++;
                }
                return counter;
            });
        }

        return result;
    }

    @Override
    public List<Map.Entry<String, Long>> getWordCountsSorted() {
        return sort(getWordCounts(), Map.Entry.comparingByValue());
    }

    @Override
    public <K extends Comparable<K>, V extends Comparable<V>> List<Map.Entry<K, V>> sort(Map<K, V> map, Comparator<Map.Entry<K, V>> comparator) {
        return map.entrySet().stream().sorted(comparator).collect(Collectors.toList());
    }

    @Override
    public <K, V> void print(List<Map.Entry<K, V>> entries, PrintStream ps) {
        for (Map.Entry k : entries) {
            ps.println(k.getKey() + " " + k.getValue());
        }
    }
}