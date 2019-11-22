package com.input.qz.input.bean;

import org.greenrobot.greendao.annotation.Entity;
import org.greenrobot.greendao.annotation.Id;
import org.greenrobot.greendao.annotation.Index;
import org.greenrobot.greendao.annotation.NotNull;
import org.greenrobot.greendao.annotation.Property;
import org.greenrobot.greendao.annotation.Generated;

/**
 * 汉字
 */
@Entity(
        createInDb = false,
        indexes = {
        @Index(value = "pinyin ASC", unique = true)
})
public class China {
    @Id(autoincrement = true)
    @Property(nameInDb = "id")
    private Long id;
    //文字
    @NotNull
    @Property(nameInDb = "pinyin")
    private String pinyin;
    //文字
    @NotNull
    @Property(nameInDb = "word")
    private String word;
    //优先级
    @Property(nameInDb = "score")
    private Double score;
@Generated(hash = 565769517)
public China(Long id, @NotNull String pinyin, @NotNull String word,
        Double score) {
    this.id = id;
    this.pinyin = pinyin;
    this.word = word;
    this.score = score;
}
@Generated(hash = 1241270372)
public China() {
}
public Long getId() {
    return this.id;
}
public void setId(Long id) {
    this.id = id;
}
public String getPinyin() {
    return this.pinyin;
}
public void setPinyin(String pinyin) {
    this.pinyin = pinyin;
}
public String getWord() {
    return this.word;
}
public void setWord(String word) {
    this.word = word;
}
public Double getScore() {
    return this.score;
}
public void setScore(Double score) {
    this.score = score;
}




}
