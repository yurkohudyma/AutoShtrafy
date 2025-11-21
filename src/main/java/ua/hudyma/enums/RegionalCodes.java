package ua.hudyma.enums;

import java.util.EnumSet;
import java.util.List;

public enum RegionalCodes {

    AA("м. Київ"),
    AB("Вінницька область"),
    AC("Волинська область"),
    AE("Дніпропетровська область"),
    AH("Донецька область"),
    AI("Київська область"),
    AK("Автономна Республіка Крим"),
    AM("Житомирська область"),
    AO("Закарпатська область"),
    AP("Запорізька область"),
    AT("Івано-Франківська область"),
    AX("Харківська область"),

    BA("Кіровоградська область"),
    BB("Луганська область"),
    BC("Львівська область"),
    BE("Миколаївська область"),
    BH("Одеська область"),
    BI("Полтавська область"),
    BK("Рівненська область"),
    BM("Сумська область"),
    BO("Тернопільська область"),
    BT("Херсонська область"),
    BX("Хмельницька область"),

    CA("Черкаська область"),
    CB("Чернігівська область"),
    CE("Чернівецька область"),
    CH("м. Севастополь"),
    II("Загальнодержавні номери");

    private final String regionName;

    RegionalCodes(String regionName) {
        this.regionName = regionName;
    }

    public String getRegionName() {
        return regionName;
    }

    public static List<String> getAllCodesList (){
        return EnumSet
                .allOf(RegionalCodes.class)
                .stream()
                .map(RegionalCodes::name)
                .toList();
    }

    public static RegionalCodes fromCode(String code) {
        for (RegionalCodes rc : values()) {
            if (rc.name().equals(code)) {
                return rc;
            }
        }
        throw new IllegalArgumentException("Unknown region code: " + code);
    }
}

