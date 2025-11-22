package ua.hudyma.dto;

import ua.hudyma.enums.ChannelType;

import java.util.List;

public record FineRespDto(
        String fineCode,
        String licensePlateNumber,
        List<String> driverNameList,
        String ownerName,
        String cameraAddress,
        String location,
        String registeredOn,
        String postanovaNumber,
        String channelType
) {}
