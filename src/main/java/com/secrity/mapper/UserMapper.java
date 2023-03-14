/*
 * @(#)UserMapper.java
 * Copyright (C) 2020 Neusoft Corporation All rights reserved.
 *
 * VERSION        DATE       BY              CHANGE/COMMENT
 * ----------------------------------------------------------------------------
 * @version 1.00  2022年7月28日 wwp-pc          初版
 *
 */
package com.secrity.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.secrity.entity.SysUser;

//只需定义接口，具体语句在xml中
public interface UserMapper extends BaseMapper<SysUser>{
   
}
