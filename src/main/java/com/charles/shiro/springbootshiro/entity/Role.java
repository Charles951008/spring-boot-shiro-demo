package com.charles.shiro.springbootshiro.entity;

import lombok.Data;

import java.util.Set;

/**
 * @author wangshengli
 * @date 2020/05/07
 */
@Data
public class Role {
    private String id;
    private String roleName;
    /**
     * 角色对应权限集合
     */
    private Set<Permissions> permissions;

    public Role(String id,String roleName,Set<Permissions> permissions){
        this.id=id;
        this.roleName=roleName;
        this.permissions=permissions;
    }
}
