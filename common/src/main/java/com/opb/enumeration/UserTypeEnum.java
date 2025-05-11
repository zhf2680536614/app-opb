package com.opb.enumeration;

import lombok.Getter;

@Getter
public enum UserTypeEnum {

    USER("user","1"),
    MANAGE("manage","0");

    private final String key;

    private final String value;

    UserTypeEnum(String key, String value) {
        this.key = key;
        this.value = value;
    }

}
