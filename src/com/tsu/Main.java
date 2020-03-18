package com.tsu;

import ru.skillbench.tasks.text.WordCounter;
import ru.skillbench.tasks.text.WordCounterImpl;

import java.util.LinkedList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Main {

    public static void main(String[] args) {

        String text = " asldkfj      alksdjfal j asd lsdf jxc k. A xcv the   ";

        WordCounter wc = new WordCounterImpl();
        wc.setText(text);
        System.out.println(wc.getWordCounts().toString());

    }

}
