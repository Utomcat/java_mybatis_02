package com.ranyk.mybatis.concurrent.test.demo01;

/**
 * ClassName:DecreaseThread
 * Description:减一线程
 *
 * @author ranyi
 * @date 2020-06-06 0:18
 * Version: V1.0
 */
public class DecreaseThread extends Thread{

    private MyObject myObject;

    public DecreaseThread(MyObject myObject) {
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
            //myObject.decrease();
            myObject.decrease2();
        }

    }
}
