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

public class Main {

    private static IParser parserTxt = new ParserTxt();

    public static void main(String[] args) {
        Optional<List<List<Model>>> lists = parserTxt.parser("file\\file1.txt",
                "file\\file2.txt");
        if (lists.isPresent()) {
            IMerging merging = new ArrayListMerging();
            merging.createFileMergers(lists.get(), "arrayListMerging.txt");
            merging = new LinkedListMerging();
            merging.createFileMergers(lists.get(), "linkedListMerging.txt");
            merging = new MapMerging();
            merging.createFileMergers(lists.get(), "mapListMerging.txt");
        }

    }
}
