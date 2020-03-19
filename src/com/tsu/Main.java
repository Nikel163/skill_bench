package com.tsu;

import ru.skillbench.tasks.javaapi.io.WordFinder;
import ru.skillbench.tasks.javaapi.io.WordFinderImpl;
import ru.skillbench.tasks.javaapi.reflect.Reflector;
import ru.skillbench.tasks.javaapi.reflect.ReflectorImpl;

import java.util.List;
import java.util.stream.Collectors;

public class Main {

    public static void main(String[] args) {
        WordFinder wf = new WordFinderImpl();
        wf.setText("the these that than there things this their them threatened they");
        wf.findWordsStartWith("Th").forEach(System.out::println);
    }

}

