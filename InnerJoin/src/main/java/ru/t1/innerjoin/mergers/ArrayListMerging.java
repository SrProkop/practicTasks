package ru.t1.innerjoin.mergers;

import ru.t1.innerjoin.models.PairKeyValue;
import ru.t1.innerjoin.service.PairWriter;

import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

public class ArrayListMerging implements IMerging<ArrayList<PairKeyValue>>{
    @Override
    public void createFileMergers(ArrayList<PairKeyValue> listOne,
                                  ArrayList<PairKeyValue> listTwo,
                                  String path) {
        try (FileWriter writer = new FileWriter(path, false)){
            listOne.stream()
                    .forEach(pairOne -> listTwo.stream()
                            .filter(pairTwo -> pairOne.getId() == pairTwo.getId())
                            .forEach(pairTwo -> PairWriter.write(pairOne.getId(),
                                    pairOne.getValue(),
                                    pairTwo.getValue(),
                                    writer)));
        } catch (IOException e) {
            System.out.println("Ошибка записи файла");
        }
    }
}