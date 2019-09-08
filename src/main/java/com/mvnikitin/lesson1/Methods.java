package com.mvnikitin.lesson1;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;

public class Methods {

    public static void main(String[] args) {

        String[] stringArray = {"Привет мир!",
                "Hello world!",
                "Hola el mundo!",
                "Hallo die Welt!",
                "Больше я не знаю :("};

        System.out.println("Задание 1.");
        System.out.println("Исходный массив: " + Arrays.toString(stringArray));
        try {
            Methods.exchangeArrayElements(stringArray, 2, 4);
            System.out.println("Поменяли местами [2] и [4]: " +
                    Arrays.toString(stringArray));
        } catch (ArrayIndexOutOfBoundsException e) {
            e.printStackTrace();
        } catch (NullPointerException e) {
            e.printStackTrace();
        }

        System.out.println();

        System.out.println("Задание 2.");
        Integer[] sourceArray = {1, 1, 2, 3, 5, 8, 13, 21, 34, 55};
        System.out.println("Исходный массив: " + Arrays.toString(sourceArray));
        System.out.println("Массив, преобразованный в ArrayList: " +
                convertToArrayList(sourceArray).toString());
    }

    /**
     * Задание 1: метод, который меняет два элемента массива местами
     * (массив может быть любого ссылочного типа).
     * @param array - массив на обмен значений.
     * @param indexFirst - иднекс первого элемента для обмена значений.
     * @param indexSecond - иднекс второго элемента для обмена значений.
     * @param <T> - тип элементов массива.
     * @throws ArrayIndexOutOfBoundsException - некорректное значение индекса.
     * @throws NullPointerException - передали null вместо массива.
     */
    public static <T> void exchangeArrayElements(
            T[] array, int indexFirst, int indexSecond)
    throws ArrayIndexOutOfBoundsException,
            NullPointerException{

        T swap = array[indexFirst];
        array[indexFirst] = array[indexSecond];
        array[indexSecond] = swap;
    }

    /**
     * Задание 2: метод, который преобразует массив в ArrayList.
     * @param array - исходный массив.
     * @param <T> - тип элементов массива.
     * @return - ArrayList<T>, заполненный элементами массива @array.
     */
    public static <T>ArrayList convertToArrayList(T[] array) {
        ArrayList<T> arrayList= new ArrayList<>();
        Collections.addAll(arrayList, array);
        return arrayList;
    };
}
