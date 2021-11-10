package ru.t1.innerjoin.parsers;

import ru.t1.innerjoin.models.Model;

import java.util.List;
import java.util.Optional;

public interface IParser {
    public Optional<List<List<Model>>> parser(String pathFileOne, String pathFileTwo);
}
