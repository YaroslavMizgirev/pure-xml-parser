package org.mym.services;

import java.util.List;

public interface CsvParser<T> {
    List<T> parse(String csvAsString);
}
