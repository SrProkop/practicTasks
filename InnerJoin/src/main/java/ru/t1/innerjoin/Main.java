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
    private static final String PATH_FREE = "mapMerging.txt";

    public static void main(String[] args) {
        IParser parserTxt = new ParserTxt();
        int countArgs = args.length;
        if (countArgs >= 2) {
            Optional<List<PairKeyValue>> listOne = parserTxt.readFileAndParsing(args[0]);
            Optional<List<PairKeyValue>> listTwo = parserTxt.readFileAndParsing(args[1]);
            if (listOne.isPresent() && listTwo.isPresent()) {
                switch (countArgs) {
                    case 2:
                        createFileMerging(PATH_ONE, PATH_TWO, PATH_FREE,
                                listOne.get(), listTwo.get());
                        break;
                    case 3:
                        createFileMerging(args[2], PATH_TWO, PATH_FREE,
                                listOne.get(), listTwo.get());
                        break;
                    case 4:
                        createFileMerging(args[2], args[3], PATH_FREE,
                                listOne.get(), listTwo.get());
                        break;
                    case 5:
                        createFileMerging(args[2], args[3], args[4],
                                listOne.get(), listTwo.get());
                        break;
                    default:
                        System.out.println("Ошибка ввода аргументов: " +
                                "Аргументов встретилось больше, чем ожидалось");
                        break;

                }
            }  else {
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
        HashMap<Integer, List<PairKeyValue>> hashMapOne = ConverterListToMap.convert(listOne);
        HashMap<Integer, List<PairKeyValue>> hashMapTwo = ConverterListToMap.convert(linkedListTwo);
        mapMerging.createFileMergers(hashMapOne, hashMapTwo, pathThree);

    }
}