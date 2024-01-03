package org.mym.services;

import org.jetbrains.annotations.NotNull;

public interface XmlValidate {
    /**
     * @param xsdPath {@link String}
     * @param xmlPath {@link String}
     * @return If xml validate against xsd is correct, then returning {@code true}, else returning {@code false}.
     */
    boolean isValid(@NotNull String xsdPath, @NotNull String xmlPath);
}
