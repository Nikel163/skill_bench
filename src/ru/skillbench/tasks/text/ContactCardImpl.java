package ru.skillbench.tasks.text;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.Period;
import java.util.*;
import java.util.regex.Pattern;

public class ContactCardImpl implements ContactCard{

    public String FN;
    public String ORG;
    public String GENDER;
    public Calendar BDAY = null;
    public List<Phone> TEL = new LinkedList<>();

    @Override
    public ContactCard getInstance(Scanner scanner) {
        if (!scanner.nextLine().equals("BEGIN:VCARD")) {
            throw new InputMismatchException();
        }

        String parsed = scanner.nextLine();
        while (scanner.hasNextLine()) {
            if (parsed.contains(";")) {
                if (parsed.contains(";") && parsed.contains("TYPE=") && parsed.contains(":")) {
                    String telInfo = parsed.split(";")[1];
                    String[] telData = {
                            telInfo.substring(telInfo.indexOf("=")+1, telInfo.indexOf(":")),
                            telInfo.substring(telInfo.indexOf(":")+1)
                    };
                    this.TEL.add(new Phone(telData[0], telData[1]));

                } else throw new InputMismatchException();
            } else {
                String[] data = parsed.split(":");
                switch (data[0]) {
                    case "FN":
                            this.FN = data[1]; break;
                    case "ORG":
                        this.ORG = data[1];
                        break;
                    case "GENDER":
                        if ((data[1].equals("F") || data[1].equals("M"))) {
                            this.GENDER = data[1];
                        } else {
                            throw new InputMismatchException();
                        } break;
                    case "BDAY":
                            String[] bday = data[1].split("-");
                            if (bday.length == 3) {
                                LocalDate localDate = LocalDate.of(Integer.parseInt(bday[2]), Integer.parseInt(bday[1]), Integer.parseInt(bday[0]));
                                Calendar calendar = Calendar.getInstance();
                                calendar.clear();
                                calendar.set(localDate.getYear(), localDate.getMonthValue()-1, localDate.getDayOfMonth());
                                this.BDAY = calendar;
                            } else throw new InputMismatchException();
                            break;
                    default: throw new InputMismatchException();
                }
            }

            parsed = scanner.nextLine();
        }

        if (!parsed.equals("END:VCARD") || this.FN == null || this.ORG == null) {
            throw new NoSuchElementException();
        }

        return this;
    }

    @Override
    public ContactCard getInstance(String data) {
        return getInstance(new Scanner(data));
    }

    @Override
    public String getFullName() {
        return this.FN;
    }

    @Override
    public String getOrganization() {
        return this.ORG;
    }

    @Override
    public boolean isWoman() {
        if (this.GENDER == null) {
            return false;
        }

        return this.GENDER.equals("F");
    }

    @Override
    public Calendar getBirthday() {
        if (this.BDAY == null) {
            throw new NoSuchElementException();
        }

        return this.BDAY;
    }

    @Override
    public Period getAge() {
        if (this.BDAY == null) {
            throw new NoSuchElementException();
        }

        LocalDate bday = LocalDate.of(this.BDAY.get(Calendar.YEAR), this.BDAY.get(Calendar.MONTH)+1, this.BDAY.get(Calendar.DAY_OF_MONTH));
        return Period.between(bday,LocalDate.now());
    }

    @Override
    public int getAgeYears() {
        return this.getAge().getYears();
    }

    @Override
    public String getPhone(String type) {
        String res = null;
        for (Phone x : this.TEL) {
            if (x.type.equals(type)) {
                res = "(" + x.number.substring(0,3) + ") " + x.number.substring(3,6) + "-" + x.number.substring(6);
            }
        }

        if (res == null) {
            throw new NoSuchElementException();
        }

        return res;
    }

    public static class Phone {

        private static Pattern pattern = Pattern.compile("\\d{10}");

        public String type;
        public String number;

        public Phone(String type, String number) {
            if (type.isEmpty() || !pattern.matcher(number).matches()) {
                throw new InputMismatchException();
            }

            this.type = type;
            this.number = number;
        }
    }
}
