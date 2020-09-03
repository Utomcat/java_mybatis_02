package com.ranyk.mybatis_ch02.concurrent.test.demo04;

import java.util.Arrays;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;
import java.util.stream.IntStream;

/**
 * ClassName:MyTest2
 * Description:Lock学习二
 *
 * <ul>程序解决的问题：
 * <li>声明一个有界(有限长度)的数组，数组的每个位置存放一个值 a</li>
 * <li>现使用两个线程对这个数组中的数据进行获取和填充，其获取和填充规则为：</li>
 *
 * <ul>填充规则：
 *  <li>在还未将数组填充满之前，按数组的位置依次往数组中填充值</li>
 *  <li>当填充满后，下一次填充的位置为数组的第一个位置重新开始填充</li>
 *  <li>当重新填充时，只有当对应位置的值为null或‘’的时候才能填充</li>
 *  <li>当数组中所有的位置都不为null或‘’的时候，填充线程等待</li>
 * </ul>
 *
 *
 * <ul>获取规则：
 *  <li>从数组的第一个位置开始一次获取对应位置的值，获取后将数组对应位置的值重置为null</li>
 *  <li>当数组中的值均为null或‘’的时候，获取线程等待</li>
 *  <li>当获取到最后一个元素的时候，下一次的获取将又从数组的第一个元素开始获取</li>
 * </ul>
 *
 * </ul>
 *
 * @author ranyi
 * @date 2020-06-11 16:38
 * Version: V1.0
 */
public class MyTest2 {

    public static void main(String[] args) {
        BoundedContainer boundedContainer = new BoundedContainer();

        IntStream.range(0, 10).forEach(i -> new Thread(() -> {
            boundedContainer.take();
        }).start());

        IntStream.range(0, 8).forEach(i -> new Thread(() -> {
            boundedContainer.put("hello" + i);
        }).start());
    }

}

/**
 * 定义操作容器
 */
class BoundedContainer {

    /**
     * 定义数据存放容器
     */
    private String[] elements = new String[10];

    /**
     * 定义对数据容器的操作锁
     */
    private Lock lock = new ReentrantLock();

    /**
     * 定义获取的操作Condition
     */
    private Condition notEmptyCondition = lock.newCondition();

    /**
     * 定义放置的操作Condition
     */
    private Condition notFullCondition = lock.newCondition();

    /**
     * 定义获取下标
     */
    private int takeIndex = 0;

    /**
     * 定义放置下标
     */
    private int putIndex = 0;

    /**
     * 定义计数器，用来确定当前容器中是否存在元素，为0的时候就是没有元素，当为数组长度时，即为元素已满
     */
    private int elementCount = 0;


    /**
     * 获取元素的方法
     *
     * @return
     */
    public void take() {
        //获取锁并上锁
        lock.lock();
        try {
            //进行获取元素的操作
            //获取操作规则：
            //1. 数组中没有有元素，线程等待
            //2. 对应的下表位置的值不为null

            //获取操作：
            //0. 判断当前数组能否进行元素的获取，即当前的数组中是否存在元素
            while (0 == elementCount) {
                notEmptyCondition.await();
            }

            //1. 获取当前是从什么位置获取元素,并获取元素
            String element = elements[takeIndex];

            //2. 判断当前位置能否获取元素，即对应位置的值是否为null，是，则无法获取；不是则获取，然后将对应位置的值置为null
            if (null == element) {
                //System.out.println("当前位置" + takeIndex + "没有元素，无法获取");
                //3. 将原来的位置加一，用于下一次的获取，当期位置加一是数组长度的时候，则将位置置为数组首位即将其置为0
                takeIndex++;

                if (takeIndex == elements.length) {
                    takeIndex = 0;
                }

                return;
            }

            elements[takeIndex] = null;

            //System.out.println("获取的位置为" + takeIndex + "的元素的值为：" + element);

            //3. 将原来的位置加一，用于下一次的获取，当期位置加一是数组长度的时候，则将位置置为数组首位即将其置为0
            takeIndex++;

            if (takeIndex == elements.length) {
                takeIndex = 0;
            }

            //4. 对应的元素计数器减一
            elementCount--;

            if (elementCount < 0) {
                elementCount = 0;
            }

            System.out.println("take method：" + Arrays.toString(elements));

            //5. 唤醒添加线程
            notFullCondition.signal();


        } catch (InterruptedException e) {
            //System.out.println("获取线程等待出错");
            e.printStackTrace();
        } finally {
            lock.unlock();
            //释放锁
            //System.out.println("获取线程释放锁");
        }
    }

    public void put(String str) {
        //获取并上锁
        lock.lock();
        try {
            //TODO 进行放置元素操作
            //放置规则：
            //1. 数组中元素已满，线程等待
            //2. 数组中对应位置的值为null

            //放置操作：
            //0. 判断当前是否因该放置元素
            while (elementCount == elements.length) {
                notFullCondition.await();
            }

            //1. 获取需要放置位置的元素
            String element = elements[putIndex];

            //2. 判断是否因该放置元素，当前位置的元素是null，则放置元素，否则不放置
            if (null != element) {
                //System.out.println("当前位置" + putIndex + "有元素，无法放置");
                //3. 将原来的位置加一，用于下一次的放置，当前位置加一是数组长度的时候，则将位置置为数组首位即将其置为0
                putIndex++;

                if (putIndex == elements.length) {
                    putIndex = 0;
                }

                return;
            }

            elements[putIndex] = str;

            //System.out.println("放置的位置为" + putIndex + "的元素的值为：" + elements[putIndex]);

            //3. 将原来的位置加一，用于下一次的放置，当前位置加一是数组长度的时候，则将位置置为数组首位即将其置为0
            putIndex++;

            if (putIndex == elements.length) {
                putIndex = 0;
            }


            //4. 对应的元素计数器减一
            elementCount++;
            if (elementCount > elements.length) {
                elementCount = elements.length;
            }

            System.out.println("put method：" + Arrays.toString(elements));

            //5. 唤醒获取线程
            notEmptyCondition.signal();

        } catch (InterruptedException e) {
            //System.out.println("放置线程等待出错");
            e.printStackTrace();
        } finally {
            //释放锁
            lock.unlock();
            //System.out.println("添加线程释放锁");
        }

    }


}
