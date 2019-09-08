package com.mvnikitin.lesson3;

import java.io.*;
import java.util.Arrays;
import java.util.Scanner;

public class TxtToConsoleReader {

    public static final int PAGE_SIZE = 1800;

    public static void main(String[] args) {

        TxtToConsoleReader txtReader = new TxtToConsoleReader();
        Scanner scanner = new Scanner(System.in);
        boolean isExit = false;

        printInfo();

        while(scanner.hasNextLine()) {

            String userInput = scanner.nextLine();
            String[] commands = userInput.split(" ", 2);

            switch (commands[0]) {
                case "/exit":
                    isExit = true;
                    break;
                case "/f":
                    long beginTimeStamp = System.currentTimeMillis();
                    txtReader.readFile(commands[1]);
                    System.out.println("Time spent: " +
                            (System.currentTimeMillis() - beginTimeStamp) +
                            "ms.");
                    break;
                default:
                    System.out.println("Неверная команда.");
            }

            if (isExit)
                break;
        }
    }

    private void readFile(String fileName) {
        try(BufferedReader reader = new BufferedReader(new FileReader(fileName))) {
            char[] pageOfData = new char[PAGE_SIZE];
            char initValue = 0;
            int iteration = 0;

            while (reader.read(pageOfData, 0, pageOfData.length) > 0) {
                System.out.println(pageOfData);
                Arrays.fill(pageOfData, initValue);
                iteration++;
            }

            System.out.println("Total iterations: " + iteration);

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static void printInfo() {
        System.out.println(
                "********************************************************************************\n" +
                "* Команды для работы с программой постраничного чтения текстовых файлов:       *\n" +
                "* /f <имя_файла> - чтение файла (имя с расширением)                            *\n" +
                "* /exit - выход из программы.                                                  *\n" +
                "********************************************************************************\n");
    }
}

/////////////////////////////////////////////////////////////////////////////////////////////////////
//
// Результаты:
// Размер файла 13.1 Мб
// Total iterations: 7649
// Time spent: 221ms.