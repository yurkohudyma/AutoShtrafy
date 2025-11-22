package ua.hudyma.dto;

import ua.hudyma.enums.ChannelType;

public record FineReqDto(
        String licensePlateNumber,
        String cameraCode,
        ChannelType channelType
) {
}
