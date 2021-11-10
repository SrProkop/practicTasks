package ru.t1.innerjoin.models;

public class Model {
    private int id;
    private char value;

    public Model() {
    }


    public Model(int id, char value) {
        this.id = id;
        this.value = value;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public char getValue() {
        return value;
    }

    public void setValue(char value) {
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
