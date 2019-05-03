package com.NgocDinhHong.RMatrix;

import com.sun.glass.ui.Size;

import java.text.DecimalFormat;
import java.text.NumberFormat;

public class Matrix {
    private Double[][] data = null;

    public Matrix(Double[][] data) {
        checkMatrix(data);
        this.data = data;
    }

    public void show() {
        System.out.println();
        for (Double[] row : this.data) {
            for (Double i : row) {
                if (Double.compare(i, 999.0) == 1) {
                    NumberFormat numberFormat = new DecimalFormat("0.######E0");
                    System.out.print(String.format("%-15s", numberFormat.format(i)));
                } else {
                    System.out.print(String.format("%-15f", i));
                }
            }
            System.out.println();
        }
    }

    public Size size() {
        Size size = new Size();
        size.height = this.data.length;
        size.width = this.data[0].length;
        return size;
    }

    public Matrix T() {
        Size size = this.size();
        Double[][] temp = new Double[size.width][size.height];

        for (int i = 0; i < size.height; i++) {
            for (int j = 0; j < size.width; j++) {
                temp[j][i] = this.data[i][j];
            }
        }
        this.data = temp;
        return this;
    }

    public Double getValue(int row, int col) {
        Size size = this.size();
        if (row < 0 || row > size.height - 1 || col < 0 || col > size.width - 1)
            throw new IndexOutOfBoundsException("Invalid index");
        return this.data[row][col];
    }

    public Matrix plus(Matrix mtx) {

        Size s1 = this.size();
        Size s2 = mtx.size();

        if (s1.height != s2.height || s1.width != s2.width)
            throw new RMatixException("Only 2 matrices of the same size can be added");

        for (int i = 0; i < s1.height; i++) {
            for (int j = 0; j < s1.width; j++) {
                this.data[i][j] += mtx.getValue(i, j);
            }
        }

        return this;
    }

    public Matrix mult(Double num) {
        Size size = size();
        for (int i = 0; i < size.height; i++) {
            for (int j = 0; j < size.width; j++) {
                this.data[i][j] *= num;
            }
        }
        return this;
    }

    public Matrix mult(Matrix mtx) {
        Size s1 = this.size();
        Size s2 = mtx.size();

        if (s1.width != s2.height)
            throw new RMatixException("It is only possible to multiply 2 matrices when and only if the number of columns of matrix A is equal to the number of lines of matrix B");

        Double[][] result = new Double[s1.height][s2.width];


        /**
         * Làm tới đây hàm này chưa xong còn.*/

        return this;
    }

    public static boolean compare(Matrix mtx1, Matrix mtx2) {
        Size s1 = mtx1.size();
        Size s2 = mtx2.size();

        if (s1.height != s2.height || s1.width != s2.width) return false;

        for (int i = 0; i < s1.height; i++) {
            for (int j = 0; j < s1.width; j++) {
                if (Double.compare(mtx1.getValue(i, j), mtx2.getValue(i, j)) != 0) {
                    return false;
                }
            }
        }
        return true;
    }

    public static Matrix plus(Matrix mtx1, Matrix mtx2) {

        Size s1 = mtx1.size();
        Size s2 = mtx2.size();

        if (s1.height != s2.height || s1.width != s2.width)
            throw new RMatixException("Only 2 matrices of the same size can be added");

        Double[][] result = new Double[s1.height][s1.width];
        for (int i = 0; i < s1.height; i++) {
            for (int j = 0; j < s1.width; j++) {
                result[i][j] = mtx1.getValue(i, j) + mtx2.getValue(i, j);
            }
        }
        return new Matrix(result);
    }

    public void setValue(int row, int col, Double val) {
        Size size = this.size();
        if (row < 0 || row > size.height - 1 || col < 0 || col > size.width - 1)
            throw new IndexOutOfBoundsException("Invalid index");
        this.data[row][col] = val;
    }

    public static Matrix mult(Matrix mtx, Double num) {
        Size size = mtx.size();
        Double[][] result = new Double[size.height][size.width];
        for (int i = 0; i < size.height; i++) {
            for (int j = 0; j < size.width; j++) {
                result[i][j] = mtx.getValue(i, j) * num;
            }
        }
        return new Matrix(result);
    }

    private void checkMatrix(Double[][] data) {
        // Check null data;
        if (data == null) throw new NullPointerException("Null matrix");

        // Check kind of matrix is m x n
        int temp = data[0].length;
        for (Double[] row : data) {
            if (row.length != temp) throw new RMatixException("Only support matrix type m x n");
        }
    }


}
