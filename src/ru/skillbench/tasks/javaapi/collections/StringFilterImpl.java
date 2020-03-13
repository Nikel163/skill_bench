package ru.skillbench.tasks.javaapi.collections;


import java.util.*;

public class StringFilterImpl implements StringFilter {

    private List<String> strs = new LinkedList<>();
    private final Set<Character> digits = new HashSet<>(Arrays.asList('0', '1', '2', '3', '4', '5', '6', '7', '8', '9'));
    public void add(String s) {
        if (!strs.contains(s)) {
            strs.add(s);
        }
    }

    public boolean remove(String s) {
        return strs.remove(s);
    }

    public void removeAll() {
        this.strs.clear();
    }

    public Collection<String> getCollection() {
        return this.strs;
    }

    public Iterator<String> getStringsContaining(String chars) {
        if(chars == null || chars.isEmpty()) {
            return strs.iterator();
        }

        return strs.stream().filter(it -> it.contains(chars)).iterator();
    }

    public Iterator<String> getStringsStartingWith(String begin) {
        if(begin == null || begin.isEmpty()) {
            return strs.iterator();
        }

        return strs.stream().filter(it -> it.startsWith(begin)).iterator();
    }

    public Iterator<String> getStringsByNumberFormat(String format) {
        List<String> res = new LinkedList<>();
        for (String x : strs) {
            boolean addded = true;
            for (int i = 0; i < x.toCharArray().length; i++) {
                if (format.charAt(i) != x.charAt(i) && !digits.contains(x.charAt(i))) {
                    addded = false;
                    break;
                } else if (x.charAt(i) == '#') {
                    addded = false;
                    break;
                }
            }
            if (addded) {
                res.add(x);
            }
        }
        return res.iterator();
    }

    public Iterator<String> getStringsByPattern(String pattern) {
        return null;
    }
}
