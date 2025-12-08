package com.bite.springblogdemo;

import com.bite.springblogdemo.common.util.SecurityUtils;
import org.junit.jupiter.api.Test;

public class MD5Test {

    @Test
    public void test() {
        System.out.println(SecurityUtils.encrypt("123456"));
    }
}
