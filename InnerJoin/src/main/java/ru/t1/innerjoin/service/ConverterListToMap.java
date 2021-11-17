package ru.t1.innerjoin.service;

import ru.t1.innerjoin.models.PairKeyValue;

import java.util.*;
import java.util.stream.Collectors;

public class ConverterListToMap {

    public static HashMap<Integer, List<String>> convert(List<PairKeyValue> pairKeyValueList) {
        Map<Integer, List<String>> modelMap = pairKeyValueList.stream().
                collect(Collectors.groupingBy(PairKeyValue::getId, Collectors.mapping(PairKeyValue::getValue, Collectors.toList())));

        return new HashMap<>(modelMap);
    }
}
