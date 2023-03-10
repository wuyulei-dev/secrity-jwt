/*
 * @(#)aa.java
 * Copyright (C) 2020 Neusoft Corporation All rights reserved.
 *
 * VERSION        DATE       BY              CHANGE/COMMENT
 * ----------------------------------------------------------------------------
 * @version 1.00  2023年3月9日 wwp-pc          初版
 *
 */
package com.secrity;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

public class aa {

    public static void main(
        String[] args) {
        BCryptPasswordEncoder bCryptPasswordEncoder = new BCryptPasswordEncoder();
        String encode = bCryptPasswordEncoder.encode("123456");
        System.out.println(encode);
    }

}
