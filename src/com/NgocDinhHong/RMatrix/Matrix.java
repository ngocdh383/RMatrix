package com.NgocDinhHong.RMatrix;

import com.sun.glass.ui.Size;

import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.util.zip.DeflaterOutputStream;

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
        Size s = new Size();
        s.height = this.data.length;
        s.width = this.data[0].length;
        return s;
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

        for (int i = 0; i < result.length; i++) {
            for (int j = 0; j < result[i].length; j++) {
                result[i][j] = 0.0;
                for (int x = 0; x < s1.width; x++) {
                    result[i][j] += this.data[i][x] * mtx.data[x][j];
                }
            }
        }

        this.data = result;

        return this;
    }

    public Matrix gauss() {
        Size s = this.size();
        int i = 0, j = 0;
        while (j < s.width && i < s.height) {
            int k = i;

            while (this.data[k][j] == 0) {
                if (k + 1 >= s.height) break;
                k++;
            }

            if (this.data[k][j] != 0) {
                if (k != i) {
                    //System.out.println(String.format("d%d <-> d%d",k,i));
                    this.swapRows(k, i);
                }
                // thuc hien cac buoc bien doi.

                for (int z = i + 1; z < s.height; z++) {
                    Double alpha = this.data[z][j] / this.data[i][j];
                    //System.out.println(String.format("d%d - %fd%d", z,alpha,i));
                    for (int v = 0; v < s.width; v++) {
                        this.data[z][v] = this.data[z][v] - alpha * this.data[i][v];

                    }
                }

                i++;

                // System.out.println("_________________________________________________________________________");
                // this.show();
            }
            j++;
        }
        return this;
    }

    public Matrix swapRows(int k, int i) {
        Size s = this.size();
        if (k < 0 || k > s.height - 1 || i < 0 || i > s.height - 1)
            throw new IndexOutOfBoundsException("Invalid row index");

        for (int j = 0; j < s.width; j++) {
            Double temp = this.data[k][j];
            this.data[k][j] = this.data[i][j];
            this.data[i][j] = temp;
        }
        return this;
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

    public static Matrix mult(Matrix mtx1, Matrix mtx2) {
        Size s1 = mtx1.size();
        Size s2 = mtx2.size();

        if (s1.width != s2.height)
            throw new RMatixException("It is only possible to multiply 2 matrices when and only if the number of columns of matrix A is equal to the number of lines of matrix B");

        Double[][] result = new Double[s1.height][s2.width];

        for (int i = 0; i < result.length; i++) {
            for (int j = 0; j < result[i].length; j++) {
                result[i][j] = 0.0;
                for (int x = 0; x < s1.width; x++) {
                    result[i][j] += mtx1.data[i][x] * mtx2.data[x][j];
                }
            }
        }
        return new Matrix(result);
    }


}
