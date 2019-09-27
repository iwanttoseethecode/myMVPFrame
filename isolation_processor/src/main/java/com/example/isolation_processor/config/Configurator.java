package com.example.isolation_processor.config;

import android.content.Context;

import java.util.HashMap;

/**
 * Created by luoling on 2019/9/20.
 * description:
 */
public class Configurator {

    private static final HashMap<ConfigKeys,Object> CONFIGS = new HashMap<>();

    private Configurator(){
        CONFIGS.put(ConfigKeys.CONFIG_READY,false);
    }

    private static class Holder{
        private static final Configurator INSTANCE = new Configurator();
    }

    public static Configurator getInstance(){
        return Holder.INSTANCE;
    }

    public final HashMap<ConfigKeys,Object> getConfigs(){
        return CONFIGS;
    }

    public Configurator setAPI_HOST(String host){
        CONFIGS.put(ConfigKeys.API_HOST,host);
        return this;
    }

    public Configurator setAPPLICATION_CONTEXT(Context context){
        CONFIGS.put(ConfigKeys.APPLICATION_CONTEXT,context);
        return this;
    }

    public Configurator setINTERCEPTOR(Object object){
        CONFIGS.put(ConfigKeys.INTERCEPTOR,object);
        return this;
    }

    public final void configReady(){
        CONFIGS.put(ConfigKeys.CONFIG_READY,true);
    }

    private void checkConfiguration(){
        final boolean isReady = (boolean) CONFIGS.get(ConfigKeys.CONFIG_READY);
        if (!isReady){
            throw new RuntimeException("Configurator is not ready,call configReady()");
        }
    }

    public final <T> T getConfiguration(ConfigKeys key){
        checkConfiguration();
        final T value = (T) CONFIGS.get(key);
        if (value == null){
            throw new NullPointerException(key.name() + "is null");
        }
        return value;
    }

}
