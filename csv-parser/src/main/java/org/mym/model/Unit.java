package org.mym.model;

import lombok.Builder;
import lombok.Data;
import org.springframework.stereotype.Component;

@Data
@Component
@Builder
public class Unit {
    final int id;
    final String name;
    final String symbol;
}
