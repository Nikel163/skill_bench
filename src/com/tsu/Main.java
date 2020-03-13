package com.tsu;

import ru.skillbench.tasks.javaapi.collections.StringFilterImpl;
import ru.skillbench.tasks.text.regex.CurriculumVitae;
import ru.skillbench.tasks.text.regex.CurriculumVitaeImpl;

import java.util.Iterator;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Main {

    public static void main(String[] args) {
        Pattern pattern = Pattern.compile(CurriculumVitae.PHONE_PATTERN);
        Matcher matcher;

        StringFilterImpl c = new StringFilterImpl();
        c.add("(#58)103-5678");
        c.add("(145)789-1111");
        c.add("(###)###-####");
        c.add("asdfzxc");
        c.add("sdfsdfaer31");
        c.add(" vzx");

        Iterator it = c.getStringsByNumberFormat("(###)###-####");
        while (it.hasNext()) {
            System.out.println(it.next());
        }
    }

}
