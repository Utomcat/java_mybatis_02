package com.ranyk.mybatis.concurrent.test.demo12;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import java.util.concurrent.Semaphore;

/**
 * CLASS_NAME: MultiThread05<br/>
 * Description: 多线程示例五 - Semaphore使用练习 <br/>
 *
 * @author ranYk <br/>
 * @version 1.0
 */
@Slf4j
public class MultiThread05 {

    public static void main(String[] args) {
        int worker = 8;
        Semaphore semaphore = new Semaphore(3);
        try {
            for (int i = 1; i <= worker; i++) {
                new Thread(new Worker(i, semaphore),"工人"+i).start();
            }
        } catch (Exception e) {
            log.error("线程 {} 发生异常,异常信息为: {}", Thread.currentThread().getName(), e.getMessage());
        }
    }

}


@Data
@Slf4j
@NoArgsConstructor
@AllArgsConstructor
class Worker implements Runnable {
    /**
     * 工作人员数量
     */
    private int workerNum;
    /**
     * 资源对象
     */
    private Semaphore semaphore;


    /**
     * When an object implementing interface <code>Runnable</code> is used
     * to create a thread, starting the thread causes the object's
     * <code>run</code> method to be called in that separately executing
     * thread.
     * <p>
     * The general contract of the method <code>run</code> is that it may
     * take any action whatsoever.
     *
     * @see Thread#run()
     */
    @Override
    public void run() {
        try {
            //获取资源
            semaphore.acquire();
            log.info("线程 {} 获取到机器,开始工作...", Thread.currentThread().getName());
            //使用资源
            Thread.sleep(1000);
            log.info("线程 {} 使用机器完毕,结束工作...", Thread.currentThread().getName());
            //释放资源
            semaphore.release();
        } catch (Exception e) {
            log.error("线程 {} 发生异常,程序终止,异常信息为: {} ", Thread.currentThread().getName(), e.getMessage());
        }
    }
}
