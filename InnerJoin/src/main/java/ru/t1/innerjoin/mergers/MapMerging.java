package ru.t1.innerjoin.mergers;

import ru.t1.innerjoin.models.PairKeyValue;
import ru.t1.innerjoin.service.PairWriter;

import java.io.FileWriter;
import java.io.IOException;
import java.util.*;

public class MapMerging implements IMerging<HashMap<Integer, List<String>>> {
    @Override
    public void createFileMergers(HashMap<Integer, List<String>> mapOne,
                                  HashMap<Integer, List<String>> mapTwo,
                                  String path) {
        try (FileWriter writer = new FileWriter(path, false)) {
            for (Integer key : mapOne.keySet()) {
                if (mapTwo.containsKey(key)) {
                    List<String> listValueOne = mapOne.get(key);
                    List<String> listValueTwo = mapTwo.get(key);
                    for (String valueOne : listValueOne) {
                        for (String valueTwo : listValueTwo) {
                            PairWriter.write(key, valueOne, valueTwo, writer);
                        }
                    }
                }
            }
        } catch(IOException e){
            System.out.println("Ошибка записи файла");
        }
    }
}