package ru.t1.innerjoin.mergers;

import ru.t1.innerjoin.models.Model;

import java.util.List;

public interface IMerging {

    public void createFileMergers(List<List<Model>> lists, String path);

}
