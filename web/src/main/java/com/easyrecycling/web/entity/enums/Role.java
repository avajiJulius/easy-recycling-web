package com.easyrecycling.web.entity.enums;

import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public enum Role {
    SINGLE_USER(Stream.of(Permission.USER_READ, Permission.USER_WRITE).collect(Collectors.toSet())),
    CORP(Stream.of(Permission.CORP_READ, Permission.CORP_WRITE).collect(Collectors.toSet())),
    ADMIN(Stream.of(Permission.ADMIN_READ, Permission.ADMIN_WRITE).collect(Collectors.toSet()));

    private final Set<Permission> permissions;

    Role(Set<Permission> permissions) {
        this.permissions = permissions;
    }

    public Set<Permission> getPermissions() {
        return permissions;
    }
}
