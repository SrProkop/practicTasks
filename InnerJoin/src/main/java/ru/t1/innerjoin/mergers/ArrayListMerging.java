package ru.t1.innerjoin.mergers;

import ru.t1.innerjoin.models.Model;

import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class ArrayListMerging implements IMerging{
    @Override
    public void createFileMergers(List<List<Model>> lists, String path) {
        ArrayList<Model> listOne = new ArrayList<>(lists.get(0));
        ArrayList<Model> listTwo = new ArrayList<>(lists.get(1));
        try (FileWriter writer = new FileWriter(path, false)){
            for (Model modelOne : listOne) {
                for (Model modelTwo : listTwo) {
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
