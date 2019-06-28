package com.haotian.core.util;

import org.apache.commons.lang3.time.StopWatch;

import java.text.SimpleDateFormat;
import java.util.Map;
import java.util.Random;
import java.util.StringTokenizer;
import java.util.UUID;
import java.util.concurrent.*;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.atomic.AtomicLong;

/**
 * @ClassName: _UniqueIDKit
 * @author: 张朋
 * @date: 2016年10月25日 下午4:57:48
 */
public class UniqueIdUtils {


    public static String getJavaUtilUUID() {
        StringBuilder sb = new StringBuilder();
        UUID uuid = UUID.randomUUID();
        StringTokenizer tokenizer = new StringTokenizer(uuid.toString(), "-", false);
        int count = tokenizer.countTokens();
        for (int i = 0; i < count; i++) {
            sb.append(tokenizer.nextToken());
        }
        return sb.toString();
    }


    /**
     * @Title: 有序
     * @author: 张朋
     * @date: 2016年10月26日 下午4:20:13
     * @return: String length 32
     */
    public static String getSortNumber() {
        StringBuffer sb = new StringBuffer();
        sb.append(new SimpleDateFormat("yyyyMMddHHmmssSSS").format(System.currentTimeMillis()));
        sb.append('-');
        sb.append(createRadom(14, 2));
        return sb.toString();
    }

    /**
     * @param iLen  需要获得随机数的长度
     * @param iType 随机数的类型： '0 ':表示仅获得数字随机数； '1 '：表示仅获得字符随机数； '2'：表示获得数字字符混合随机数
     * @Title: 该函数获得随机数字符串
     * @author: 张朋
     * @date: 2016年10月25日 下午4:18:23
     * @return: String
     */
    private static String createRadom(int iLen, int iType) {
        StringBuffer sb = new StringBuffer("");
        Random rnd = new Random();
        if (iLen < 0) {
            iLen = 5;
        }
        switch (iType) {
            case 0:
                for (int iLoop = 0; iLoop < iLen; iLoop++) {
                    sb.append(rnd.nextInt(10));
                }
                break;
            case 1:
                for (int iLoop = 0; iLoop < iLen; iLoop++) {
                    sb.append(Integer.toString(35 - rnd.nextInt(26), 36));
                }
                break;
            default:
                for (int iLoop = 0; iLoop < iLen; iLoop++) {
                    sb.append(Integer.toString(rnd.nextInt(36), 36));
                }
                break;
        }
        return sb.toString().trim();
    }


    private static AtomicInteger aiShort = new AtomicInteger(0);
    private static final int maxUnsignedShort = 255;

    /**
     * 单字节标识自增循环使用
     */
    public static int getUnsignedShort() {
        int next = aiShort.incrementAndGet();
        if (next > maxUnsignedShort) {
            synchronized (aiShort) {
                if (aiShort.get() > maxUnsignedShort) {
                    aiShort.set(0);
                    next = aiShort.incrementAndGet();
                } else {
                    next = aiShort.incrementAndGet();
                }
            }
        }
        return next;
    }


    private static AtomicLong al = new AtomicLong(0);

    /**
     * 序列 自增（保证原子性 (线程安全)）(高效) Max: 9223372036854775806
     */
    public static long getSequence() {
        long next = al.incrementAndGet();
        if (next > Long.MAX_VALUE) {
            synchronized (al) {
                if (al.get() > Long.MAX_VALUE) {
                    al.set(0);
                    next = al.incrementAndGet();
                } else {
                    next = al.incrementAndGet();
                }
            }
        }
        return next;
    }

    public static ExecutorService newFixedThreadPool(int nThreads) {
        return new ThreadPoolExecutor(nThreads, nThreads,
                0L, TimeUnit.MILLISECONDS,
                new LinkedBlockingQueue<Runnable>());
    }

    public static void main(String[] args) {

        StopWatch sw = new StopWatch();
        sw.start();
        for (int i = 0; i < 1000; i++) {
            System.out.println(getSortNumber());
        }
        sw.stop();
        System.out.println(sw.getTime());
    }


}
