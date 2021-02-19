package com.ranyk.mybatis.concurrent.test.demo10;

import lombok.extern.log4j.Log4j2;
import org.junit.jupiter.api.Test;
import org.springframework.stereotype.Component;

import java.util.UUID;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ForkJoinPool;
import java.util.concurrent.TimeUnit;
import java.util.function.Function;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import java.util.stream.LongStream;

/**
 * @author ranyi
 */
@Log4j2
@Component(value = "main01Test")
public class Main01Test {

    public static final int THREAD_COUNT = 10;
    public static final int ITEM_COUNT = 1000;

    /**
     * 创建一个 ConcurrentHashMap ,其中存放了指定数量的元素
     * @param count 指定的 ConcurrentHashMap 中需要存放的元素数量
     * @return 返回创建的 ConcurrentHashMap 对象
     */
    public ConcurrentHashMap<String, Long> getData(int count) {
        return LongStream.rangeClosed(1, count)
                .boxed()
                .collect(Collectors.toConcurrentMap(i -> UUID.randomUUID().toString(), Function.identity(), (o1, o2) -> o1, ConcurrentHashMap::new));
    }

    @Test
    public void test() {
        log.info("================================== 操作开始 ========================================");
        int reduction = ITEM_COUNT - 100;
        ConcurrentHashMap<String, Long> concurrentMap = getData(reduction);
        log.error(String.format("初始化数据的数据量: %d", concurrentMap.size()));
        ForkJoinPool forkJoinPool = new ForkJoinPool(THREAD_COUNT);
        forkJoinPool.execute(() -> IntStream.rangeClosed(1, 10).parallel().forEach(i -> {
            synchronized (concurrentMap) {
                int reduction2 = ITEM_COUNT - concurrentMap.size();
                log.info("差量数据: " + reduction2);
                concurrentMap.putAll(getData(reduction2));
            }
        }));
        try {
            forkJoinPool.shutdown();
            forkJoinPool.awaitTermination(1, TimeUnit.HOURS);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        log.error(String.format("插入完成后的数据量: %d", concurrentMap.size()));
        log.info("================================== 操作结束 ========================================");

    }

}
