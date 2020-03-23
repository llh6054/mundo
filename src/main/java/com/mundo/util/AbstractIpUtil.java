package com.mundo.util;

import org.apache.http.util.Asserts;

import java.util.ArrayList;
import java.util.List;

/**
 * @author chubby
 * @date 2020/3/22
 *
 * 将ip拆成两个部分分别放在两个位图中存储
 * 当两个部分都出现在位图中时认为ip存在，反之不存在
 */
public abstract class AbstractIpUtil {

    /**
     * 被拆分ip部分的大小
     */
    private static final Integer PARTITION = 256256;

    /**
     * 用于存储将ip中间拆开后的第一部分
     * 如果按照普通数组方式存储 我们需要开辟一个new int[N]的空间
     * 但是使用位图 我们使用的空间是原来的 1/32
     */
    private static final Integer[]  FIRST_PART = new Integer[PARTITION / 32 + 1];

    /**
     * 用于存储将ip中间拆开后的第二部分
     */
    private static final Integer[]  SECOND_PART = new Integer[PARTITION / 32 + 1];

    /**
     * ip分割的第一部分在数组中的下标
     */
    private static final Byte FIRST_PART_INDEX = 1;

    /**
     * ip分割的第二部分在数组中的下标
     */
    private static final Byte SECOND_PART_INDEX = 2;

    /**
     * 维护一个总数用于统计
     */
    private static Integer total = 0;

    /**
     * row = n / 32 求整数n在数组a中的下标 如 n = 7 那么它在数组下标为0的位置
     * n=32(32/32=1) 那么它在数组下标为1的位置
     * a[row] |= 1 << (n & 0x1F) 这个语句是求n在对应下标的具体位置
     * n & 0x1F 表示将n和31求与  如n = 7 那么7 & 0x1F = 7 表示在0中的位置为7
     * 如n = 32 那么32 & 0x1F = 0 表示在0中的位置为0
     * 1 << (n & 0x1F) n % 32
     *
     * @param value
     * @param  bitMap
     */
    private static void addValue(Integer value, Integer[] bitMap) {
        int row = value >> 5;
        bitMap[row] |= 1 << (value & 0x1F);
    }

    /**
     * 判断当前数字是否存在
     * @param value
     * @param bitMap
     * @return
     */
    private static Boolean exists(Integer value, Integer[] bitMap) {
        int row = value >> 5;
        return (bitMap[row] & (1 << (value & 0x1F))) == 1;
    }

    public static Boolean ipExists(String ip) {
        return ipExists(partitionForBitMap(ip));
    }

    private static Boolean ipExists(List<Integer> partitions) {
        Asserts.check(partitions.size() != 2, "分割的ip应包含两部分");
        Boolean exist = exists(partitions.get(FIRST_PART_INDEX), FIRST_PART) && exists(partitions.get(SECOND_PART_INDEX), SECOND_PART);
        return exist;
    }

    /**
     * 添加ip至位图中
     * @param ip
     */
    public static Boolean addIp(String ip) {
        List<Integer> partitions = partitionForBitMap(ip);
        if (ipExists(partitions)) {
            return false;
        }
        addValue(partitions.get(FIRST_PART_INDEX), FIRST_PART);
        addValue(partitions.get(SECOND_PART_INDEX), SECOND_PART);
        total++;
        return true;
    }

    /**
     * 将ip分割为两部分的整数
     * @param ip
     * @return
     */
    private static List<Integer> partitionForBitMap(String ip) {
        List<Integer> partitions = new ArrayList<>(2);
        String[] segments = ip.split("\\.");
        partitions.add(Integer.valueOf(String.valueOf(new StringBuilder(segments[0]).append(segments[1]))));
        partitions.add(Integer.valueOf(String.valueOf(new StringBuilder(segments[2]).append(segments[3]))));
        return partitions;
    }

    public static Integer total() {
        return total;
    }

}
