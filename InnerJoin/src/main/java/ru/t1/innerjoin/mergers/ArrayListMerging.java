package ru.t1.innerjoin.mergers;

import ru.t1.innerjoin.models.PairKeyValue;
import ru.t1.innerjoin.service.PairWriter;

import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

public class ArrayListMerging implements IMerging<ArrayList<PairKeyValue>>{
    @Override
    public void createFileMergers(ArrayList<PairKeyValue> listOne, ArrayList<PairKeyValue> listTwo, String path) {
        try (FileWriter writer = new FileWriter(path, false)){
            listOne.stream().
                    forEach(pairOne -> listTwo.stream().
                            forEach(pairTwo -> writeFile(pairOne, pairTwo, writer)));
        } catch (IOException e) {
            System.out.println("Ошибка записи файла");
        }
    }

    private void writeFile(PairKeyValue pairOne, PairKeyValue pairTwo, FileWriter writer) {
        if (pairOne.getId() == pairTwo.getId()) {
            PairWriter.write(pairOne.getId(),
                    pairOne.getValue(),
                    pairTwo.getValue(),
                    writer);
        }
    }
}
