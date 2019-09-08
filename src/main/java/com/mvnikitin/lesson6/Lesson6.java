package com.mvnikitin.lesson6;

import java.util.Arrays;

public class Lesson6 {

    public int[] processArray(int[] source, int neededDigit) throws RuntimeException {

        String errorMessage = "Входной массив должен содержать хотя бы один элемент со значением 4.";

        if(source == null)
            throw new RuntimeException(errorMessage);

        int indexOfLastFound = -1;
        for (int i = 0; i < source.length; i++) {
            if (source[i] == neededDigit) {
                indexOfLastFound = i;
            }
        }

        if (indexOfLastFound == -1)
            throw new RuntimeException(errorMessage);

        return indexOfLastFound == source.length - 1 ? null :
                Arrays.copyOfRange(
                        source, indexOfLastFound + 1, source.length);
    }

    public boolean isArrayContainDigit(int[] array, int[] digits) {

        for(int i = 0; i < array.length; i++) {
            for (int j = 0; j < digits.length; j++) {
                if (array[i] == digits[j]) {
                    return true;
                }
            }
        }

        return false;
    }
}
