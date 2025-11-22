package ua.hudyma.dto;

import ua.hudyma.enums.ChannelType;

import java.util.List;
import java.util.Set;

public record CameraRespDto(
        String cameraCode,
        Location location,
        String address,
        Set<ChannelType> channelTypeSet,
        List<String> fineList
) {
}
