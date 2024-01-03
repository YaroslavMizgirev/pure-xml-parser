package org.mym.services;

import lombok.extern.slf4j.Slf4j;
import org.jetbrains.annotations.NotNull;
import org.mym.exceptions.XmlParsingException;
import org.mym.model.Country;
import org.springframework.stereotype.Service;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.InputSource;

import javax.xml.XMLConstants;
import javax.xml.parsers.DocumentBuilderFactory;
import java.io.StringReader;
import java.util.ArrayList;
import java.util.List;

@Service
@Slf4j
public class CountryXmlParser implements XmlParser<Country> {
    @Override
    public List<Country> parse(@NotNull String xmlAsString) {
        var countries = new ArrayList<Country>();

        var dbf = DocumentBuilderFactory.newInstance();
        dbf.setAttribute(XMLConstants.ACCESS_EXTERNAL_DTD, "");
        dbf.setAttribute(XMLConstants.ACCESS_EXTERNAL_SCHEMA, "");

        try {
            dbf.setFeature(XMLConstants.FEATURE_SECURE_PROCESSING, true);
            var db = dbf.newDocumentBuilder();
            try (var reader = new StringReader(xmlAsString)) {
                Document doc = db.parse(new InputSource(reader));
                doc.getDocumentElement().normalize();

                NodeList list = doc.getElementsByTagName("country");
                for (var countryIdx = 0; countryIdx < list.getLength(); countryIdx++) {
                    var node = list.item(countryIdx);
                    if (node.getNodeType() == Node.ELEMENT_NODE) {
                        var element = (Element) node;
                        var country = Country.builder()
                                .ruName(element.getElementsByTagName("name").item(0).getTextContent())
                                .ruFullName(element.getElementsByTagName("fullname").item(0).getTextContent())
                                .enName(element.getElementsByTagName("english").item(0).getTextContent())
                                .alpha2(element.getElementsByTagName("alpha2").item(0).getTextContent())
                                .alpha3(element.getElementsByTagName("alpha3").item(0).getTextContent())
                                .iso(element.getElementsByTagName("iso").item(0).getTextContent())
                                .worldLocation(element.getElementsByTagName("location").item(0).getTextContent())
                                .locationPrecise(element.getElementsByTagName("location-precise").item(0).getTextContent())
                                .dialingCode(element.getElementsByTagName("dialing-code").item(0).getTextContent())
                                .build();
                        countries.add(country);
                    }
                }
            }
        } catch (Exception exception) {
            log.error("xml parsing error, xml:{}", xmlAsString, exception);
            throw new XmlParsingException(exception);
        }
        return countries;
    }
}
