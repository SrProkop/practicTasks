package ru.t1.innerjoin.parsers;

import ru.t1.innerjoin.models.Model;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class ParserTxt implements IParser {

    @Override
    public Optional<List<List<Model>>> parser(String pathFileOne, String pathFileTwo) {
        List<List<Model>> modelsList = new ArrayList<>();
        Optional<List<Model>> listOne = readFileAndParsing(pathFileOne);
        Optional<List<Model>> listTwo = readFileAndParsing(pathFileTwo);
        if (listOne.isPresent() && listTwo.isPresent()) {
            modelsList.add(listOne.get());
            modelsList.add(listTwo.get());
            printLists(modelsList);
            return Optional.of(modelsList);
        }
        return Optional.empty();
    }

    private Optional<List<Model>> readFileAndParsing(String path) {
        List<Model> list = new ArrayList<>();
        try(FileReader fileReader = new FileReader(path);
            BufferedReader reader = new BufferedReader(fileReader)) {
            System.out.println("Читаем файл: " + path);
            String line = reader.readLine();
            int numberLine = 1;
            while (line != null) {
                String[] lineSplit = line.split("\t", -1);
                if (isValidLine(lineSplit, numberLine)) {
                    Model model = new Model(Integer.parseInt(lineSplit[0].trim()), lineSplit[1].trim().charAt(0));
                    list.add(model);
                }
                line = reader.readLine();
                numberLine++;
            }
            if (list.size() != 0) {
                return Optional.of(list);
            } else  {
                System.out.println("Файл " + path + " оказался пустым" );
                return Optional.empty();
            }
        } catch (FileNotFoundException e) {
            System.out.println("Файл " + path + " не найден");
            return Optional.empty();
        } catch (IOException e) {
            System.out.println("Ошибка чтения файла");
            return Optional.empty();
        }
    }

    private boolean isValidLine(String[] lineArray, int numberLine) {
        if (lineArray.length == 1 && lineArray[0].trim().length() == 0) {
            System.out.println("Ошибка, встретилась пустая строка под номером: " + numberLine);
            return false;
        }
        if (lineArray.length == 2) {
            if (!isNumeric(lineArray[0].trim())) {
                System.out.println("Первое поле (" + lineArray[0] + ") в " + numberLine + " строке не является числовым");
                return false;
            }
            if (!isChar(lineArray[1])) {
                System.out.println("Второе поле (" + lineArray[1] + ") в " + numberLine + " строке не является символьным");
                return false;
            }
        } else {
            System.out.println("Ошибка форматирования в строке: " + numberLine);
            return false;
        }
        return true;
    }

    private boolean isNumeric(String line){
        try {
            Integer.parseInt(line);
            return true;
        } catch (NumberFormatException e) {
            return false;
        }
    }

    private boolean isChar(String line) {
        return line.trim().length() == 1;
    }

    private void printLists(List<List<Model>> lists) {
        for (List<Model> list : lists) {
            System.out.println("Список: ");
            for (Model model : list) {
                System.out.println(model.getId() + "   " + model.getValue());
            }
            System.out.println("\n");
        }
    }
}
