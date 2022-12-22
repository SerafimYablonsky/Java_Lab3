package mainPackage;

import javax.swing.table.AbstractTableModel;
import java.math.BigInteger;
import java.util.Objects;

@SuppressWarnings("serial")
public class GornerTableModel extends AbstractTableModel {
    private Double[] coefficients;
    private Double from;
    private Double to;
    private Double step;
    public GornerTableModel(Double from, Double to, Double step,
                            Double[] coefficients) {
        this.from = from;
        this.to = to;
        this.step = step;
        this.coefficients = coefficients;
    }
    public Double getFrom() {
        return from;
    }
    public Double getTo() {
        return to;
    }
    public Double getStep() {
        return step;
    }
    public int getColumnCount() {
        return 3;
    }
    public int getRowCount() {
// Вычислить количество точек между началом и концом отрезка
// исходя из шага табулирования
        return new Double(Math.ceil((to-from)/step)).intValue()+1;
    }
    public int gcd(int a, int b) {
        if (b==0) return a;
        return gcd(b,a % b);
    }
    public Object getValueAt(int row, int col) {
        double x = from + step * row;
        if (col == 0) return x;

        Double result = 0.0;
        for (Double a: coefficients) {
            result = result * x + a;
        }
        if (col == 1) {
            return result;
        }
        int wholePart1 = (int) x;
        int wholePart2 = Integer.parseInt(String.valueOf(result).split("\\.")[0]);
        return gcd(wholePart1, wholePart2) == 1;

    }
    public String getColumnName(int col) {
        switch (col) {
            case 0:
                return "Значение X";
            case 1:
                return "Значение многочлена";
            default:
                return "Разностороннее";
        }
    }
    public Class<?> getColumnClass(int col) {
        if (col == 2) return Boolean.class;
        return Double.class;
    }
}
