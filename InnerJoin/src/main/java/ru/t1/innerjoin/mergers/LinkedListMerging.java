package ru.t1.innerjoin.mergers;

import ru.t1.innerjoin.models.PairKeyValue;
import ru.t1.innerjoin.service.PairWriter;

import java.io.FileWriter;
import java.io.IOException;
import java.util.*;

public class LinkedListMerging implements IMerging <LinkedList<PairKeyValue>> {
    @Override
    public void createFileMergers(LinkedList<PairKeyValue> listOne,
                                  LinkedList<PairKeyValue> listTwo,
                                  String path) {
        try (FileWriter writer = new FileWriter(path, false)){
            ListIterator<PairKeyValue> iteratorOne = listOne.listIterator();
            ListIterator<PairKeyValue> iteratorTwo = listTwo.listIterator();
            PairKeyValue pairOne = iteratorOne.next();
            PairKeyValue pairTwo = iteratorTwo.next();
            while ((iteratorOne.hasNext() || pairOne.getId() > pairTwo.getId())
                    && (iteratorTwo.hasNext() || pairOne.getId() < pairTwo.getId())) {
                if (pairOne.getId() > pairTwo.getId()) {
                    pairTwo = iteratorTwo.next();
                } else if (pairOne.getId() < pairTwo.getId()) {
                    pairOne = iteratorOne.next();
                }
                while (pairOne.getId() == pairTwo.getId()) {
                    int countIterations = 0;
                    while (pairOne.getId() == pairTwo.getId()) {
                        PairWriter.write(pairOne.getId(),
                                pairOne.getValue(),
                                pairTwo.getValue(),
                                writer);
                        countIterations++;
                        if (iteratorTwo.hasNext()) {
                            pairTwo = iteratorTwo.next();
                        } else {
                            break;
                        }
                    }
                    if (!iteratorOne.hasNext()) {
                        break;
                    }
                    PairKeyValue pairOneOld = pairOne;
                    pairOne = iteratorOne.next();
                    if (pairOneOld.getId() == pairOne.getId()) {
                        for (int i = 0; i < countIterations; i++) {
                            pairTwo = iteratorTwo.previous();
                        }
                    }
                }
            }
        } catch (IOException e) {
            System.out.println("Ошибка записи файла");
        }
    }
}