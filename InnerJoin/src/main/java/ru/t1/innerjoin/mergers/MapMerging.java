package ru.t1.innerjoin.mergers;

import ru.t1.innerjoin.models.Model;

import java.io.FileWriter;
import java.io.IOException;
import java.lang.reflect.Array;
import java.util.*;

public class MapMerging implements IMerging{
    @Override
    public void createFileMergers(List<List<Model>> lists, String path) {
        Map<Integer, List<Character>> mapModelOne = converterListToMap(lists.get(0));
        Map<Integer, List<Character>> mapModelTwo = converterListToMap(lists.get(1));
        try (FileWriter writer = new FileWriter(path, false)) {
            for (Integer key : mapModelOne.keySet()) {
                List<Character> listValueOne = mapModelOne.get(key);
                List<Character> listValueTwo = mapModelTwo.get(key);
                if (listValueTwo != null && listValueOne != null) {
                    System.out.println(listValueOne.size());
                    System.out.println(listValueTwo.size());
                    writeFile(key, listValueOne, listValueTwo, writer);
                }
            }
        } catch (IOException e) {
            System.out.println("Ошибка записи файла");
        }

    }

    private Map<Integer, List<Character>> converterListToMap(List<Model> modelList) {
        Map<Integer, List<Character>> modelMap = new HashMap<>();
        for (Model model : modelList) {
            if (modelMap.containsKey(model.getId())) {
                modelMap.get(model.getId()).add(model.getValue());
            } else {
                ArrayList<Character> list = new ArrayList<>();
                list.add(model.getValue());
                modelMap.put(model.getId(), list);
            }
        }
        for (Map.Entry<Integer, List<Character>> entry : modelMap.entrySet()) {
            System.out.println(entry.getKey());
            System.out.println(entry.getValue().toString());
            System.out.println("\n");
        }
        return modelMap;
    }

    private void writeFile(Integer id,
                           List<Character> valueListOne,
                           List<Character> valueListTwo,
                           FileWriter writer) {
        try {
            for (Character charOne : valueListOne) {
                for (Character charTwo: valueListTwo) {
                    writer.write(id +
                            "\t" +
                            charOne +
                            "\t" +
                            charTwo +
                            "\n");
                }
            }
        } catch (IOException e) {
            System.out.println("Ошибка записи файла");
        }
    }
}
