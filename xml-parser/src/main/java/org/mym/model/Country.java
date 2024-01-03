package org.mym.model;

import jakarta.xml.bind.annotation.*;
import lombok.*;
import org.springframework.stereotype.Component;

@Component
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Builder
@ToString
@XmlRootElement(name = "country")
@XmlAccessorType(value = XmlAccessType.FIELD)
@XmlType(propOrder = {"ruName", "ruFullName", "enName", "alpha2", "alpha3", "iso", "worldLocation", "locationPrecise", "dialingCode"})
public class Country {
    @XmlElement(name = "name")
    private String ruName;
    @XmlElement(name = "fullname")
    private String ruFullName;
    @XmlElement(name = "english")
    private String enName;
    @XmlElement(name = "alpha2")
    private String alpha2;
    @XmlElement(name = "alpha3")
    private String alpha3;
    @XmlElement(name = "iso")
    private String iso;
    @XmlElement(name = "location")
    private String worldLocation;
    @XmlElement(name = "location-precise")
    private String locationPrecise;
    @XmlElement(name = "dialing-code", defaultValue = "+0")
    private String dialingCode;
}
