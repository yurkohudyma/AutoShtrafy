package ua.hudyma.dto;

import ua.hudyma.enums.ChannelType;

public record FineReqDto(
        String licensePlateNumber,
        String driverCode,
        String cameraCode,
        ChannelType channelType,
        String postanovaNumber
) {
}
