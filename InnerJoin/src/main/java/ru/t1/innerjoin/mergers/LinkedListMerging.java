package ru.t1.innerjoin.mergers;

import ru.t1.innerjoin.models.PairKeyValue;
import ru.t1.innerjoin.service.PairWriter;

import java.io.FileWriter;
import java.io.IOException;
import java.util.*;

public class LinkedListMerging implements IMerging <LinkedList<PairKeyValue>> {
    @Override
    public void createFileMergers(LinkedList<PairKeyValue> listOne, LinkedList<PairKeyValue> listTwo, String path) {
        try (FileWriter writer = new FileWriter(path, false)){
            for(int i = 0, j = 0; i < listOne.size() || j < listTwo.size();) {
                PairKeyValue pairOne = listOne.get(i);
                PairKeyValue pairTwo = listTwo.get(j);
                int idOne = pairOne.getId();
                int idTwo = pairTwo.getId();
                if (idOne == idTwo) {
                    int nextIndexTwo = j;
                    int nextIdTwo = idTwo;
                    while (idOne == nextIdTwo) {
                        PairWriter.write(idOne, pairOne.getValue(), pairTwo.getValue(), writer);
                        nextIndexTwo++;
                        if (listTwo.size() > nextIndexTwo) {
                            nextIdTwo = listTwo.get(nextIndexTwo).getId();
                            pairTwo = listTwo.get(nextIndexTwo);
                        } else {
                            break;
                        }
                    }
                }
                if (idOne > idTwo) {
                    j++;
                    if (j == listTwo.size()) {
                        break;
                    }
                } else {
                    i++;
                    if (i == listOne.size()) {
                        break;
                    }
                }
            }
        } catch (IOException e) {
            System.out.println("Ошибка записи файла");
        }
    }
}