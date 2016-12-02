package org.jretty.asmtool.prof;

import java.util.List;

public interface Config {
    
    /**
     * 是否统计变量的get&&set方法，默认false
     * @return
     */
    public boolean isIgnoreGetSetMethod();
    
    /**
     * 是否是需要注入的类
     * 
     * @param className
     * @return
     */
    public boolean isNeedInject(String className);

    /**
     * 是否是不需要注入的类
     * 
     * @param className
     * @return
     */
    public boolean isNotNeedInject(String className);

    /**
     * 是否是不需要注入的类加载器
     * 
     * @param classLoader
     * @return
     */
    public boolean isNotNeedInjectClassLoader(String classLoader);
    
    /**
     * 打印调试日志
     * @param msg
     */
    public void debug(String msg);
    
    /**
     * 是否使用纳秒统计时间
     * @return
     */
    public boolean isUseNanoTime();
    
    /**
     * 是否统计
     * @return
     */
    public boolean isOpenWeaveMethod();
    
    /**
     * 统计开关
     * @param openWeaveMethod
     * @return
     */
    public void setOpenWeaveMethod(boolean openWeaveMethod);
    
    public List<Weave> getWeaves();
    
    public String getInjectClass();
    
    public String getInjectStartMethod();
    
    public String getInjectEndMethod();

}
