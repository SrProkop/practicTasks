package ru.t1.innerjoin.service;

import ru.t1.innerjoin.models.PairKeyValue;

import java.util.*;
import java.util.stream.Collectors;

public class ConverterListToMap {

    public static HashMap<Integer, List<PairKeyValue>> convert(List<PairKeyValue> pairKeyValueList) {
        Map<Integer, List<PairKeyValue>> modelMap = pairKeyValueList.stream().
                collect(Collectors.groupingBy(PairKeyValue::getId));
        return new HashMap<>(modelMap);
    }
}
