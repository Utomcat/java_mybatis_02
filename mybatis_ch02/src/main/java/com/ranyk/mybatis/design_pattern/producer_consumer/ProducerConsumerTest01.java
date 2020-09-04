package com.ranyk.mybatis.design_pattern.producer_consumer;

import java.util.PriorityQueue;

/**
 * ClassName:ProducerConsumerTest
 * Description:生产者和消费者模式  使用非阻塞队列实现生产者消费者模式
 *
 * @author ranyi
 * @date 2020-05-31 1:56
 * Version: V1.0
 */
public class ProducerConsumerTest01 {

    private int queueSize = 10;
    private PriorityQueue<Integer> queue = new PriorityQueue<Integer>(queueSize);


    public static void main(String[] args) {
        ProducerConsumerTest01 producerConsumerTest01 = new ProducerConsumerTest01();
        Consumer consumer = producerConsumerTest01.new Consumer();
        Producer producer = producerConsumerTest01.new Producer();

        consumer.start();
        producer.start();
    }


    /**
     * 消费者
     */
    class Consumer extends Thread {

        @Override
        public void run() {
            consume();
        }

        private void consume() {
            while (true) {
                synchronized (queue) {
                    while (queue.size() == 0) {
                        try {
                            System.out.println("队列为空，请等待数据！");
                            queue.wait();
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                            queue.notify();
                        }
                    }
                    queue.poll();
                    queue.notify();
                    System.out.println("从队列中取走一个元素，队列当前还剩余：" + queue.size() + "个元素！");
                }
            }
        }
    }

    /**
     * 生产者
     */
    class Producer extends Thread {

        @Override
        public void run() {
            produce();
        }

        private void produce() {
            while (true) {
                synchronized (queue) {
                    while (queue.size() == queueSize) {
                        try {
                            System.out.println("队列已满，请等待空余空间！");
                            queue.wait();
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                            queue.notify();
                        }
                    }
                    queue.offer(1);
                    queue.notify();
                    System.out.println("生产者想队列中插入了一个元素，当前队列中剩余空间为：" + (queueSize - queue.size()));
                }
            }
        }
    }


}
