package ua.hudyma.dto;

import ua.hudyma.enums.ChannelType;

import java.util.Set;

public record CameraReqDto(
        Location location,
        String address,
        Set<ChannelType> channelTypeSet
) {
}
