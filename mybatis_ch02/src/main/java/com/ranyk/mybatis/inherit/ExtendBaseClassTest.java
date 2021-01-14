package com.ranyk.mybatis.inherit;

import lombok.extern.slf4j.Slf4j;

/**
 * ClassName:ExtendBaseClassTest
 * Description:继承的子类
 *
 * @author ranyi
 * @date 2020-07-28 10:20
 * Version: V1.0
 */
@Slf4j
public class ExtendBaseClassTest extends BaseClass {

    public ExtendBaseClassTest(String str) {
        super();
    }

    public static void main(String[] args) {
        ExtendBaseClassTest extendBaseClassTest = new ExtendBaseClassTest("aaaa");
        log.info(extendBaseClassTest.getSuccess());
    }
}
