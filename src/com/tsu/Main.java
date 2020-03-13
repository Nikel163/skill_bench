package com.tsu;

import ru.skillbench.tasks.text.regex.CurriculumVitae;
import ru.skillbench.tasks.text.regex.CurriculumVitaeImpl;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Main {

    public static void main(String[] args) {
        Pattern pattern = Pattern.compile(CurriculumVitae.PHONE_PATTERN);
        Matcher matcher;

//        String text = "(609) 234-5678";
        String text = "       Latin\nDosdksdfazxc Llakdsjfadsf kdlsf@jasld.kfj aldfj; lzkjcvlkjzxcv. (123)456 7890 123SKLjalskdjcczlxkcv";
        CurriculumVitaeImpl c = new CurriculumVitaeImpl();
        c.setText(text);
        c.hide("aldfj;");
        c.hide("lzkjcvlkjzxcv.");
        c.hidePhone("(123)456 7890");
        System.out.println(c.getText());
        System.out.println(c.unhideAll());
        System.out.println(c.getFullName());
        System.out.println(c.getFirstName());
        System.out.println(c.getMiddleName());
        System.out.println(c.getLastName());
        for (CurriculumVitae.Phone x : c.getPhones()) {
            System.out.println(x.toString());
        }
    }

}
