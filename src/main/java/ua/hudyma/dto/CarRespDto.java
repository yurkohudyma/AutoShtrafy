package ua.hudyma.dto;

import java.util.List;

public record CarRespDto(
        String licensePlate,
        List<String> driverNamesList,
        String ownerName,
        List<String> finePostanovaList
) {
}
