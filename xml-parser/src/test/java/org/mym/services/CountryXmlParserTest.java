package org.mym.services;

import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.net.URISyntaxException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;

import static org.assertj.core.api.Assertions.assertThat;

class CountryXmlParserTest {
    @Test
    void parseXmlFile() throws URISyntaxException, IOException {
        // given
        var parser = new CountryXmlParser();
        var uri = ClassLoader.getSystemResource("countries.xml").toURI();
        var countriesXml = Files.readString(Paths.get(uri), StandardCharsets.UTF_8);

        // when
        var countries = parser.parse(countriesXml);

        // then
        System.out.println(countries);
//        assertThat(rates.size()).isEqualTo(43);
//        assertThat(rates.contains(getUsdRate())).isTrue();
//        assertThat(rates.contains(getEurRate())).isTrue();
//        assertThat(rates.contains(getJpyRate())).isTrue();
    }
}