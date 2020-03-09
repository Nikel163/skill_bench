package com.tsu;

import ru.skillbench.tasks.text.ContactCard;
import ru.skillbench.tasks.text.ContactCardImpl;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        String text = "FN:Андрей Петров\r\nORG:Фриланс\r\nBDAY:24-11-1991";
        ContactCard c = new ContactCardImpl().getInstance("BEGIN:VCARD\r\n"+text+"\r\nEND:VCARD");
        System.out.println(c.getAge());
    }

}
