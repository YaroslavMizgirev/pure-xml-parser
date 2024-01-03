package org.mym.services;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.jetbrains.annotations.NotNull;
import org.springframework.stereotype.Service;
import org.xml.sax.SAXException;

import javax.xml.XMLConstants;
import javax.xml.transform.Source;
import javax.xml.transform.stream.StreamSource;
import javax.xml.validation.Schema;
import javax.xml.validation.SchemaFactory;
import javax.xml.validation.Validator;
import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.Objects;

@Service
@RequiredArgsConstructor
@Slf4j
public class XmlValidateXsd implements XmlValidate {
    private final XmlHandler xmlHandler;

    /**
     * @param xsdPath {@link String}
     * @return The new {@link Validator}
     *
     * @throws RuntimeException
     * If a SAX error occurs during parsing.
     *
     */
    private Validator initValidator(@NotNull String xsdPath) {
        try {
            var factory = SchemaFactory.newInstance(XMLConstants.W3C_XML_SCHEMA_NS_URI);
            Source schemaFile = new StreamSource(xmlHandler.getURI(xsdPath).toString());
            Schema schema = factory.newSchema(schemaFile);
            return schema.newValidator();
        } catch (SAXException e) {
            log.error(e.getMessage());
            throw new RuntimeException(e);
        }
    }

    /**
     * @param xsdPath {@link String}
     * @param xmlPath {@link String}
     * @return If xml validate against xsd is correct, then returning {@code true}, else returning {@code false}.
     */
    @Override
    public boolean isValid(@NotNull String xsdPath, @NotNull String xmlPath) {
        try {
            Validator validator = initValidator(xsdPath);
            validator.validate(new StreamSource(xmlHandler.getURI(xmlPath).toString()));
            return true;
        } catch (SAXException | IOException e) {
            log.info(xmlHandler.getURI(xmlPath) + " validate against " + xmlHandler.getURI(xsdPath) +
                    " with result " + false + ", because " + e.getMessage());
            return false;
        }
    }
}
