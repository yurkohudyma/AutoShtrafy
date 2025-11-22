package ua.hudyma.enums;

import lombok.Data;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Getter
public enum ChannelType {
    SPEED_LIMIT ("Швидкість"),
    TRAFFIC_LIGHTS ("Світлофор"),
    LINES_CONTROL ("Розмітка");
    private final String value;
}
