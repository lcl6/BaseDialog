package com.lcl.greendao.bean;


import com.ccc.danmuibrary.BaseDanmakuData;

import org.greenrobot.greendao.annotation.Entity;
import org.greenrobot.greendao.annotation.Generated;
import org.greenrobot.greendao.annotation.Id;

/**
 * Created by liancl on 2017/11/28.
 */
@Entity(nameInDb = "danmaku")
public class DanmakuInfo extends BaseDanmakuData {
    @Id(autoincrement = true)
    private Long id;
    private int type;
    private String content;
    private long time;
    private float textSize;
    private int textColor;
    // 用户名
    private String userName;
    // 对应一个视频
    private String vid;
    @Generated(hash = 182389505)
    public DanmakuInfo(Long id, int type, String content, long time, float textSize,
            int textColor, String userName, String vid) {
        this.id = id;
        this.type = type;
        this.content = content;
        this.time = time;
        this.textSize = textSize;
        this.textColor = textColor;
        this.userName = userName;
        this.vid = vid;
    }
    @Generated(hash = 2104047588)
    public DanmakuInfo() {
    }
    public Long getId() {
        return this.id;
    }
    public void setId(Long id) {
        this.id = id;
    }
    public int getType() {
        return this.type;
    }
    public void setType(int type) {
        this.type = type;
    }
    public String getContent() {
        return this.content;
    }
    public void setContent(String content) {
        this.content = content;
    }
    public long getTime() {
        return this.time;
    }
    public void setTime(long time) {
        this.time = time;
    }
    public float getTextSize() {
        return this.textSize;
    }
    public void setTextSize(float textSize) {
        this.textSize = textSize;
    }
    public int getTextColor() {
        return this.textColor;
    }
    public void setTextColor(int textColor) {
        this.textColor = textColor;
    }
    public String getUserName() {
        return this.userName;
    }
    public void setUserName(String userName) {
        this.userName = userName;
    }
    public String getVid() {
        return this.vid;
    }
    public void setVid(String vid) {
        this.vid = vid;
    }

}
