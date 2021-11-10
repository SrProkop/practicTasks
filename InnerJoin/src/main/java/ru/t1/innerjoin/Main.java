package ru.t1.innerjoin;

import ru.t1.innerjoin.mergers.IMerging;
import ru.t1.innerjoin.mergers.ArrayListMerging;
import ru.t1.innerjoin.mergers.LinkedListMerging;
import ru.t1.innerjoin.mergers.MapMerging;
import ru.t1.innerjoin.models.Model;
import ru.t1.innerjoin.parsers.IParser;
import ru.t1.innerjoin.parsers.ParserTxt;

import java.util.List;
import java.util.Optional;

/* Первый и второй аргументы - исходные файлы для чтения
*  С третьего по пятый аргументы - выходные файлы для записи по каждой коллекции */

public class Main {

    public static void main(String[] args) {
        IParser parserTxt = new ParserTxt();
        if (args.length == 5) {
            Optional<List<List<Model>>> lists = parserTxt.parser(args[0],
                    args[1]);
            if (lists.isPresent()) {
                IMerging merging = new ArrayListMerging();
                merging.createFileMergers(lists.get(), args[2]);
                merging = new LinkedListMerging();
                merging.createFileMergers(lists.get(), args[3]);
                merging = new MapMerging();
                merging.createFileMergers(lists.get(), args[4]);
            } else {
                System.out.println("Ошибка ввода аргументов");
            }
        }

    }
}
