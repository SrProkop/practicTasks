package ru.t1.innerjoin.mergers;

import ru.t1.innerjoin.models.PairKeyValue;
import ru.t1.innerjoin.service.PairWriter;

import java.io.FileWriter;
import java.io.IOException;
import java.util.*;

public class MapMerging implements IMerging<HashMap<Integer, List<PairKeyValue>>> {
    @Override
    public void createFileMergers(HashMap<Integer, List<PairKeyValue>> mapOne, HashMap<Integer, List<PairKeyValue>> mapTwo, String path) {
        try (FileWriter writer = new FileWriter(path, false)) {
            for (Integer key : mapOne.keySet()) {
                if (mapOne.containsKey(key) && mapTwo.containsKey(key)) {
                    List<PairKeyValue> listPairOne = mapOne.get(key);
                    List<PairKeyValue> listPairTwo = mapTwo.get(key);
                    for (PairKeyValue pairOne : listPairOne) {
                        for (PairKeyValue pairTwo : listPairTwo) {
                            PairWriter.write(key, pairOne.getValue(), pairTwo.getValue(), writer);
                        }
                    }
                }
            }
        } catch(IOException e){
            System.out.println("Ошибка записи файла");
        }
    }
}
