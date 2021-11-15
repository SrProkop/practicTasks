package ru.t1.innerjoin.parsers;

import ru.t1.innerjoin.models.PairKeyValue;

import java.util.List;
import java.util.Optional;

public interface IParser {
    public Optional<List<PairKeyValue>> readFileAndParsing(String pathFileOne);
}
