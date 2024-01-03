package org.mym.services;

import org.junit.jupiter.api.Test;

import java.net.URISyntaxException;

import static org.junit.jupiter.api.Assertions.*;

class XmlValidateXsdTest {
    @Test
    void isValidXmlAgainstXsd() throws URISyntaxException {
        var xmlValidateAgainstXsd = new XmlValidateXsd(new XmlHandler());
        var xmlURI = ClassLoader.getSystemResource("countries.xml").toURI();
        var xsdURI = ClassLoader.getSystemResource("countries.xsd").toURI();
        assertTrue(xmlValidateAgainstXsd.isValid(xsdURI.toString(), xmlURI.toString()));
    }

    @Test
    void isNotValidXmlAgainstXsd() throws URISyntaxException {
        var xmlValidateAgainstXsd = new XmlValidateXsd(new XmlHandler());
        var xmlURI = ClassLoader.getSystemResource("not_countries.xml").toURI();
        var xsdURI = ClassLoader.getSystemResource("countries.xsd").toURI();
        assertFalse(xmlValidateAgainstXsd.isValid(xsdURI.toString(), xmlURI.toString()));
    }
}