package com.input.qz.input.bean;

import org.greenrobot.greendao.annotation.Entity;
import org.greenrobot.greendao.annotation.Generated;
import org.greenrobot.greendao.annotation.Id;
import org.greenrobot.greendao.annotation.Index;
import org.greenrobot.greendao.annotation.NotNull;

/**
 * 配置
 */
@Entity(
        indexes = {
        @Index(value = "name ASC", unique = true)
})
public class Config {
    //英文词库是否初始化
    public static final String table_english_inited="table.english.inited";
    @Id(autoincrement = true)
    private Long id;
    /**
     *
     */
    @NotNull
    private String name;
    /**
     * 版本号:
     * 1.0.0
     */
    @NotNull
    private String value;

    @Generated(hash = 1036370983)
    public Config(Long id, @NotNull String name, @NotNull String value) {
        this.id = id;
        this.name = name;
        this.value = value;
    }

    @Generated(hash = 589037648)
    public Config() {
    }

    public boolean isTure() {
        return Boolean.parseBoolean(value);
    }

    public String getName() {
        return this.name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getValue() {
        return this.value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    public Long getId() {
        return this.id;
    }

    public void setId(Long id) {
        this.id = id;
    }


}
