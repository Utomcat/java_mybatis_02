package com.ranyk.mybatis.inherit;

/**
 * ClassName:ExtendBaseClassTest
 * Description:继承的子类
 *
 * @author ranyi
 * @date 2020-07-28 10:20
 * Version: V1.0
 */
public class ExtendBaseClassTest extends BaseClass {

    public ExtendBaseClassTest(String str) {
        super(str);
    }

    public static void main(String[] args) {
        ExtendBaseClassTest extendBaseClassTest = new ExtendBaseClassTest("aaaa");
        BaseClass base = extendBaseClassTest.getBase();
        System.out.println(extendBaseClassTest.getSuccess());
    }
}
