package org.template.com.common.config;

import java.util.concurrent.ConcurrentHashMap;

public class ValidateCodeHandle {

	private static ConcurrentHashMap<String, String> validateCode = new ConcurrentHashMap<String, String>();

    public static ConcurrentHashMap<String, String> getCode() {
        return validateCode;
    }

    public static void save(String sessionId, String code) {
        validateCode.put(sessionId, code);
    }

    public static String getValidateCode(String sessionId) {
        Object obj = validateCode.get(sessionId);
        if (obj != null) {
            return String.valueOf(obj);
        }
        return null;
    }

    public static boolean matchCode(String sessionId, String inputCode) {
        String saveCode = getValidateCode(sessionId);
        if (saveCode.equals(inputCode)) {
            return true;
        }
        return false;
    }
}
