package com.ranyk.mybatis_ch02.design_pattern.producer_consumer;

import java.util.concurrent.ArrayBlockingQueue;

/**
 * ClassName:ProducerConsumerTest02
 * Description:生产者消费者模式 - 使用阻塞队列实现
 *
 * @author ranyi
 * @date 2020-05-31 2:18
 * Version: V1.0
 */
public class ProducerConsumerTest02 {

    private int queueSize = 10;
    private ArrayBlockingQueue<Integer> queue = new ArrayBlockingQueue<Integer>(queueSize);

    public static void main(String[] args) {
        ProducerConsumerTest02 producerConsumerTest02 = new ProducerConsumerTest02();
        Consumer consumer = producerConsumerTest02.new Consumer();
        Producer producer = producerConsumerTest02.new Producer();
        consumer.start();
        producer.start();
    }


    class Consumer extends Thread{
        @Override
        public void run() {
            consume();
        }

        private void consume() {
            while(true){
                try{
                    queue.take();
                    System.out.println("从队列中取走一个元素，队列当前还剩余：" + queue.size() + "个元素！");
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    class Producer extends  Thread{
        @Override
        public void run() {
            produce();
        }

        private void produce() {
            while (true){
                try {
                    queue.put(1);
                    System.out.println("生产者想队列中插入了一个元素，当前队列中剩余空间为：" + (queueSize - queue.size()));
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }
    }


}
