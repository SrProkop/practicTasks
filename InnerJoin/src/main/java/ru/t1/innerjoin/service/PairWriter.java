package ru.t1.innerjoin.service;

import java.io.IOException;
import java.io.FileWriter;

public class PairWriter {

    public static void write(Integer id,
                           String valueOne,
                           String valueTwo,
                           FileWriter writer) {
        try {
            writer.write(id +
                    "\t" +
                    valueOne +
                    "\t" +
                    valueTwo +
                    "\n");
        } catch (IOException e) {
            System.out.println("Ошибка записи файла");
        }
    }

}
