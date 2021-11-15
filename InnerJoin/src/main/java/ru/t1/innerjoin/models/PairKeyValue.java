package ru.t1.innerjoin.models;

public class PairKeyValue {
    private int id;
    private String value;

    public PairKeyValue() {
    }


    public PairKeyValue(int id, String value) {
        this.id = id;
        this.value = value;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    @Override
    public String toString() {
        return "Models{" +
                "id=" + id +
                ", value=" + value +
                '}';
    }
}
