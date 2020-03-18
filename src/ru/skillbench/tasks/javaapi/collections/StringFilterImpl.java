package ru.skillbench.tasks.javaapi.collections;


import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

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

        return strs.stream().filter(it -> it != null && it.contains(chars)).iterator();
    }

    public Iterator<String> getStringsStartingWith(String begin) {
        if(begin == null || begin.isEmpty()) {
            return strs.iterator();
        }

        return strs.stream().filter(it -> it != null && it.startsWith(begin.toLowerCase())).iterator();
    }

    public Iterator<String> getStringsByNumberFormat(String format) {
        if (format == null || format.isEmpty()) {
            return strs.iterator();
        }

        List<String> res = new LinkedList<>();
        for (String x : strs) {
            if (x == null) continue;
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
        if (pattern == null || pattern.isEmpty()) {
            return strs.iterator();
        }

        String regex = pattern.replace("*", ".*");
        Pattern pat = Pattern.compile(regex);
        Matcher matcher;

        List<String> res = new LinkedList<>();
        for (String x : strs) {
            if (x == null) continue;
            if (pat.matcher(x).matches()) res.add(x);
        }
        return res.iterator();
    }
}
