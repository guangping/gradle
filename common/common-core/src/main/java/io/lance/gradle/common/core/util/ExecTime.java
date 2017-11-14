package io.lance.gradle.common.core.util;

import java.math.BigDecimal;

/**
 * @desc: 记录耗时
 * @author: lance
 * @time: 2017-09-14 14:20:02
 */
public class ExecTime {

    private long start;

    private long end;

    private long time;

    public ExecTime() {
        start = System.currentTimeMillis();
    }

    public String toString() {

        end = System.currentTimeMillis();
        long all = end - start;
        time = all;

        BigDecimal d = new BigDecimal(all + "");
        if (all < 1000) {
            return all + " ms";
        } else if (all < 60 * 1000) {
            return d.divide(new BigDecimal(1000 + ""), 2, BigDecimal.ROUND_HALF_DOWN) + " s";
        } else {
            return d.divide(new BigDecimal(60 + ""), 4, BigDecimal.ROUND_HALF_DOWN).divide(new BigDecimal(1000 + ""), 2, BigDecimal.ROUND_HALF_DOWN) + " m";
        }
    }

    public long getTime() {
        return time;
    }
}
