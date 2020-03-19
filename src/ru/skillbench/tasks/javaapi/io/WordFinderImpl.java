package ru.skillbench.tasks.javaapi.io;

import sun.nio.ch.IOUtil;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;
import java.util.function.Supplier;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class WordFinderImpl implements WordFinder {

    private String text;
    private List<String> result;

    @Override
    public String getText() {
        return this.text;
    }

    @Override
    public void setText(String text) {
        if (text == null) {
            throw new IllegalArgumentException();
        }
        this.text = text;
    }

    @Override
    public void setInputStream(InputStream is) throws IOException {
        if (is == null) {
            throw new IllegalArgumentException();
        }
        this.text = new Scanner(is).useDelimiter("\\A").next();
        is.close();
    }

    @Override
    public void setFilePath(String filePath) throws IOException {
        if (filePath == null) {
            throw new IllegalArgumentException();
        }

        this.text = new String(Files.readAllBytes(Paths.get(filePath)));
    }

    @Override
    public void setResource(String resourceName) throws IOException {
        if (resourceName == null) {
            throw new IllegalArgumentException();
        }

        InputStream is = this.getClass().getResourceAsStream(resourceName);
        this.text = new Scanner(is).useDelimiter("\\A").next();
        is.close();
    }

    @Override
    public Stream<String> findWordsStartWith(String begin) {
        if (this.text == null) {
            throw new IllegalStateException();
        }

        List<String> res = (begin != null) ? Arrays.stream(this.text.toLowerCase().split("\\s"))
                .filter(it -> it.startsWith(begin.toLowerCase()) && !it.isEmpty()).collect(Collectors.toList()) :
                Arrays.stream(this.text.toLowerCase().split("\\s")).filter(it -> !it.isEmpty()).collect(Collectors.toList());
        this.result = res;
        return res.stream();
    }

    @Override
    public void writeWords(OutputStream os) throws IOException {
        if (this.result == null) {
            throw new IllegalStateException();
        }

        StringBuilder sb = new StringBuilder();
        this.result.stream().sorted().forEach(it -> sb.append(it).append(" "));
        os.write(sb.toString().getBytes());
        os.close();
    }
}
