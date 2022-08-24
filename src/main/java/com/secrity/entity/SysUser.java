/*
 * @(#)SysUser.java
 * Copyright (C) 2020 Neusoft Corporation All rights reserved.
 *
 * VERSION        DATE       BY              CHANGE/COMMENT
 * ----------------------------------------------------------------------------
 * @version 1.00  Jun 24, 2020 li.dg          初版
 *
 */
package com.secrity.entity;

import java.io.Serializable;
import java.util.Date;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;

/**
 * 用户信息
 * 
 * @version 1.0 2020-06-24
 */
@TableName("sys_user")
public class SysUser implements Serializable {

    /**
     * serialVersionUID
     */
    private static final long serialVersionUID = 1L;

    /**
     * 用户ID
     */

    @TableId
    private String userId;

    /**
     * 是否删除。0:正常 1:删除
     */
    private Integer delFlag;

    /**
     * 状态 0:生效 1:失效
     */
    private Integer status;

    /**
     * 用户类型 内部用户 0外部用户 1
     */
    private Integer userType;

    /**
     * 通讯地址
     */
    private String address;

    /**
     * AES密钥
     */
    private String aesCode;

    /**
     * 身份证号
     */
    private String certificateNum;

    /**
     * 人员公司名
     */
    private String companyName;

    /**
     * 创建者ID
     */
    private String createUserId;

    /**
     * 邮箱
     */
    private String email;

    /**
     * 人员类别
     */
    private String employeeCategory;

    /**
     * 工号
     */
    private String employeeNum;

    /**
     * 人员类型
     */
    private String employeeType;

    /**
     * 用户表示名
     */
    private String fullName;

    /**
     * 登录名
     */
    private String loginName;

    /**
     * 手机
     */
    private String mobile;

    /**
     * 密码
     */
    private String passwd;

    /**
     * 男、女
     */
    private String sex;

    /**
     * 办公电话
     */
    private String tel;

    /**
     * 分机号
     */
    private String telExt;

    /**
     * 更新者ID
     */
    private String updateUserId;

    /**
     * 创建时间
     */
    private Date createTime;

    /**
     * 更新时间
     */
    private Date updateTime;

    /**
     * 取得用户ID
     *
     * @return 用户ID
     */
    public String getUserId() {
        return userId;
    }

    /**
     * 设置用户ID
     *
     * @param userId 用户ID
     */
    public void setUserId(
        final String userId) {
        this.userId = userId;
    }

    /**
     * 取得是否删除。0:正常 1:删除
     *
     * @return 是否删除。0:正常 1:删除
     */
    public Integer getDelFlag() {
        return delFlag;
    }

    /**
     * 设置是否删除。0:正常 1:删除
     *
     * @param delFlag 是否删除。0:正常 1:删除
     */
    public void setDelFlag(
        final Integer delFlag) {
        this.delFlag = delFlag;
    }

    /**
     * 取得状态 0:生效 1:失效
     *
     * @return 状态 0:生效 1:失效
     */
    public Integer getStatus() {
        return status;
    }

    /**
     * 设置状态 0:生效 1:失效
     *
     * @param status 状态 0:生效 1:失效
     */
    public void setStatus(
        final Integer status) {
        this.status = status;
    }

    /**
     * 取得用户类型 内部用户 0外部用户 1
     *
     * @return 用户类型 内部用户 0外部用户 1
     */
    public Integer getUserType() {
        return userType;
    }

    /**
     * 设置用户类型 内部用户 0外部用户 1
     *
     * @param userType 用户类型 内部用户 0外部用户 1
     */
    public void setUserType(
        final Integer userType) {
        this.userType = userType;
    }

    /**
     * 取得通讯地址
     *
     * @return 通讯地址
     */
    public String getAddress() {
        return address;
    }

    /**
     * 设置通讯地址
     *
     * @param address 通讯地址
     */
    public void setAddress(
        final String address) {
        this.address = address;
    }

    /**
     * 取得AES密钥
     *
     * @return AES密钥
     */
    public String getAesCode() {
        return aesCode;
    }

    /**
     * 设置AES密钥
     *
     * @param aesCode AES密钥
     */
    public void setAesCode(
        final String aesCode) {
        this.aesCode = aesCode;
    }

    /**
     * 取得身份证号
     *
     * @return 身份证号
     */
    public String getCertificateNum() {
        return certificateNum;
    }

    /**
     * 设置身份证号
     *
     * @param certificateNum 身份证号
     */
    public void setCertificateNum(
        final String certificateNum) {
        this.certificateNum = certificateNum;
    }

    /**
     * 取得人员公司名
     *
     * @return 人员公司名
     */
    public String getCompanyName() {
        return companyName;
    }

    /**
     * 设置人员公司名
     *
     * @param companyName 人员公司名
     */
    public void setCompanyName(
        final String companyName) {
        this.companyName = companyName;
    }

    /**
     * 取得创建者ID
     *
     * @return 创建者ID
     */
    public String getCreateUserId() {
        return createUserId;
    }

    /**
     * 设置创建者ID
     *
     * @param createUserId 创建者ID
     */
    public void setCreateUserId(
        final String createUserId) {
        this.createUserId = createUserId;
    }

    /**
     * 取得邮箱
     *
     * @return 邮箱
     */
    public String getEmail() {
        return email;
    }

    /**
     * 设置邮箱
     *
     * @param email 邮箱
     */
    public void setEmail(
        final String email) {
        this.email = email;
    }

    /**
     * 取得人员类别
     *
     * @return 人员类别
     */
    public String getEmployeeCategory() {
        return employeeCategory;
    }

    /**
     * 设置人员类别
     *
     * @param employeeCategory 人员类别
     */
    public void setEmployeeCategory(
        final String employeeCategory) {
        this.employeeCategory = employeeCategory;
    }

    /**
     * 取得工号
     *
     * @return 工号
     */
    public String getEmployeeNum() {
        return employeeNum;
    }

    /**
     * 设置工号
     *
     * @param employeeNum 工号
     */
    public void setEmployeeNum(
        final String employeeNum) {
        this.employeeNum = employeeNum;
    }

    /**
     * 取得人员类型
     *
     * @return 人员类型
     */
    public String getEmployeeType() {
        return employeeType;
    }

    /**
     * 设置人员类型
     *
     * @param employeeType 人员类型
     */
    public void setEmployeeType(
        final String employeeType) {
        this.employeeType = employeeType;
    }

    /**
     * 取得用户表示名
     *
     * @return 用户表示名
     */
    public String getFullName() {
        return fullName;
    }

    /**
     * 设置用户表示名
     *
     * @param fullName 用户表示名
     */
    public void setFullName(
        final String fullName) {
        this.fullName = fullName;
    }

    /**
     * 取得登录名
     *
     * @return 登录名
     */
    public String getLoginName() {
        return loginName;
    }

    /**
     * 设置登录名
     *
     * @param loginName 登录名
     */
    public void setLoginName(
        final String loginName) {
        this.loginName = loginName;
    }

    /**
     * 取得手机
     *
     * @return 手机
     */
    public String getMobile() {
        return mobile;
    }

    /**
     * 设置手机
     *
     * @param mobile 手机
     */
    public void setMobile(
        final String mobile) {
        this.mobile = mobile;
    }

    /**
     * 取得密码
     *
     * @return 密码
     */
    public String getPasswd() {
        return passwd;
    }

    /**
     * 设置密码
     *
     * @param passwd 密码
     */
    public void setPasswd(
        final String passwd) {
        this.passwd = passwd;
    }

    /**
     * 取得男、女
     *
     * @return 男、女
     */
    public String getSex() {
        return sex;
    }

    /**
     * 设置男、女
     *
     * @param sex 男、女
     */
    public void setSex(
        final String sex) {
        this.sex = sex;
    }

    /**
     * 取得办公电话
     *
     * @return 办公电话
     */
    public String getTel() {
        return tel;
    }

    /**
     * 设置办公电话
     *
     * @param tel 办公电话
     */
    public void setTel(
        final String tel) {
        this.tel = tel;
    }

    /**
     * 取得分机号
     *
     * @return 分机号
     */
    public String getTelExt() {
        return telExt;
    }

    /**
     * 设置分机号
     *
     * @param telExt 分机号
     */
    public void setTelExt(
        final String telExt) {
        this.telExt = telExt;
    }

    /**
     * 取得更新者ID
     *
     * @return 更新者ID
     */
    public String getUpdateUserId() {
        return updateUserId;
    }

    /**
     * 设置更新者ID
     *
     * @param updateUserId 更新者ID
     */
    public void setUpdateUserId(
        final String updateUserId) {
        this.updateUserId = updateUserId;
    }

    /**
     * 取得创建时间
     *
     * @return 创建时间
     */
    public Date getCreateTime() {
        return createTime;
    }

    /**
     * 设置创建时间
     *
     * @param createTime 创建时间
     */
    public void setCreateTime(
        final Date createTime) {
        this.createTime = createTime;
    }

    /**
     * 取得更新时间
     *
     * @return 更新时间
     */
    public Date getUpdateTime() {
        return updateTime;
    }

    /**
     * 设置更新时间
     *
     * @param updateTime 更新时间
     */
    public void setUpdateTime(
        final Date updateTime) {
        this.updateTime = updateTime;
    }

}
