package ua.hudyma.dto;

import java.util.List;

public record CarRespDto(
        String licensePlate,
        List<DriverRespDto> driverList,
        List<FineRespDto> fineList
) {
}
