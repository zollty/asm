package org.jretty.asmtool.prof;

import org.jretty.asmtool.prof.support.DefaultConfig;

public class Manager {
    
    // private construct
    private Manager() {
        try {
            
            // TODO init
            System.out.println("--------init---------");
            
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private static class LazyHolder {
        private static final Manager INSTANCE = new Manager();
    }

    public static Manager instance() {
        return LazyHolder.INSTANCE;
    }
    
    public static Config getConfig() {
        return DefaultConfig.instance();
    }
    

}
