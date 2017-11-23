package com.lcl6.cn.basedialog.bean.damaku;

/**
 * Created by liancl on 2017/11/23.
 */

public class DanmakuInfo extends com.dl7.player.danmaku.BaseDanmakuData {

    private int type;
    private String content;
    private long time;
    private float textSize;
    private int textColor;
    // 用户名
    private String userName;
    // 对应一个视频
    private String vid;

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public long getTime() {
        return time;
    }

    public void setTime(long time) {
        this.time = time;
    }

    public float getTextSize() {
        return textSize;
    }

    public void setTextSize(float textSize) {
        this.textSize = textSize;
    }

    public int getTextColor() {
        return textColor;
    }

    public void setTextColor(int textColor) {
        this.textColor = textColor;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getVid() {
        return vid;
    }

    public void setVid(String vid) {
        this.vid = vid;
    }
}
