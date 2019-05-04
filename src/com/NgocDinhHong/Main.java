package com.NgocDinhHong;

import com.NgocDinhHong.RMatrix.Matrix;

import java.util.Random;

public class Main {

    public static void main(String[] args) {

        Matrix matrix = new Matrix(new Double[][]{
//                {1.0, 1.0, 1.0, 1.0, 1.0},
//                {2.0, 1.0, 1.0, 1.0, 1.0},
//                {3.0, 1.0, 1.0, 1.0, 1.0},
//                {4.0, 1.0, 1.0, 1.0, 1.0},
//                {5.0, 1.0, 1.0, 1.0, 1.0}

//                {1.0, 2.0, 2.0, 3.0, 1.0, -1.0},
//                {1.0, 2.0, 2.0, 1.0, -2.0, 2.0},
//                {1.0, 2.0, 3.0, 2.0, 1.0, -1.0},
//                {2.0, 4.0, 1.0, 3.0, -7.0, 7.0}

                {1.0, 2.0, 3.0},
                {1.0, 3.0, 2.0},
                {2.0, 4.0, 6.0}
        });

        Random random = new Random();
        random.nextDouble();
        Matrix matrix2 = new Matrix(new Double[][]{
//                {1.0, 1.0, 1.0, 1.0, 1.0},
//                {2.0, 1.0, 1.0, 1.0, 1.0},
//                {3.0, 1.0, 1.0, 1.0, 1.0},
//                {4.0, 1.0, 1.0, 1.0, 1.0},
//                {5.0, 1.0, 1.0, 1.0, 1.0}

                {random.nextDouble(), random.nextDouble(), random.nextDouble(), random.nextDouble(), random.nextDouble()},
                {random.nextDouble(), random.nextDouble(), random.nextDouble(), random.nextDouble(), random.nextDouble()},
                {random.nextDouble(), random.nextDouble(), random.nextDouble(), random.nextDouble(), random.nextDouble()},
                {random.nextDouble(), random.nextDouble(), random.nextDouble(), random.nextDouble(), random.nextDouble()},
                {random.nextDouble(), random.nextDouble(), random.nextDouble(), random.nextDouble(), random.nextDouble()},
        });

        matrix2.show();
        System.out.println("_______________________________");
        matrix2.gauss().show();
        System.out.println("========================== END ==========================");
    }
}
