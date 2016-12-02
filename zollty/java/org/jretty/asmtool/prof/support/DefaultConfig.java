package org.jretty.asmtool.prof.support;

import java.util.Arrays;
import java.util.List;

import org.jretty.asmtool.prof.Config;
import org.jretty.asmtool.prof.MethodStatis;
import org.jretty.asmtool.prof.Weave;

public class DefaultConfig implements Config {
    
    private volatile boolean openWeaveMethod = true;
    
    // private construct
    private DefaultConfig() {
        try {
            // TODO init
            System.out.println("--------init---------");
            
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private static class LazyHolder {
        private static final DefaultConfig INSTANCE = new DefaultConfig();
    }

    public static DefaultConfig instance() {
        return LazyHolder.INSTANCE;
    }
    
    public boolean isIgnoreGetSetMethod() {

        return false;
    }

    public boolean isNeedInject(String className) {
        
        return false;
    }

    public boolean isNotNeedInject(String className) {
        
        return false;
    }

    public boolean isNotNeedInjectClassLoader(String classLoader) {
        
        return false;
    }

    public void debug(String msg) {
        System.out.println(msg);
    }

    public boolean isUseNanoTime() {
        
        return false;
    }

    public boolean isOpenWeaveMethod() {
        return openWeaveMethod;
    }

    public void setOpenWeaveMethod(boolean openWeaveMethod) {
        this.openWeaveMethod = openWeaveMethod;
    }

    public String getInjectClass() {
        
        return "org/jretty/asmtool/prof/WeaveFacade";
    }

    public String getInjectStartMethod() {
        
        return "begin";
    }

    public String getInjectEndMethod() {
        
        return "end";
    }

    public List<Weave> getWeaves() {
        Weave wm = new MethodStatis();
        return Arrays.asList(wm);
    }

}
