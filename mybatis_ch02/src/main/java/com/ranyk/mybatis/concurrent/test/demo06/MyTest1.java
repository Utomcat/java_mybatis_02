package com.ranyk.mybatis.concurrent.test.demo06;

/**
 * ClassName:MyTest1
 * Description:CAS学习一
 *
 * @author ranyi
 * @date 2020-06-12 22:43
 * Version: V1.0
 */
public class MyTest1 {

    private int count;

    public int getCount() {
        return count;
    }

    public void increaseCount() {
        ++this.count;
    }
}
