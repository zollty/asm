/**
 * (C) 2011-2012 Alibaba Group Holding Limited.
 *
 * This program is free software; you can redistribute it and/or
 * modify it under the terms of the GNU General Public License
 * version 2 as published by the Free Software Foundation.
 * 
 */
package org.jretty.asmtool.prof;

import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * 此类收集应用代码的运行时数据
 * 
 * @author zollty
 * @since 2016-11-23
 */
public class WeaveFacade {
    /**
     * 注入类数
     */
    public static AtomicInteger instrumentClassCount = new AtomicInteger(0);
    /**
     * 注入方法数
     */
    public static AtomicInteger instrumentMethodCount = new AtomicInteger(0);

    /**
     * 方法开始时调用,采集开始时间
     * 
     * @param methodId
     */
    public static void begin(int methodId) {
        if (!Manager.getConfig().isOpenWeaveMethod()) {
            return;
        }
        List<Weave> weaves = Manager.getConfig().getWeaves();
        for (Weave wv : weaves) {
            try {
                wv.begin(methodId);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    /**
     * 方法退出时调用,采集结束时间
     * 
     * @param methodId
     */
    public static void end(int methodId) {
        if (!Manager.getConfig().isOpenWeaveMethod()) {
            return;
        }
        List<Weave> weaves = Manager.getConfig().getWeaves();
        int size = weaves.size();
        for (int i = size - 1; i >= 0; i--) {
            try {
                weaves.get(i).begin(methodId);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }
}