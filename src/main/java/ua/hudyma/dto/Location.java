package ua.hudyma.dto;

import java.math.BigDecimal;

public record Location(
        BigDecimal lat,
        BigDecimal lon
) {
}
