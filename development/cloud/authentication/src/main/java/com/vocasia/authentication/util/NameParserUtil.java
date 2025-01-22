package com.vocasia.authentication.util;

import org.springframework.stereotype.Component;

import java.util.Arrays;

@Component
public class NameParserUtil {

    public String getFirstName(String name) {
        return name.split(" ")[0];
    }

    public String getLastName(String name) {
        return getLastName(name, "");
    }

    public String getLastName(String name, String defaultIfEmpty) {
        String[] nameParts = name.split(" ");

        if (nameParts.length < 2) {
            return defaultIfEmpty;
        }

        return String.join(" ", Arrays.copyOfRange(nameParts, 1, nameParts.length));
    }
}


