package org.mym.config;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;

@Data
@ConfigurationProperties(prefix = "country-list")
public class XmlConfig {
    String url;
}
