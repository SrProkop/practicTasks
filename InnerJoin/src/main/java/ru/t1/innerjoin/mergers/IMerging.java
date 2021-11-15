package ru.t1.innerjoin.mergers;


public interface IMerging <T>{

    public void createFileMergers(T listOne, T listTwo, String path);

}