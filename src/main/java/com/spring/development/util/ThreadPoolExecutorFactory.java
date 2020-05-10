package com.spring.development.util;

import com.google.common.util.concurrent.ThreadFactoryBuilder;

import java.util.concurrent.*;

/**
 * @Description
 * @Project development
 * @Package com.spring.development.util
 * @Author xuzhenkui
 * @Date 2020/5/10 8:24
 */
public class ThreadPoolExecutorFactory {
    private final static int CORE_POOL_SIZE = 3;

    private final static int MAXIMUM_POOL_SIZE = 6;

    private final static long KEEP_ALIVE_TIME = 3 * 60;

    private final static TimeUnit TIME_UNIT = TimeUnit.SECONDS;

    private final static BlockingQueue<Runnable> workQueue = new LinkedBlockingQueue<Runnable>();

    private final static ThreadFactory threadFactory =  new ThreadFactoryBuilder().setNameFormat("Thread-pool-%d").build();


    private static ExecutorService threadPoolExecutor = new ThreadPoolExecutor(CORE_POOL_SIZE, MAXIMUM_POOL_SIZE,
            KEEP_ALIVE_TIME, TIME_UNIT,
            workQueue, new ThreadPoolExecutor.AbortPolicy());

    public static ExecutorService getThreadPoolExecutor() {
        return threadPoolExecutor;
    }
}
