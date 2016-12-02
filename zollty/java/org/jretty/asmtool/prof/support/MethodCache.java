/**
 * (C) 2011-2012 Alibaba Group Holding Limited.
 *
 * This program is free software; you can redistribute it and/or
 * modify it under the terms of the GNU General Public License
 * version 2 as published by the Free Software Foundation.
 * 
 */
package org.jretty.asmtool.prof.support;

import java.util.ArrayList;
import java.util.List;

/**
 * 方法名缓存,用ID代替方法名进行剖析,提升性能
 * 
 * @author zollty
 * @since 2016-11-23
 */
public class MethodCache {

    /**
     * 方法缓存默认大小
     */
    private static final int INIT_CACHE_SIZE = 10240;
    /**
     * 方法名缓存
     */
    private static List<MethodInfo> mCacheMethods = new ArrayList<MethodInfo>(INIT_CACHE_SIZE);

    /**
     * 占位并生成方法ID
     * 
     * @param fileName
     * @param className
     * @param methodName
     * @return
     */
    public synchronized static int createMethodInfo(String fileName, String className, String methodName) {
        mCacheMethods.add(new MethodInfo(className, methodName));
        return mCacheMethods.size() - 1;
    }

    /**
     * 更新行号
     * 
     * @param id
     * @param linenum
     */
    public static void UpdateLineNum(int id, int linenum) {
        mCacheMethods.get(id).setMLineNum(linenum);
    }

}