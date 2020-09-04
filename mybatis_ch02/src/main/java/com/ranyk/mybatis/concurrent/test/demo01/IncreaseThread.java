package com.ranyk.mybatis.concurrent.test.demo01;

/**
 * ClassName:IncreaseThread
 * Description:加一线程
 *
 * @author ranyi
 * @date 2020-06-06 0:19
 * Version: V1.0
 */
public class IncreaseThread extends Thread{

    private MyObject myObject;

    public IncreaseThread(MyObject myObject) {
        this.myObject = myObject;
    }

    @Override
    public void run() {
        int count = 30;
        for (int i = 0; i < count; i++) {
            try {
                Thread.sleep((long)(Math.random()*1000));
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            //myObject.increase();
            myObject.increase2();
        }

    }
}
