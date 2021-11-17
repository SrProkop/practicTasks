package ru.t1.innerjoin;

import ru.t1.innerjoin.mergers.ArrayListMerging;
import ru.t1.innerjoin.mergers.LinkedListMerging;
import ru.t1.innerjoin.mergers.MapMerging;
import ru.t1.innerjoin.mergers.MergingProxy;
import ru.t1.innerjoin.models.PairKeyValue;
import ru.t1.innerjoin.parsers.IParser;
import ru.t1.innerjoin.parsers.ParserTxt;
import ru.t1.innerjoin.service.ConverterListToMap;

import java.util.*;

/* Первый и второй аргументы - исходные файлы для чтения
*  С третьего по пятый аргументы - выходные файлы для записи по каждой коллекции */

public class Main {

    private static final String PATH_ONE = "arrayListMerging.txt";
    private static final String PATH_TWO = "linkedListMerging.txt";
    private static final String PATH_THREE = "mapMerging.txt";

    public static void main(String[] args) {
        IParser parserTxt = new ParserTxt();
        int countArgs = args.length;
        if (countArgs >= 2) {
            Optional<List<PairKeyValue>> listOne = parserTxt.readFileAndParsing(args[0]);
            if (listOne.isPresent()) {
                Optional<List<PairKeyValue>> listTwo = parserTxt.readFileAndParsing(args[1]);
                if (listTwo.isPresent()) {
                    createFileMerging(countArgs >= 3 ? args[2] : PATH_ONE,
                            countArgs >= 4 ? args[3] : PATH_TWO,
                            countArgs >= 5 ? args[4] : PATH_THREE,
                            listOne.get(),
                            listTwo.get());
                }
            } else {
                System.out.println("Ошибка чтения списков. " +
                        "Один или несколько списков не были прочитаны");
            }
        } else {
            System.out.println("Ошибка ввода аргументов");
        }
    }

    private static void createFileMerging(String pathOne,
                                             String pathTwo,
                                             String pathThree,
                                             List<PairKeyValue> listOne,
                                             List<PairKeyValue> listTwo) {
        MergingProxy arrayListMerging = new MergingProxy(new ArrayListMerging());
        ArrayList<PairKeyValue> arrayListOne = new ArrayList<>(listOne);
        ArrayList<PairKeyValue> arrayListTwo = new ArrayList<>(listTwo);
        arrayListMerging.createFileMergers(arrayListOne, arrayListTwo, pathOne);

        MergingProxy linkedListMerging = new MergingProxy(new LinkedListMerging());
        LinkedList<PairKeyValue> linkedListOne = new LinkedList<>(listOne);
        linkedListOne.sort(Comparator.comparing(PairKeyValue::getId));
        LinkedList<PairKeyValue> linkedListTwo = new LinkedList<>(listTwo);
        linkedListTwo.sort(Comparator.comparing(PairKeyValue::getId));
        linkedListMerging.createFileMergers(linkedListOne, linkedListTwo, pathTwo);

        MergingProxy mapMerging = new MergingProxy(new MapMerging());
        HashMap<Integer, List<String>> hashMapOne = ConverterListToMap.convert(listOne);
        HashMap<Integer, List<String>> hashMapTwo = ConverterListToMap.convert(listTwo);
        mapMerging.createFileMergers(hashMapOne, hashMapTwo, pathThree);

    }
}