package ru.skillbench.tasks.basics.control;

public class ControlFlowStatements1Impl implements ControlFlowStatements1 {
    @Override
    public float getFunctionValue(float x) {
        return x > 0 ? (float) (2 * Math.sin(x)) : 6 - x;
    }

    @Override
    public String decodeWeekday(int weekday) {
        String day;
        switch (weekday) {
            case 1: day = "Monday"; break;
            case 2: day = "Tuesday"; break;
            case 3: day = "Wednesday"; break;
            case 4: day = "Thursday"; break;
            case 5: day = "Friday"; break;
            case 6: day = "Saturday"; break;
            default: day = "Sunday";
        }
        return day;
    }

    @Override
    public int[][] initArray() {
        int[][] arr = new int[8][5];
        for (int i = 0; i < 8; i++) {
            for (int j = 0; j < 5; j++) {
                arr[i][j] = i*j;
            }
        }
        return arr;
    }

    @Override
    public int getMinValue(int[][] array) {
        int min = array[0][0];
        for (int[] i: array) {
            for (int j: i) {
                min = Math.min(min, j);
            }
        }
        return min;
    }

    @Override
    public BankDeposit calculateBankDeposit(double P) {
        BankDeposit bd = new BankDeposit();
        bd.amount = 1000;
        bd.years = 0;
        do {
            bd.amount *= 1.0 + P/100;
            bd.years++;
        } while (bd.amount <= 5000);
        return bd;
    }
}
