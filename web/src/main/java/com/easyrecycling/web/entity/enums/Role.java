package com.easyrecycling.web.entity.enums;

import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public enum Role {
    SINGLE_USER("user", Stream.of(Permission.USER_READ, Permission.USER_WRITE).collect(Collectors.toSet())),
    CORP("corp", Stream.of(Permission.CORP_READ, Permission.CORP_WRITE).collect(Collectors.toSet())),
    ADMIN("admin", Stream.of(Permission.ADMIN_READ, Permission.ADMIN_WRITE).collect(Collectors.toSet()));

    private String roleName;
    private final Set<Permission> permissions;

    Role(String roleName, Set<Permission> permissions) {
        this.roleName = roleName;
        this.permissions = permissions;
    }

    public String getRoleName() {
        return roleName;
    }

    public Set<Permission> getPermissions() {
        return permissions;
    }
}
