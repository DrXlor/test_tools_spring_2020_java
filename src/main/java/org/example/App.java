package org.example;

import java.io.FileReader;
import java.io.IOException;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public final class App {
    App() {
    }
            public static int[] readmatrix() throws FileNotFoundException {
                Scanner s = new Scanner(new File("./src/main/java/resources/msize"));
                int[] array = new int[8];
                for (int i = 0; i < array.length; i++)
                    array[i] = s.nextInt();
                return array;
        }

    static boolean palindrome(String arr)
    {
        char[] chars = arr.toCharArray();
        int length = chars.length;
        int flag = 0;
        for (int i = 0; i <= length / 2 && length != 0; i++) {
            if ( chars[i] != chars[length - i - 1]) {
                flag = 1;
                break;
            }
        }
        if (flag == 1)
           return true;
        else
            return false;
    }

    public static double[][] transpose(double[][] matrix) {
        double[][] transpose = new double[matrix[0].length][matrix.length];

        for (int i = 0; i < matrix.length; i++)
            for (int j = 0; j < matrix[i].length; j++)
                transpose[j][i] = matrix[i][j];
        return transpose;
    }

    public static double determinant(double[][] matrix) {
        if (matrix.length != matrix[0].length)
            throw new IllegalStateException("invalid dimensions");

        if (matrix.length == 2)
            return matrix[0][0] * matrix[1][1] - matrix[0][1] * matrix[1][0];

        double det = 0;
        for (int i = 0; i < matrix[0].length; i++)
            det += Math.pow(-1, i) * matrix[0][i]
                * determinant(minor(matrix, 0, i));
        return det;
    }

    public static double[][] minor(double[][] matrix, int row, int column) {
        double[][] minor = new double[matrix.length - 1][matrix.length - 1];

        for (int i = 0; i < matrix.length; i++)
            for (int j = 0; i != row && j < matrix[i].length; j++)
                if (j != column)
                    minor[i < row ? i : i - 1][j < column ? j : j - 1] = matrix[i][j];
        return minor;
    }

    public static double[][] sum(double[][] firstMatrix, double[][] secondMatrix) {
        double[][] result = new double[firstMatrix.length][secondMatrix[0].length];
        for (int row = 0; row < result.length; row++) {
            for (int col = 0; col < result[row].length; col++) {
                for (int i = 0; i < secondMatrix.length; i++) {
                    result[row][col] = firstMatrix[row][i] + secondMatrix[i][col];
                }
            }
        }
        return result;
    }

    public static double[][] division(double[][] firstMatrix, double[][] secondMatrix) {
        double[][] result = new double[firstMatrix.length][secondMatrix[0].length];
        for (int row = 0; row < result.length; row++) {
            for (int col = 0; col < result[row].length; col++) {
                for (int i = 0; i < secondMatrix.length; i++) {
                    result[row][col] += firstMatrix[row][i] * Math.pow(secondMatrix[i][col],-1);
                }
            }
        }
        return result;
    }

    public static double[][] subtraction(double[][] firstMatrix, double[][] secondMatrix) {
        double[][] result = new double[firstMatrix.length][secondMatrix[0].length];
        for (int row = 0; row < result.length; row++) {
            for (int col = 0; col < result[row].length; col++) {
                for (int i = 0; i < secondMatrix.length; i++) {
                    result[row][col] = firstMatrix[row][i] - secondMatrix[i][col];
                }
            }
        }
        return result;
    }

    public static int comparison(double[][] firstMatrix, double[][] secondMatrix) {
        int result = 0;
        for (int row = 0; row < firstMatrix.length; row++) {
            for (int col = 0; col < firstMatrix[row].length; col++) {
                if(firstMatrix[row][col] != secondMatrix[row][col]) {
                    result =-1;
                }
            }
        }
        return result;
    }

    public static double[][] multiply(double[][] firstMatrix, double[][] secondMatrix) {
        double[][] result = new double[firstMatrix.length][secondMatrix[0].length];
        for (int row = 0; row < result.length; row++) {
            for (int col = 0; col < result[row].length; col++) {
                result[row][col] += multiplyMatricesCell(firstMatrix, secondMatrix, row, col);
            }
        }
        return result;
    }
    private static double multiplyMatricesCell(double[][] firstMatrix, double[][] secondMatrix, int row, int col) {
        double cell = 0;
        for (int i = 0; i < secondMatrix.length; i++) {
            cell = firstMatrix[row][i] * secondMatrix[i][col];
        }
        return cell;
    }

    public static void print(double[][] matrix) {
        int rows=matrix.length;
        int cols=matrix[0].length;
        for(int i=0;i<rows;i++) {
            for(int j=0;j<cols;j++)
                System.out.print(matrix[i][j]+" ");
            System.out.println();
        }
    }
}
