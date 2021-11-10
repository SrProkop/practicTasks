package ru.t1.innerjoin.mergers;

import ru.t1.innerjoin.models.Model;

import java.io.FileWriter;
import java.io.IOException;
import java.util.*;

public class LinkedListMerging implements IMerging {
    @Override
    public void createFileMergers(List<List<Model>> lists, String path) {
        List<Model> listOne = new LinkedList<>(lists.get(0));
        listOne.sort(Comparator.comparing(Model::getId));
        List<Model> listTwo = new LinkedList<>(lists.get(1));
        listTwo.sort(Comparator.comparing(Model::getId));
        try (FileWriter writer = new FileWriter(path, false)){
            for (Model modelOne : listOne) {
                for (Model modelTwo : listTwo) {
                    if (modelOne.getId() > listTwo.get(listTwo.size() - 1).getId()) {
                        break;
                    }
                    if (modelOne.getId() < modelTwo.getId()) {
                        break;
                    }
                    if (modelOne.getId() == modelTwo.getId()) {
                        writeFile(modelOne, modelTwo, writer);
                    }
                }
            }
        } catch (IOException e) {
            System.out.println("Ошибка записи файла");
        }
    }

    private void writeFile(Model modelOne, Model modelTwo, FileWriter writer) {
        try {
            writer.write(modelOne.getId() +
                    "\t" +
                    modelOne.getValue() +
                    "\t" +
                    modelTwo.getValue() +
                    "\n");
        } catch (IOException e) {
            System.out.println("Ошибка чтения файла");
        }
    }
}
