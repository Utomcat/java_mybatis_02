package com.ranyk.mybatis.controller;

import com.ranyk.mybatis.concurrent.test.demo10.Main01Test;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ForkJoinPool;
import java.util.concurrent.TimeUnit;
import java.util.stream.IntStream;

/**
 * ClassName:ThreadTestController<br/>
 * Description:ThreadLocal 变量 和 ConcurrentHashMap 在多线程环境中的问题测试
 *
 * @author ranYk <br/>
 * @version: V1.0
 */
@Slf4j
@RestController
public class ThreadTestController {

    private ThreadLocal<Integer> currentUser = ThreadLocal.withInitial(() -> null);
    @Autowired
    private Main01Test main01Test;

    /**
     * @param userId 用户ID
     */
    @GetMapping("wrong")
    public Map<String, Object> wrong(@RequestParam("userId") Integer userId) {
        String before = Thread.currentThread().getName() + " : " + currentUser.get();
        currentUser.set(userId);
        String after = Thread.currentThread().getName() + " : " + currentUser.get();
        log.error("before: " + before);
        log.error("after: " + after);
        HashMap<String, Object> result = new HashMap<>(16);
        result.put("before", before);
        result.put("after", after);
        return result;
    }

    @GetMapping("right")
    public Map<String, Object> right(@RequestParam("userId") Integer userId) {
        HashMap<String, Object> result = new HashMap<>(16);
        try {
            String before = Thread.currentThread().getName() + " : " + currentUser.get();
            currentUser.set(userId);
            String after = Thread.currentThread().getName() + " : " + currentUser.get();
            log.error("before: " + before);
            log.error("after: " + after);
            result.put("before", before);
            result.put("after", after);
        } finally {
            currentUser.remove();
        }
        return result;
    }

    @GetMapping("concurrentHashMapWrong")
    public Map<String, Object> concurrentHashMapWrong() {
        Map<String, Object> result = new HashMap<>(16);
        log.info("===================================== 数据操作开始 ===============================================");
        ConcurrentHashMap<String, Long> data = main01Test.getData(Main01Test.ITEM_COUNT - 100);
        log.info("操作开始前创建的数据存储对象中数据的个数为: " + data.size());
        ForkJoinPool forkJoinPool = new ForkJoinPool(Main01Test.THREAD_COUNT);
        forkJoinPool.execute(() -> IntStream.rangeClosed(1, 10).parallel().forEach(i -> {
            // 此处使用和不使用 同步机制的差异
            synchronized (data) {
                int gap = Main01Test.ITEM_COUNT - data.size();
                log.error("gap ==> " + gap);
                data.putAll(main01Test.getData(gap));
            }
        }));
        forkJoinPool.shutdown();
        try {
            boolean b = forkJoinPool.awaitTermination(1, TimeUnit.MINUTES);
            if (b) {
                result.put("code", 200);
                result.put("msg", "ok");
                result.put("data", data);
            } else {
                result.put("code", 500);
                result.put("msg", "error");
                result.put("data", null);
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        log.info("操作完成后的数据存储对象中数据的个数为: " + data.size());
        log.info("===================================== 数据操作开始 ===============================================");
        return result;
    }
}
