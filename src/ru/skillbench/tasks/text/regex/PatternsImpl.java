package ru.skillbench.tasks.text.regex;

import java.util.LinkedList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class PatternsImpl implements Patterns {
    @Override
    public Pattern getSQLIdentifierPattern() {
        return Pattern.compile("[A-Za-z][\\w]{0,29}");
    }

    @Override
    public Pattern getEmailPattern() {
        return Pattern.compile("[A-Za-z0-9]([A-Za-z0-9_.-]{0,20}[A-Za-z0-9])?@([A-Za-z0-9][A-Za-z0-9-]*[A-Za-z0-9].)+(ru|com|net|org)");
    }

    @Override
    public Pattern getHrefTagPattern() {
        return Pattern.compile("<\\s*a\\s*href\\s*=\\s*(\".*\"|[^\"\\s]*).*/?\\s*>", Pattern.CASE_INSENSITIVE);
    }

    @Override
    public List<String> findAll(String input, Pattern pattern) {
        List<String> result = new LinkedList<>();
        Matcher matcher = pattern.matcher(input);
        while (matcher.find()) {
            result.add(matcher.group(0));
        }
        return result;
    }

    @Override
    public int countMatches(String input, String regex) {
        return findAll(input, Pattern.compile(regex, Pattern.CASE_INSENSITIVE)).size();
    }
}
