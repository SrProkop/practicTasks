package ru.t1.innerjoin.parsers;

import ru.t1.innerjoin.models.PairKeyValue;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class ParserTxt implements IParser {

    @Override
    public Optional<List<PairKeyValue>> readFileAndParsing(String path) {
        List<PairKeyValue> list = new ArrayList<>();
        try(FileReader fileReader = new FileReader(path);
            BufferedReader reader = new BufferedReader(fileReader)) {
            System.out.println("Читаем файл: " + path);
            String line = reader.readLine();
            int numberLine = 1;
            while (line != null) {
                String[] lineSplit = line.split("\t", -1);
                if (isValidLine(lineSplit, numberLine)) {
                    PairKeyValue pairKeyValue = new PairKeyValue(Integer.parseInt(lineSplit[0].trim()),
                            lineSplit[1].trim());
                    list.add(pairKeyValue);
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
            if (!isInteger(lineArray[0].trim())) {
                System.out.println("Первое поле (" + lineArray[0] + ") в " + numberLine + " строке не является числовым");
                return false;
            }
            return isValidString(lineArray[1], numberLine);
        } else {
            System.out.println("Ошибка форматирования в строке: " + numberLine);
            return false;
        }
    }

    private boolean isInteger(String line){
        try {
            Integer.parseInt(line);
            return true;
        } catch (NumberFormatException e) {
            return false;
        }
    }

    private boolean isValidString(String line, int numberLine) {
        if (line.trim().length() == 0) {
            System.out.println("Второе поле (" + line + ") в " +
                    numberLine +
                    " строке является пустым");
            return false;
        }
        if (line.trim().length() > 100) {
            System.out.println("Второе поле (" + line + ") в " +
                    numberLine +
                    " строке первышает максимальные допустимые 100 символов");
            return false;
        }
        return true;
    }
}
