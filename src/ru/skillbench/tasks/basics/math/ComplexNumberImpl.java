package ru.skillbench.tasks.basics.math;

import java.util.Arrays;
import java.util.Objects;

public class ComplexNumberImpl implements ComplexNumber {

    private double re;
    private double im;

    public ComplexNumberImpl(double re, double im) {
        set(re, im);
    }

    public ComplexNumberImpl(String va) {
        set(va);
    }

    public ComplexNumberImpl() {
        this.re = 0;
        this.im = 0;
    }

    @Override
    public double getRe() {
        return this.re;
    }

    @Override
    public double getIm() {
        return this.im;
    }

    @Override
    public boolean isReal() {
        return this.im == 0;
    }

    @Override
    public void set(double re, double im) {
        this.re = re;
        this.im = im;
    }

    @Override
    public void set(String value) throws NumberFormatException {
        String re;
        String im;
        if (value.indexOf('+',1) != -1 || value.indexOf('-',1) != -1) {
            int endInd = (value.indexOf('+',1) != -1) ? value.indexOf('+',1) : value.indexOf('-',1);
            re = value.substring(0,endInd);
            if (value.substring(endInd).endsWith("i")) {
                im = value.substring(endInd, value.length()-1);
            } else {
                throw new NumberFormatException();
            }
        } else if (value.indexOf('i') != -1) {
            re = "0";
            im = value.substring(0, value.indexOf('i'));
            if (im.equals("") && value.length() == 1) {
                im = "1";
            }
        } else {
            re = value;
            im = "0";
        }

        if (im.equals("+") || im.equals("-")) {
            im += "1";
        }
        this.re = Double.parseDouble(re);
        this.im = Double.parseDouble(im);
    }

    @Override
    public ComplexNumber copy() {
        return new ComplexNumberImpl(this.re, this.im);
    }

    @Override
    public ComplexNumber clone() throws CloneNotSupportedException {
        return this.copy();
    }

    public String toString() {
        String rem;
        if (this.re == 0 && this.im != 0) {
            rem = "";
        } else  {
            rem = Double.toString(this.re);
        }

        String im;
        if (this.im == 0) {
            im = "";
        } else if (this.im > 0 && this.re != 0) {
            im = "+" + this.im + "i";
        } else {
            im = this.im + "i";
        }
        return rem + im;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() == o.getClass()) return false;
        if (o.getClass() != ComplexNumberImpl.class) return false;
        ComplexNumberImpl that = (ComplexNumberImpl) o;
        return Double.compare(that.getRe(), re) == 0 &&
                Double.compare(that.getIm(), im) == 0;
    }

    @Override
    public int hashCode() {
        return Objects.hash(re, im);
    }

    @Override
    public int compareTo(ComplexNumber other) {
        double x = Math.sqrt(Math.pow(this.re,2) + Math.pow(this.im, 2));
        double y = Math.sqrt(Math.pow(other.getRe(),2) + Math.pow(other.getIm(),2));
        return Double.compare(x, y);
    }

    @Override
    public void sort(ComplexNumber[] array) {
        Arrays.sort(array);
    }

    @Override
    public ComplexNumber negate() {
        this.re *= -1;
        this.im *= -1;
        return this;
    }

    @Override
    public ComplexNumber add(ComplexNumber arg2) {
        this.re += arg2.getRe();
        this.im += arg2.getIm();
        return this;
    }

    @Override
    public ComplexNumber multiply(ComplexNumber arg2) {
        double newRe = this.re * arg2.getRe() - this.im * arg2.getIm();
        double newIm = this.re * arg2.getIm() + this.im * arg2.getRe();
        this.re = newRe;
        this.im = newIm;
        return this;
    }
}
