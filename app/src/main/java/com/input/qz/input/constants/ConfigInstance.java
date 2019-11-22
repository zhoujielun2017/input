package com.input.qz.input.constants;

import com.input.qz.input.bean.Config;

import java.util.ArrayList;
import java.util.List;

public class ConfigInstance {
    private static final ConfigInstance ourInstance = new ConfigInstance();

    private List<Config> configs = new ArrayList<Config>();


    public static ConfigInstance getInstance() {
        return ourInstance;
    }

    private ConfigInstance() {
    }

    public void init(List<Config> configs){
        this.configs=configs;
    }

    private Config getConfigByName(String name){
        for (Config config : this.configs) {
            if(config.getName().equals(name)){
                return config;
            }
        }
        return null;
    }

    /**
     * 英文字库是否已经初始化
     * @return
     */
    public boolean tableEnglishInited() {
        Config config = getConfigByName(Config.table_english_inited);
        if(null==config){
            return false;
        }
        return getConfigByName(Config.table_english_inited).isTure();
    }


}
