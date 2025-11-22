package ua.hudyma.dto;

import java.util.List;

public record DriverRespDto(
        String driverName,
        String phoneNumber,
        String driverCode,
        List<String> carLicensePlatesList,
        List<String> finePostanovaList
) {
}
