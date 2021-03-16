package com.easyrecycling.web.entity.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum Permission {
    USER_READ("user:read"),
    CORP_READ("corp:read"),
    ADMIN_READ("admin:read"),
    USER_WRITE("user:write"),
    CORP_WRITE("corp:write"),
    ADMIN_WRITE("admin:write");

    private final String permission;
}
