package com.input.qz.input.bean;

import org.greenrobot.greendao.annotation.Entity;
import org.greenrobot.greendao.annotation.Id;
import org.greenrobot.greendao.annotation.Index;
import org.greenrobot.greendao.annotation.NotNull;
import org.greenrobot.greendao.annotation.Generated;
import org.greenrobot.greendao.annotation.Property;

/**
 * 文字或者词组
 */
@Entity(
        createInDb = false,
        indexes = {
        @Index(value = "word ASC", unique = true)
})
public class English {
    @Id(autoincrement = true)
    @Property(nameInDb = "id")
    private Long id;
    //文字
    @NotNull
    @Property(nameInDb = "word")
    private String word;
    //优先级
    @Property(nameInDb = "score")
    private Double score;
@Generated(hash = 1402506792)
public English(Long id, @NotNull String word, Double score) {
    this.id = id;
    this.word = word;
    this.score = score;
}
@Generated(hash = 584193142)
public English() {
}
public Long getId() {
    return this.id;
}
public void setId(Long id) {
    this.id = id;
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
