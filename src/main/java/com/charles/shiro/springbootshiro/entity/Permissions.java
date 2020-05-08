package com.charles.shiro.springbootshiro.entity;

import lombok.Data;

/**
 * @author wangshengli
 * @date 2020/05/07
 */
@Data
public class Permissions {

    private String id;
    private String permissionsName;

    public Permissions(String id, String permissionsName) {
        this.id = id;
        this.permissionsName = permissionsName;
    }
}
