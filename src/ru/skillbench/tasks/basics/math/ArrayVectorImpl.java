package ru.skillbench.tasks.basics.math;

import java.util.Arrays;

public class ArrayVectorImpl implements ArrayVector {

    public double[] array;

    public ArrayVectorImpl() {
        this.array = new double[5];
    }

    public ArrayVectorImpl(double... elements) {
        set(elements);
    }

    @Override
    public void set(double... elements) {
        this.array = elements;
    }

    @Override
    public double[] get() {
        return this.array;
    }

    @Override
    public ArrayVector clone() {
        ArrayVectorImpl vector = new ArrayVectorImpl();
        System.arraycopy(this.array, 0, vector.array, 0, this.array.length);
        return vector;
    }

    @Override
    public int getSize() {
        return this.array.length;
    }

    @Override
    public void set(int index, double value) {
        if (index < 0) {
            return;
        }
        double[] temp = this.array;
        int len = Math.min(index+1, temp.length);
        this.array = new double[index+1];
        for (int i = 0; i < len; i++) {
            this.array[i] = temp[i];
        }
        this.array[index] = value;
    }

    @Override
    public double get(int index) throws ArrayIndexOutOfBoundsException {
        if (index < 0 || index > this.array.length - 1) {
            throw new ArrayIndexOutOfBoundsException();
        }

        return this.array[index];
    }

    @Override
    public double getMax() {
        double max = this.array[0];
        for (double x : this.array) {
            max = Math.max(max, x);
        }
        return max;
    }

    @Override
    public double getMin() {
        double min = this.array[0];
        for (double x : this.array) {
            min = Math.min(min, x);
        }
        return min;
    }

    @Override
    public void sortAscending() {
        Arrays.sort(this.array);
    }

    @Override
    public void mult(double factor) {
        for (int i = 0; i < this.array.length; i++) {
            this.array[i] *= factor;
        }
    }

    @Override
    public ArrayVector sum(ArrayVector anotherVector) {
        int min = Math.min(this.array.length, anotherVector.getSize());
        for (int i = 0; i < min; i++) {
            this.array[i] += anotherVector.get(i);
        }
        return this;
    }

    @Override
    public double scalarMult(ArrayVector anotherVector) {
        double scalar = 0;
        int min = Math.min(this.array.length, anotherVector.getSize());
        for (int i = 0; i < min; i++) {
            scalar += this.array[i] * anotherVector.get(i);
        }
        return scalar;
    }

    @Override
    public double getNorm() {
        return Math.sqrt(scalarMult(this));
    }
}
