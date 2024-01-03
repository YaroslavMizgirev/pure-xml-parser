package org.mym.services;

import jakarta.xml.bind.JAXBContext;
import jakarta.xml.bind.JAXBException;
import jakarta.xml.bind.Marshaller;
import lombok.extern.slf4j.Slf4j;
import org.jetbrains.annotations.NotNull;
import org.mym.model.Countries;
import org.mym.model.Country;
import org.springframework.stereotype.Service;

import java.io.File;
import java.net.MalformedURLException;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.URL;
import java.util.Objects;

@Service
@Slf4j
public class XmlHandler {
    /**
     * @param location {@link String}
     * @return The new {@link URI}
     *
     * @throws NullPointerException
     * If {@code location} is {@code null}
     *
     * @throws RuntimeException
     * If the given string violates <a href="https://www.ietf.org/rfc/rfc2396.txt">RFC 2396</a>
     *
     */
    public URI getURI(@NotNull String location) {
        try {
            return new URI(Objects.requireNonNull(location));
        } catch (URISyntaxException e) {
            log.error(e.getMessage());
            throw new RuntimeException(e);
        }
    }

    /**
     * @param country {@link Country}
     *
     * @throws JAXBException
     */
    public void marshall(Country country) throws JAXBException {
        JAXBContext context = JAXBContext.newInstance(Country.class);
        Marshaller marshaller = context.createMarshaller();
        marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, Boolean.TRUE);
        marshaller.marshal(country, new File("./country1.xml"));
    }

    /**
     * @param countries {@link Countries}
     *
     * @throws JAXBException
     */
    public void marshall(Countries countries) throws JAXBException {
        JAXBContext context = JAXBContext.newInstance(Countries.class);
        Marshaller marshaller = context.createMarshaller();
        marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, Boolean.TRUE);
        marshaller.marshal(countries, new File("./country2.xml"));
    }

    /**
     * @param location {@link String}
     * @return {@link Countries}
     *
     * @throws JAXBException
     * If an error was encountered while creating the JAXBContext, such as (but not limited to):
     * <ol>
     *     <li>No Jakarta XML Binding implementation was discovered</li>
     *     <li>Classes use Jakarta XML Binding annotations incorrectly</li>
     *     <li>Classes have colliding annotations (i.e., two classes with the same type name)</li>
     *     <li>The Jakarta XML Binding implementation was unable to locate provider-specific out-of-band information (such as additional files generated at the development time.)</li>
     *     <li>classesToBeBound are not open to jakarta.xml.bind module</li>
     * </ol>
     *
     * @throws MalformedURLException
     * If a protocol handler for the URL could not be found, or if some other error occurred while constructing the URL
     */
    public Countries unmarshall(String location) throws JAXBException, MalformedURLException {
        JAXBContext context = JAXBContext.newInstance(Countries.class);
        return (Countries) context.createUnmarshaller().unmarshal(getURI(location).toURL());
    }
}
