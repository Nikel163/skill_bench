package ru.skillbench.tasks.text.regex;

import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class CurriculumVitaeImpl implements CurriculumVitae {

    private static final Pattern PATTERN_PHONE = Pattern.compile(CurriculumVitae.PHONE_PATTERN);
    private static final Pattern FULL_NAME_PATTERN = Pattern.compile("([A-Z][a-z]+(\\.)?)([ \\t]([A-Z][a-z]+(\\.)?))?([ \\t]([A-Z][a-z]+(\\.)?))");

    private static final Set<Character> exclude_chars = new HashSet<>(Arrays.asList(',','.','@',' '));
    private static final Set<Character> digits = new HashSet<>(Arrays.asList('0','1','2','3','4','5','6','7','8','9'));
    private Map<String, String> hided;

    private boolean isCorrect;
    private Matcher matcher;

    private String text;

    public CurriculumVitaeImpl() {
        this.hided = new HashMap<>();
        this.isCorrect = false;
        this.text = null;
    }

    public void setText(String text) {
        this.isCorrect = true;
        hided.clear();
        this.text = text;
    }

    public String getText() {
        thr();
        return this.text;
    }

    public List<Phone> getPhones() {
        thr();
        String text = getText();
        List<Phone> phones = new LinkedList<>();
        matcher = PATTERN_PHONE.matcher(text);

        while (matcher.find()) {
            int area = (matcher.group(2) != null) ? Integer.parseInt(matcher.group(2)) : -1;
            int ext = (matcher.group(7) != null) ? Integer.parseInt(matcher.group(7)) : -1;
            phones.add(new Phone(matcher.group(0),area,ext));
        }
        return phones;
    }

    public String getFullName() {
        String text = getText();
        matcher = FULL_NAME_PATTERN.matcher(text);

        if (!matcher.find()) {
            throw new NoSuchElementException();
        }

        return matcher.group(0);

    }

    public String getFirstName() {
        String fullName = getFullName();
        matcher = FULL_NAME_PATTERN.matcher(fullName);
        matcher.find();
        return matcher.group(1);
    }

    public String getMiddleName() {
        String fullName = getFullName();
        matcher = FULL_NAME_PATTERN.matcher(fullName);
        matcher.find();
        return matcher.group(4);
    }

    public String getLastName() {
        String fullName = getFullName();
        matcher = FULL_NAME_PATTERN.matcher(fullName);
        matcher.find();
        return matcher.group(7);
    }

    public void updateLastName(String newLastName) {
        String fullName = getFullName();
        String newFullName = fullName.replace(getLastName(), newLastName);
        this.text = this.text.replace(fullName, newFullName);
    }

    public void updatePhone(Phone oldPhone, Phone newPhone) {
        String text = getText();
        if (!text.contains(oldPhone.getNumber())) {
            throw new IllegalArgumentException();
        }
        this.text = text.replace(oldPhone.getNumber(), newPhone.getNumber());
    }

    public void hide(String piece) {
        String text = getText();
        if (!text.contains(piece)) {
            throw new IllegalArgumentException();
        }
        StringBuilder newPiece = new StringBuilder();
        for (int i = 0; i < piece.length(); i++) {
            if (exclude_chars.contains(piece.charAt(i))) {
                newPiece.append(piece.charAt(i));
            } else {
                newPiece.append("X");
            }
        }
        this.hided.computeIfAbsent(piece, k -> newPiece.toString());
        this.text = text.replace(piece, newPiece.toString());
    }

    public void hidePhone(String phone) {
        String text = getText();
        if (!text.contains(phone)) {
            throw new IllegalArgumentException();
        }
        StringBuilder newPhone = new StringBuilder();
        for (int i = 0; i < phone.length(); i++) {
            if (digits.contains(phone.charAt(i))) {
                newPhone.append("X");
            } else {
                newPhone.append(phone.charAt(i));
            }
        }
        this.hided.computeIfAbsent(phone, k -> newPhone.toString());
        this.text = text.replace(phone, newPhone.toString());
    }

    public int unhideAll() {
        String text = getText();
        for (Map.Entry<String, String> entry : this.hided.entrySet()) {
            text = text.replace(entry.getValue(), entry.getKey());
        }
        this.text = text;
        return this.hided.size();
    }

    private void thr() {
        if (!this.isCorrect) {
            throw new IllegalStateException();
        }
    }
}
