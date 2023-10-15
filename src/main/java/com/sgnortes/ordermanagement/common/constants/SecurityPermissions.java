package com.sgnortes.ordermanagement.common.constants;

public class SecurityPermissions {

    public static final String ADMIN_PERMISSION = "hasAuthority('ROLE_ADMIN')";
    public static final String READ_PERMISSION = "hasAuthority('ROLE_USER') OR hasAuthority('ROLE_ADMIN')";

}
