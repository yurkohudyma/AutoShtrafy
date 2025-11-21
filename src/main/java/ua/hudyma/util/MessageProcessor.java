package ua.hudyma.util;

import lombok.extern.log4j.Log4j2;

@Log4j2
public class MessageProcessor {
    public static <T> String getReturnMessage(T t,
                                              String fieldName)
            throws Exception {
        var getterName = "get" + Character.toUpperCase(fieldName.charAt(0))
                + fieldName.substring(1);
        var getFieldMethod = t.getClass().getMethod(getterName);
        var fieldValue = getFieldMethod.invoke(t);
        var msg = String.format("%s [%s] додано до бази",
                t.getClass().getSimpleName(),
                fieldValue);
        log.info(msg);
        return msg;
    }

}
