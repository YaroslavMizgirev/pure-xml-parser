package org.mym.services;

import jakarta.xml.bind.JAXBException;
import org.junit.jupiter.api.Test;
import org.mym.model.Countries;
import org.mym.model.Country;

import java.net.MalformedURLException;
import java.net.URISyntaxException;
import java.util.ArrayList;
import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.*;

class XmlHandlerTest {
    @Test
    void testMarshall() throws JAXBException {
        XmlHandler xmlHandler = new XmlHandler();
        Country country = Country.builder()
                .ruName("Азербайджан")
                .ruFullName("Республика Азербайджан")
                .enName("Azerbaijan")
                .alpha2("AZ")
                .alpha3("AZE")
                .iso("031")
                .worldLocation("Азия")
                .locationPrecise("Западная Азия")
                .dialingCode("+994")
                .build();
        xmlHandler.marshall(country);
    }

    @Test
    void testFullMarshall() throws JAXBException {
        XmlHandler xmlHandler = new XmlHandler();
        Countries countries = new Countries(new ArrayList<>());

        Country country = Country.builder()
                .ruName("Азербайджан")
                .ruFullName("Республика Азербайджан")
                .enName("Azerbaijan")
                .alpha2("AZ")
                .alpha3("AZE")
                .iso("031")
                .worldLocation("Азия")
                .locationPrecise("Западная Азия")
                .dialingCode("+994")
                .build();
        countries.getCountries().add(country);

        country = Country.builder()
                .ruName("Австралия")
                .ruFullName("Австралия")
                .enName("Australia")
                .alpha2("AU")
                .alpha3("AUS")
                .iso("036")
                .worldLocation("Океания")
                .locationPrecise("Австралия и Новая Зеландия")
                .dialingCode("+61")
                .build();
        countries.getCountries().add(country);

        country = Country.builder()
                .ruName("Абхазия")
                .ruFullName("Республика Абхазия")
                .enName("Abkhazia")
                .alpha2("AB")
                .alpha3("ABH")
                .iso("895")
                .worldLocation("Азия")
                .locationPrecise("Закавказье")
                .dialingCode("+840")
                .build();
        countries.getCountries().add(country);

        xmlHandler.marshall(countries);
    }

    @Test
    void testUnmarshall() throws URISyntaxException, MalformedURLException, JAXBException {
        var xmlURI = ClassLoader.getSystemResource("countries.xml").toURI();

        XmlHandler xmlHandler = new XmlHandler();
        var countries = xmlHandler.unmarshall(xmlURI.toString());
        Stream.of(countries.getCountries()).forEach(System.out::println);
    }
}