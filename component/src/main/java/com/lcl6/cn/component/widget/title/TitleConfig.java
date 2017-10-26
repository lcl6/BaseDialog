package com.lcl6.cn.component.widget.title;

import android.graphics.drawable.Drawable;
import android.view.View;

/**
 * title 的配置类
 * Created by liancl on 2017/10/26.
 */

public final class TitleConfig {

    public static final int DEFAULT_TITLE_BAR_HEIGHT = 48;
    public static final int DEFAULT_ACTION_TEXT_SIZE = 15;
    public static final int DEFAULT_SUB_TEXT_SIZE = 12;
    public static final int DEFAULT_MAIN_TEXT_SIZE = 18;

    /**设置状态栏右边文字的颜色*/
    private int colorResId=-1;
    /**设置状态栏自定义标题*/
    private  View titleView;
    /**设置状态栏分割线颜色*/
    private  int divideColor;
    /**设置状态栏分割线高度*/
    private int dividerHeight;
    /**设置状态栏的高度*/
    private int titleHeight=TitleBar.dip2px(DEFAULT_TITLE_BAR_HEIGHT);
    /**设置状态栏是否沉浸*/
    private  boolean immersive;
    /**设置状态栏返回的图片*/
    private int resBackId;
    /**设置状态栏左边的文字*/
    private CharSequence charLeftTitle;
    /**设置状态栏左边的文字*/
    private String stringResId;
    /**设置状态栏左边的文字大小*/
    private float leftTextSize=DEFAULT_ACTION_TEXT_SIZE;
    /**设置状态栏左边的文字颜色*/
    private  int leftTextColor;
    /**设置状态栏左边的文字是否可见*/
    private boolean leftTextVsible;
    /**设置状态栏标题*/
    private CharSequence mainTitle;
    /**设置状态栏标题  设置多个参数 这个可以先放着*/
    private CharSequence subTitle;
    /**设置状态栏标题颜色*/
    private int mainTitleResId;
    /**设置状态栏标题大小*/
    private  float mainTitleSize=DEFAULT_MAIN_TEXT_SIZE;
    /**设置状态栏子标题背景*/
    private int subTitleBackRoundResId;
    /**设置状态栏子标题颜色*/
    private  int subTitleColorResId;
    /**设置状态栏子标题大小*/
    private  float subTitleTextSize=DEFAULT_SUB_TEXT_SIZE;
    /**添加右边的扩展view*/
    private TitleBar.ActionList actionList;
    /**添加右边的扩展view*/
    private TitleBar.Action action;
    /**设置状态栏分割线背景*/
    private  Drawable divideDrawable;
    /**设置子标题字体颜色*/
    private int subTitleColor;
    /**设置title背景颜色*/
    private  int titleBackgroundColor;

    public TitleConfig(Buidler buidler) {
        this.action=buidler.action;
        this.actionList=buidler.actionList;
        this.charLeftTitle=buidler.charLeftTitle;
        this.colorResId=buidler.colorResId;
        this.divideColor= buidler.divideColor;
        this.divideDrawable=buidler.divideDrawable;
        this.dividerHeight=buidler.dividerHeight;
        this.immersive=buidler.immersive;
        this.leftTextColor=buidler.leftTextColor;
        this.leftTextVsible=buidler.leftTextVsible;
        this.mainTitle=buidler.mainTitle;
        this.mainTitleResId=buidler.mainTitleResId;
        this.leftTextSize=buidler.leftTextSize;
        this.resBackId=buidler.resBackId;
        this.stringResId= buidler.stringResId;
        this.subTitle=buidler.subTitle;
        this.subTitleBackRoundResId= buidler.subTitleBackRoundResId;
        this.subTitleTextSize=buidler.subTitleTextSize;
        this.subTitleColorResId=buidler.subTitleColorResId;
        this.titleHeight=buidler.titleHeight;
        this.titleView=buidler.titleView;
        this.mainTitleSize=buidler.mainTitleSize;
        this.subTitleColor=buidler.subTitleColor;
        this.titleBackgroundColor=buidler.titleBackgroundColor;
    }

    public int getSubTitleColor() {
        return subTitleColor;
    }

    public int getTitleBackgroundColor() {
        return titleBackgroundColor;
    }

    public int getColorResId() {
        return colorResId;
    }

    public View getTitleView() {
        return titleView;
    }

    public int getDivideColor() {
        return divideColor;
    }

    public int getDividerHeight() {
        return dividerHeight;
    }

    public int getTitleHeight() {
        return titleHeight;
    }

    public boolean isImmersive() {
        return immersive;
    }

    public int getResBackId() {
        return resBackId;
    }

    public CharSequence getCharLeftTitle() {
        return charLeftTitle;
    }

    public String getStringResId() {
        return stringResId;
    }

    public float getLeftTextSize() {
        return leftTextSize;
    }

    public int getLeftTextColor() {
        return leftTextColor;
    }

    public boolean isLeftTextVsible() {
        return leftTextVsible;
    }

    public CharSequence getMainTitle() {
        return mainTitle;
    }

    public CharSequence getSubTitle() {
        return subTitle;
    }

    public int getMainTitleResId() {
        return mainTitleResId;
    }

    public float getMainTitleSize() {
        return mainTitleSize;
    }

    public int getSubTitleBackRoundResId() {
        return subTitleBackRoundResId;
    }

    public int getSubTitleColorResId() {
        return subTitleColorResId;
    }

    public float getSubTitleTextSize() {
        return subTitleTextSize;
    }

    public TitleBar.ActionList getActionList() {
        return actionList;
    }

    public TitleBar.Action getAction() {
        return action;
    }

    public Drawable getDivideDrawable() {
        return divideDrawable;
    }

    public static class Buidler{
        /**设置状态栏右边文字的颜色*/
        private int colorResId=-1;
        /**设置状态栏自定义标题*/
        private  View titleView;
        /**设置状态栏分割线颜色*/
        private  int divideColor;
        /**设置状态栏分割线高度*/
        private int dividerHeight;
        /**设置状态栏的高度*/
        private int titleHeight=TitleBar.dip2px(DEFAULT_TITLE_BAR_HEIGHT);
        /**设置状态栏是否沉浸*/
        private  boolean immersive;
        /**设置状态栏返回的图片*/
        private int resBackId;
        /**设置状态栏左边的文字*/
        private CharSequence charLeftTitle;
        /**设置状态栏左边的文字*/
        private String stringResId;
        /**设置状态栏左边的文字大小*/
        private float leftTextSize=DEFAULT_ACTION_TEXT_SIZE;
        /**设置状态栏左边的文字颜色*/
        private  int leftTextColor;
        /**设置状态栏左边的文字是否可见*/
        private boolean leftTextVsible=true;
        /**设置状态栏标题*/
        private CharSequence mainTitle;
        /**设置状态栏标题  设置多个参数 这个可以先放着*/
        private CharSequence subTitle;
        /**设置状态栏标题颜色*/
        private int mainTitleResId;
        /**设置状态栏标题大小*/
        private  float mainTitleSize=DEFAULT_MAIN_TEXT_SIZE;
        /**设置状态栏子标题背景*/
        private int subTitleBackRoundResId;
        /**设置状态栏子标题颜色*/
        private  int subTitleColorResId;
        /**设置状态栏子标题大小*/
        private  float subTitleTextSize=DEFAULT_SUB_TEXT_SIZE;
        /**添加右边的扩展view*/
        private TitleBar.ActionList actionList;
        /**添加右边的扩展view*/
        private TitleBar.Action action;
        /**设置状态栏分割线背景*/
        private  Drawable divideDrawable;
        /**设置子标题字体颜色*/
        private int subTitleColor;
        /**设置标题栏颜色*/
        private int titleBackgroundColor;


        public Buidler() {
        }

        public Buidler(TitleConfig titleConfig) {
            this.action=titleConfig.action;
            this.actionList=titleConfig.actionList;
            this.charLeftTitle=titleConfig.charLeftTitle;
            this.colorResId=titleConfig.colorResId;
            this.divideColor= titleConfig.divideColor;
            this.divideDrawable=titleConfig.divideDrawable;
            this.dividerHeight=titleConfig.dividerHeight;
            this.immersive=titleConfig.immersive;
            this.leftTextColor=titleConfig.leftTextColor;
            this.leftTextVsible=titleConfig.leftTextVsible;
            this.mainTitle=titleConfig.mainTitle;
            this.mainTitleResId=titleConfig.mainTitleResId;
            this.leftTextSize=titleConfig.leftTextSize;
            this.resBackId=titleConfig.resBackId;
            this.stringResId= titleConfig.stringResId;
            this.subTitle=titleConfig.subTitle;
            this.subTitleBackRoundResId= titleConfig.subTitleBackRoundResId;
            this.subTitleTextSize=titleConfig.subTitleTextSize;
            this.subTitleColorResId=titleConfig.subTitleColorResId;
            this.titleHeight=titleConfig.titleHeight;
            this.titleView=titleConfig.titleView;
            this.mainTitleSize=titleConfig.mainTitleSize;
            this.titleBackgroundColor=titleConfig.titleBackgroundColor;
        }

        /**设置状态栏右边文字的颜色*/
        public Buidler setActionTextColor(int colorResId) {
            this.colorResId = colorResId;
            return this;
        }

        /**设置状态栏自定义标题*/
        public Buidler setCustomTitle(View titleView) {
            if(titleView!=null){
                this.titleView=titleView;
            }
            return this;
        }

        /**设置状态栏分割线颜色*/
        public Buidler setDividerColor(int color) {
            this.divideColor=color;
            return this;
        }
        /**设置状态栏分割线高度*/
        public Buidler setDividerHeight(int dividerHeight) {
            this.dividerHeight= dividerHeight;
            return this;
        }
        /**设置状态栏的高度*/
        public Buidler setHeight(int height) {
            this.titleHeight= height;
            return this;
        }
        /**设置状态栏是否沉浸*/
        public Buidler setImmersive(boolean immersive) {
            this.immersive= immersive;
            return this;
        }
        /**设置状态栏返回的图片*/
        public Buidler setLeftImageResource(int resId) {
            this.resBackId=resId;
            return this;
        }
        /**设置状态栏左边的文字*/
        public Buidler setLeftText(CharSequence title) {
            this.charLeftTitle=title;
            return this;
        }
        /**设置状态栏左边的文字*/
        public Buidler setLeftText(String stringResId) {
            this.stringResId=stringResId;
            return this;
        }
        /**设置状态栏左边的文字大小*/
        public Buidler setLeftTextSize(float size) {
            this.leftTextSize=size;
            return this;
        }

        /**设置状态栏左边的文字颜色*/
        public Buidler setLeftTextColor(int color) {
            this.leftTextColor=color;
            return this;
        }

        /**设置状态栏左边的文字是否可见*/
        public Buidler setLeftVisible(boolean visible) {
            this.leftTextVsible=visible;
            return this;
        }
        /**设置状态栏标题*/
        public Buidler setTitle(CharSequence title) {
            this.mainTitle=title;
            return this;
        }

        /**设置状态栏标题  这个方法暂时放着*/
        private Buidler setTitle(CharSequence title, CharSequence subTitle, int orientation) {
            return this;
        }

        /**设置状态栏标题颜色*/
        public Buidler setTitleColor(int resid) {
            this.mainTitleResId=resid;
            return this;
        }
        /**设置状态栏标题大小*/
        public Buidler setTitleSize(float size) {
            this.mainTitleSize= size;
            return this;
        }
        /**设置状态栏标题背景*/
        public Buidler setTitleBackground(int resid) {
            subTitleBackRoundResId=resid;
            return this;
        }

        /**设置状态栏子标题大小*/
        public Buidler setSubTitleSize(float size) {
            this.subTitleTextSize=size;
            return this;
        }
        /**添加右边的扩展view*/
        public Buidler addActions(TitleBar.ActionList actionList) {
            this.actionList=actionList;
            return this;
        }
        /**设置状态栏分割线背景*/
        public Buidler setDivider(Drawable drawable) {
            this.divideDrawable=drawable;
            return this;
        }

        /**设置状态栏子标题颜色*/
        public Buidler setSubTitleColor(int resid) {
            this.subTitleColor=resid;
            return this;
        }
        /**设置标题栏颜色*/
        public Buidler setBackgroundColor(int resid) {
            this.titleBackgroundColor=resid;
            return this;
        }



        public TitleConfig builde(){
            return new TitleConfig(this);
        }

  }

}
