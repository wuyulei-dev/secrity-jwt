package com.secrity.entity;

import java.io.Serializable;
import java.time.LocalDateTime;
import com.baomidou.mybatisplus.annotation.TableId;

//权限表
public class RoleInfo implements Serializable {

    private static final long serialVersionUID = 1L;

    @TableId
    private String id;

    private String roleName;

    private String roleCode;

    private String roleRemark;

    private Integer activeStatus;

    private LocalDateTime createTime;

    public String getId() {
        return id;
    }

    public void setId(
        String id) {
        this.id = id;
    }

    public String getRoleName() {
        return roleName;
    }

    public void setRoleName(
        String roleName) {
        this.roleName = roleName;
    }

    public String getRoleCode() {
        return roleCode;
    }

    public void setRoleCode(
        String roleCode) {
        this.roleCode = roleCode;
    }

    public String getRoleRemark() {
        return roleRemark;
    }

    public void setRoleRemark(
        String roleRemark) {
        this.roleRemark = roleRemark;
    }

    public Integer getActiveStatus() {
        return activeStatus;
    }

    public void setActiveStatus(
        Integer activeStatus) {
        this.activeStatus = activeStatus;
    }

    public LocalDateTime getCreateTime() {
        return createTime;
    }

    public void setCreateTime(
        LocalDateTime createTime) {
        this.createTime = createTime;
    }
    
}