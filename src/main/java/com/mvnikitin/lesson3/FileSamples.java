package com.mvnikitin.lesson3;

import java.io.*;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Enumeration;
import java.util.List;

public class FileSamples {

    public static void main(String[] args) {

        FileSamples fs = new FileSamples();

        // Доп. задание 1.
        String fileName = "src\\com\\mvnikitin\\lesson3\\res\\50bytes.txt";
        fs.printBytesFromFile(fileName);

        //Доп. задание 2.
        fs.linkFiles("src\\com\\mvnikitin\\lesson3\\res\\res.txt",
                "src\\com\\mvnikitin\\lesson3\\res\\src1.txt",
                "src\\com\\mvnikitin\\lesson3\\res\\src2.txt",
                "src\\com\\mvnikitin\\lesson3\\res\\src3.txt",
                "src\\com\\mvnikitin\\lesson3\\res\\src4.txt",
                "src\\com\\mvnikitin\\lesson3\\res\\src5.txt");
    }

    /**
     * Дополнительное задание 1:
     * Прочитать файл (около 50 байт) в байтовый массив и
     * вывести этот массив в консоль.
     */
    public void printBytesFromFile(String fileName) {

        int fileSize = (int)new File(fileName).length();
        byte[] fileData = new byte[fileSize];

        try(FileInputStream in = new FileInputStream(fileName)) {
            int count = -1;
            while ((count = in.read(fileData)) > 0) {
                for(int i = 0; i < count; i++) {
                    System.out.print((char)fileData[i]);
                }
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * Дополнительное задание 2:
     * Последовательно сшить 5 файлов в один (файлы примерно 100 байт).
     */
    public void linkFiles(String resFileName, String... srcFileNames) {

        List<InputStream> sourceFiles = new ArrayList<>();
        SequenceInputStream in = null;

        try (FileOutputStream resFile = new FileOutputStream( resFileName )){

            for (String s: srcFileNames) {
                sourceFiles.add(new FileInputStream(s));
            }

            Enumeration<InputStream> enumeration = Collections.enumeration(sourceFiles);
            in = new SequenceInputStream(enumeration);

            int x = -1;
            while ((x = in.read()) != - 1 ) {
                resFile.write(x);
            }

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if(in != null) {
                try {
                    in.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }
}
