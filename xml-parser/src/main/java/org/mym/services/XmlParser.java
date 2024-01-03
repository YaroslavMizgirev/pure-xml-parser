package org.mym.services;

import org.jetbrains.annotations.NotNull;

import java.util.List;

public interface XmlParser<T> {
    List<T> parse(@NotNull String xmlPath);
}
