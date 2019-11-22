package com.input.qz.input.bean;

import org.greenrobot.greendao.annotation.Entity;
import org.greenrobot.greendao.annotation.Generated;
import org.greenrobot.greendao.annotation.Id;
import org.greenrobot.greendao.annotation.Index;
import org.greenrobot.greendao.annotation.NotNull;

/**
 * 版本号
 */
@Entity(indexes = {
        @Index(value = "number ASC", unique = true)
})
public class Version {
    @Id(autoincrement = true)
    private Long id;
    /**
     *
     */
    private String name;
    /**
     * 版本号:
     * 1.0.0
     */
    @NotNull
    private String number;
@Generated(hash = 155438343)
public Version(Long id, String name, @NotNull String number) {
    this.id = id;
    this.name = name;
    this.number = number;
}
@Generated(hash = 1433053919)
public Version() {
}
public String getName() {
    return this.name;
}
public void setName(String name) {
    this.name = name;
}
public String getNumber() {
    return this.number;
}
public void setNumber(String number) {
    this.number = number;
}
public Long getId() {
    return this.id;
}
public void setId(Long id) {
    this.id = id;
}




}
