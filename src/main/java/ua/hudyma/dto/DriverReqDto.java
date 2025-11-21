package ua.hudyma.dto;

import java.util.List;

public record DriverReqDto(
        String driverName,
        List<String> ownedCarsLicensePlatesList
) {
}
