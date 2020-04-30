package org.example;

import static org.junit.Assert.assertTrue;
import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.*;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.junit.jupiter.params.provider.ValueSource;
import org.apache.commons.io.IOUtils;
import java.io.IOException;
import java.io.InputStream;
import java.nio.charset.StandardCharsets;


/**
 * Unit test for simple App.
 */
public class AppTest
{


    @Test
    public void TestAdditionalMatrices(){
        int[][] matrixFirstExample = {{4,5},{6,7}};
        int[][] matrixSecondExample = {{1,2},{3,4}};
        int[][] matrixResult = {{5,7},{9,11}};
        assertArrayEquals(matrixResult,App.additionMatrix(matrixFirstExample,matrixSecondExample));
    }


    @Test
    public void TestNotEqualSizeMultiplyMatrices(){
        int[][] matrixA = {{4,5},{3,2},{6,7}};
        int[][] matrixB = {{1,2},{3,4},{5,6}};
        assertThrows(ArrayIndexOutOfBoundsException.class,()->{App.multiplyMatrix(matrixA,matrixB);});
    }

    @Test
    public void TestNotEqualSizeAdditionMatrices(){
        int[][] matrixA = {{4,5},{3,2},{6,7},{4,7}};
        int[][] matrixB = {{1,2},{3,4},{5,6}};
        assertThrows(ArrayIndexOutOfBoundsException.class,()->{App.additionMatrix(matrixA,matrixB);});
    }

    @Test
    public void TestSubtractionMatrices(){
        int[][] matrixFirstExample = {{4,5},{6,7}};
        int[][] matrixSecondExample = {{1,2},{3,4}};
        int[][] matrixResult = {{3,3},{3,3}};
        assertArrayEquals(matrixResult,App.subMatrix(matrixFirstExample,matrixSecondExample));
    }

    @Test
    public void TestMultiplyMatrices(){
        int[][] matrixA = {{4,5,7},{-2,-4,8},{6,7,0}};
        int[][] matrixB = {{1,2,3},{-4,5,0},{-3,4,6}};
        int[][] matrixResult = {{-37,61,54},{-10,8,42},{-22,47,18}};
        assertArrayEquals(matrixResult,App.multiplyMatrix(matrixA,matrixB));
    }

    @Test
    public void TestTranspose(){
        int[][] matrixA = {{4,5},{6,7}};
        int[][] matrixResult = {{4,6},{5,7}};
        assertArrayEquals(matrixResult,App.transpose(matrixA));
    }

    @Test
    public void TestMultiplyMatrixByValue(){
        int[][] matrixA = {{4,5},{6,7}};
        int value = 3;
        int[][] matrixResult = {{12,15},{18,21}};
        assertArrayEquals(matrixResult,App.multiplyMatrixByValue(matrixA,3));
    }

    @Test
    void TestFileMultiplyMatrices() {
        int[][] matrixA = null;
        int[][] matrixB = null;
        int[][] expMatrix = null;
        try (InputStream inputStream = getClass().getResourceAsStream("/matrixA.txt")) {
            String result = IOUtils.toString(inputStream, StandardCharsets.UTF_8);
            matrixA = App.stringToMatrix(result);
        } catch (IOException e) {
            e.printStackTrace();
        }

        try (InputStream inputStream = getClass().getResourceAsStream("/matrixB.txt")) {
            String result = IOUtils.toString(inputStream, StandardCharsets.UTF_8);
            matrixB = App.stringToMatrix(result);
        } catch (IOException e) {
            e.printStackTrace();
        }

        try (InputStream inputStream = getClass().getResourceAsStream("/expMatrix.txt")) {
            String result = IOUtils.toString(inputStream, StandardCharsets.UTF_8);
            expMatrix = App.stringToMatrix(result);
        } catch (IOException e) {
            e.printStackTrace();
        }
        assertArrayEquals(expMatrix,App.multiplyMatrix(matrixA,matrixB));
    }

    @Test
    void TestFileAdditionMatrices() {
        int[][] matrixA = null;
        int[][] matrixB = null;
        int[][] expMatrix = null;
        try (InputStream inputStream = getClass().getResourceAsStream("/matrixA.txt")) {
            String result = IOUtils.toString(inputStream, StandardCharsets.UTF_8);
            matrixA = App.stringToMatrix(result);
        } catch (IOException e) {
            e.printStackTrace();
        }

        try (InputStream inputStream = getClass().getResourceAsStream("/matrixB.txt")) {
            String result = IOUtils.toString(inputStream, StandardCharsets.UTF_8);
            matrixB = App.stringToMatrix(result);
        } catch (IOException e) {
            e.printStackTrace();
        }

        try (InputStream inputStream = getClass().getResourceAsStream("/expMatrixSum.txt")) {
            String result = IOUtils.toString(inputStream, StandardCharsets.UTF_8);
            expMatrix = App.stringToMatrix(result);
        } catch (IOException e) {
            e.printStackTrace();
        }
        assertArrayEquals(expMatrix,App.additionMatrix(matrixA,matrixB));
    }

    @ParameterizedTest
    @CsvSource({"1,2,3,4,10,20,30,40",
        "0,0,0,1,0,0,0,10",
        "-5,-3,-4,-2,-50,-30,-40,-20",
        "0,-2,3,0,0,-20,30,0"})
    void TestMultiplyByValueParam(String str1,String str2,String str3,String str4,
                              String str5,String str6,String str7,String str8) {

        int [][] matrixA = {{Integer.parseInt(str1),Integer.parseInt(str2)},
            {Integer.parseInt(str3),Integer.parseInt(str4)}};

        int [][] expMatrix = {{Integer.parseInt(str5),Integer.parseInt(str6)},
            {Integer.parseInt(str7),Integer.parseInt(str8)}};

        int value = 10;

        assertArrayEquals(expMatrix,App.multiplyMatrixByValue(matrixA,value));
    }
}
