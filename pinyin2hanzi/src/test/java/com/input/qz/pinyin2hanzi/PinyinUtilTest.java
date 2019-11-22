package com.input.qz.pinyin2hanzi;

import org.junit.Assert;
import org.junit.Test;

public class PinyinUtilTest {

    @Test
    public void getShengmu(){
        Assert.assertEquals(PinyinUtil.getShengmu("chang"),"ch");
    }
}
