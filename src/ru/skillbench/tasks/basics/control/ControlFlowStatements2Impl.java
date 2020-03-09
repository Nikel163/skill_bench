package ru.skillbench.tasks.basics.control;

public class ControlFlowStatements2Impl implements ControlFlowStatements2{
    @Override
    public int getFunctionValue(int x) {
        return (x < -2 || x > 2) ? 2 * x : -3 * x;
    }

    @Override
    public String decodeMark(int mark) {
        String res;
        switch (mark) {
            case 1: res = "Fail"; break;
            case 2: res = "Poor"; break;
            case 3: res = "Satisfactory"; break;
            case 4: res = "Good"; break;
            case 5: res = "Excellent"; break;
            default: res = "Error";
        }
        return res;
    }

    @Override
    public double[][] initArray() {
        double[][] arr = new double[5][8];
        for (int i = 0; i < 5; i++) {
            for (int j = 0; j < 8; j++) {
                arr[i][j] = Math.pow(i,4) - Math.sqrt(j);
            }
        }
        return arr;
    }

    @Override
    public double getMaxValue(double[][] array) {
        double max = array[0][0];
        for (double[] i: array) {
            for (double j: i) {
                max = Math.max(max, j);
            }
        }
        return max;
    }

    @Override
    public Sportsman calculateSportsman(float P) {
        Sportsman sport = new Sportsman();
        float dist = 10;
        sport.addDay(dist);
        do {
            dist *= 1.0 + P/100;
            sport.addDay(dist);
        } while (sport.getTotalDistance() <= 200);
        return sport;
    }
}
