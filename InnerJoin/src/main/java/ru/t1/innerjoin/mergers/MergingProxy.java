package ru.t1.innerjoin.mergers;

public class MergingProxy implements IMerging{

    private IMerging merging;

    public MergingProxy(IMerging merging) {
        this.merging = merging;
    }

    @Override
    public void createFileMergers(Object listOne, Object listTwo, String path) {
        long start = System.currentTimeMillis();
        merging.createFileMergers(listOne, listTwo, path);
        System.out.println("Время выполнения " +  merging.getClass().getSimpleName() + ": " + (System.currentTimeMillis() - start));
    }
}
