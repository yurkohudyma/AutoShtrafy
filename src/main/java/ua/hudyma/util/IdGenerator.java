package ua.hudyma.util;

import ua.hudyma.enums.RegionalCodes;

import java.security.SecureRandom;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static ua.hudyma.enums.RegionalCodes.getAllCodesList;

public class IdGenerator {

    private final static List<String> gsmCodesList = List.of("67", "68", "50", "96", "98", "95", "99");
    private final static List<String> ukrLatinLetters = List.of("A","B","C","E","H","I","K","M","O","P","T","X");

    /*private final static List<String> licensePlateCodesList = List.of(
            "AA","AB","AC","AE","AH","AI","AK","AM","AO","AP","AT",
            "BA","BB","BC","BE","BH","BI","BK","BM","BO","BP", "BT",
            "CA","CB","CE","CH", "DI", "KA","KB","KC","KE","KH","KI",
            "KK","KM","KT");*/

    private final static List<String> regionalCodesList = getAllCodesList();

    private final static SecureRandom secureRandom = new SecureRandom();

    public static Integer generateCityCode() {
        return generateRandomDigits();
    }

    public static String generateLicensePlate() {
        return regionalCodesList.get(secureRandom
                .nextInt(regionalCodesList.size()))
                + "-" + generateId(0,4) + "-"
                + generateRandomUppercaseLetters(ukrLatinLetters, 2);
    }

    private static String generateRandomUppercaseLetters(List<String> localePatternList, int length) {
        return Stream.generate(
                () -> localePatternList
                        .get(secureRandom.nextInt(localePatternList.size())))
                .limit(length)
                .collect(Collectors.joining());
    }

    public static String generateLinkedIdUserCode() {
        return generateRandomDigits(5)
                + generateRandomLowercaseLetters(1)
                + generateRandomDigits(1)
                + generateRandomLowercaseLetters(1)
                + generateRandomDigits(1);
    }

    public static String generateLinkedProfileUrl(String fullName, String userCode) {
        if (fullName == null || fullName.isEmpty()){
            throw new IllegalArgumentException("Fullname is empty or NULL, cannot generate profile url");
        }
        if (userCode == null || userCode.isEmpty()){
            throw new IllegalArgumentException("UserCode is empty or NULL, cannot generate profile url");
        }
        var fullnameArray = fullName.split("\\s");
        fullnameArray[1] = fullnameArray.length > 1 ? fullnameArray[1] : "";
        return "https://www.linkedin.com/in/"
                + fullnameArray[0] + "-"
                + fullnameArray[1] + "-"
                + userCode;
    }

    public static Integer generateRandomDigits() {
        return secureRandom.nextInt(100);
    }

    public static LocalDate generateIssuedOn() {
        var today = LocalDate.now();
        int daysBack = new SecureRandom().nextInt(365 * 10);
        return today.minusDays(daysBack);
    }

    public static String generatePhoneNumber() {
        return "+380" + getRandomGSMCode() + generateRandomDigits(7);
    }

    public static String generateTtn() {
        return "2045" + generateRandomDigits(10);
    }

    private static String getRandomGSMCode() {
        return gsmCodesList.get(secureRandom.nextInt(gsmCodesList.size()));
    }

    public static String generateProductCode(String catName) {
        return catName
                .substring(0,2)
                .toUpperCase() +
                generateRandomDigits(10);
    }

    public static LocalTime generateRandomTime() {
        var random = new SecureRandom();
        int secondsInDay = 24 * 60 * 60;
        int randomSecondOfDay = random.nextInt(secondsInDay);
        return LocalTime.ofSecondOfDay(randomSecondOfDay);
    }

    public static String generateId(int letterLength, int numberLength) {
        String letters = generateRandomUppercaseLetters(letterLength);
        String numbers = generateRandomDigits(numberLength);
        return letters + numbers;
    }

    public static String generateRandomUppercaseLetters(int length) {
        return secureRandom
                .ints('A',
                        'Z' + 1)
                .limit(length)
                .collect(StringBuilder::new,
                        StringBuilder::appendCodePoint,
                        StringBuilder::append)
                .toString();
    }

    public static String generateRandomLowercaseLetters(int length) {
        return secureRandom
                .ints('a',
                        'z' + 1)
                .limit(length)
                .collect(StringBuilder::new,
                        StringBuilder::appendCodePoint,
                        StringBuilder::append)
                .toString();
    }

    public static String generateRandomDigits(int length) {
        return secureRandom
                .ints('0',
                        '9' + 1)
                .limit(length)
                .collect(
                        StringBuilder::new,
                        StringBuilder::appendCodePoint,
                        StringBuilder::append)
                .toString();
    }

    public static String generateVendorCode() {
        return "VE" + generateRandomDigits(10);
    }

    public static String generateBuyerCode() {
        return "BU" + generateRandomDigits(10);
    }

    public static <T extends Enum<T>> T getRandomEnum(Class<T> enumClass) {
        T[] values = enumClass.getEnumConstants();
        int index = secureRandom.nextInt(values.length);
        return values[index];
    }
}