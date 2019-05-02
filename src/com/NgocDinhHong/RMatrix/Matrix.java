package com.NgocDinhHong.RMatrix;

public class Matrix {
    private Double[][] data = null;

    public Matrix(Double[][] data) {
        this.data = data;
    }

    public void print() {
        System.out.println();
        if (this.data != null) {
            for (Double[] row : this.data) {
                for (Double i : row) {
                    System.out.print(String.format("%-10f",i));
                }
                System.out.println();
            }
        }
    }

    public void addValue(int row, int col, int value) {
        if(this.data == null){
          throw new NullPointerException("Matrix is null");
        }
    }
}
