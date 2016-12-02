package org.jretty.asmtool.prof;

import org.jretty.asmtool.prof.support.ThreadData;

public class MethodStatis implements Weave {
    
    private final static int size = 65535;
    /**
     * 线程数组
     */
    public ThreadData[] threadProfile = new ThreadData[size];
    
    private volatile boolean openWeaveMethod = true;

    public void clearData() {
        for (int index = 0; index < threadProfile.length; index++) {
            ThreadData profilerData = threadProfile[index];
            if (profilerData == null) {
                continue;
            }
            profilerData.clear();
        }
    }

    /**
     * 方法开始时调用,采集开始时间
     */
    public void begin(int methodId) {

        if (!openWeaveMethod) {
            return;
        }
        long threadId = Thread.currentThread().getId();
        if (threadId >= size) {
            return;
        }

        long startTime;
        if (Manager.getConfig().isUseNanoTime()) {
            startTime = System.nanoTime();
        } else {
            startTime = System.currentTimeMillis();
        }
        try {
            ThreadData thrData = threadProfile[(int) threadId];
            if (thrData == null) {
                thrData = new ThreadData();
                threadProfile[(int) threadId] = thrData;
            }

            long[] frameData = new long[3];
            frameData[0] = methodId;
            frameData[1] = thrData.stackNum;
            frameData[2] = startTime;
            thrData.stackFrame.push(frameData);
            thrData.stackNum++;
        } catch (Exception e) {
            e.printStackTrace();
        }
    
    }

    /**
     * 方法退出时调用,采集结束时间
     */
    public void end(int methodId) {

        if (!openWeaveMethod) {
            return;
        }
        long threadId = Thread.currentThread().getId();
        if (threadId >= size) {
            return;
        }

        long endTime;
        boolean useNanoTime = Manager.getConfig().isUseNanoTime();
        if (useNanoTime) {
            endTime = System.nanoTime();
        } else {
            endTime = System.currentTimeMillis();
        }
        try {
            ThreadData thrData = threadProfile[(int) threadId];
            if (thrData == null || thrData.stackNum <= 0 || thrData.stackFrame.size() == 0) {
                // 没有执行start,直接执行end/可能是异步停止导致的
                return;
            }
            // 栈太深则抛弃部分数据
            if (thrData.profileData.size() > 20000) {
                thrData.stackNum--;
                thrData.stackFrame.pop();
                return;
            }
            thrData.stackNum--;
            long[] frameData = thrData.stackFrame.pop();
            long id = frameData[0];
            if (methodId != id) {
                return;
            }
            long useTime = endTime - frameData[2];
            if (useNanoTime) {
                if (useTime > 100000) { // bigger than 0.1 ms
                    frameData[2] = useTime;
                    thrData.profileData.push(frameData);
                }
            } else if (useTime > 0) { // bigger than 1ms
                frameData[2] = useTime;
                thrData.profileData.push(frameData);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    
    }

    public boolean isOpenWeaveMethod() {
        return openWeaveMethod;
    }

    public void setOpenWeaveMethod(boolean openWeaveMethod) {
        this.openWeaveMethod = openWeaveMethod;
    }

}
