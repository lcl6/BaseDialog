package com.lcl6.cn.component.widget.title;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.res.Resources;
import android.graphics.drawable.Drawable;
import android.support.annotation.ColorRes;
import android.support.annotation.IdRes;
import android.text.TextUtils;
import android.util.AttributeSet;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.util.LinkedList;

import static com.lcl6.cn.component.widget.title.TitleConfig.DEFAULT_ACTION_TEXT_SIZE;

/**
 * 类描述：
 * 创建人：Bob
 * 创建时间：2015/9/25 11:36
 */
public class TitleBar extends ViewGroup implements View.OnClickListener {

    private static final String STATUS_BAR_HEIGHT_RES_NAME = "status_bar_height";

    /**左边返回按钮的文字*/
    private TextView mLeftText;
    /**右边的布局*/
    private LinearLayout mRightLayout;
    /**title 布局*/
    private LinearLayout mCenterLayout;
    /**标题文字*/
    private TextView mCenterText;
    /**子标题*/
    private TextView mSubTitleText;
    /**自定义的中间的title 布局*/
    private View mCustomCenterView;
    /**底下的分割线*/
    private View mDividerView;
    /**是否开启沉浸状态栏*/
    private boolean mImmersive;
    /**屏幕的宽度*/
    private int mScreenWidth;
    /**状态栏的高度*/
    private int mStatusBarHeight;
    private int mActionPadding;
    /**设置右边的pading*/
    private int mOutPadding;
    /**右边文字的颜色*/
    private int mActionTextColor;
    /**title 的高度*/
    private int mHeight;

    public TitleBar(Context context) {
        super(context);
        init(context);
    }

    public TitleBar(Context context, AttributeSet attrs) {
        super(context, attrs);
        init(context);
    }

    public TitleBar(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init(context);
    }

    private void init(Context context) {
        if (mImmersive) {
            mStatusBarHeight = getStatusBarHeight();
        }
        mActionPadding = dip2px(5);
        mOutPadding = dip2px(8);
//        mHeight = dip2px(DEFAULT_TITLE_BAR_HEIGHT);
        initView(context);
    }

    public void setTitleConfig(TitleConfig titleConfig){
        if(titleConfig==null){
            new Throwable("please set titleConfig first");
            return;
        }
        setActionTextColor(titleConfig.getColorResId());
        setCustomTitle(titleConfig.getTitleView());
        setDividerColor(titleConfig.getDivideColor());
        setDividerHeight(titleConfig.getDividerHeight());
        setHeight(titleConfig.getTitleHeight());
        setImmersive(titleConfig.isImmersive());
        setLeftImageResource(titleConfig.getResBackId());
        setLeftText(titleConfig.getCharLeftTitle());
        setLeftText(titleConfig.getStringResId());
        setLeftTextSize(titleConfig.getLeftTextSize());
        setLeftTextColor(titleConfig.getLeftTextColor());
        setLeftVisible(titleConfig.isLeftTextVsible());
        setTitle(titleConfig.getMainTitle());
        //这个方法先放着 带更新
//        setTitle();
        setTitleColor(titleConfig.getMainTitleResId());
        setTitleSize(titleConfig.getMainTitleSize());
        setTitleBackground(titleConfig.getMainTitleResId());
        setSubTitleSize(titleConfig.getSubTitleTextSize());
        addActions(titleConfig.getActionList());
        setDivider(titleConfig.getDivideDrawable());
        setBackgroundColor(titleConfig.getTitleBackgroundColor());
        removeAllActions(titleConfig.isRemoveRightView());
    }


    private void initView(Context context) {
        mLeftText = new TextView(context);
        mCenterLayout = new LinearLayout(context);
        mRightLayout = new LinearLayout(context);
        mDividerView = new View(context);

        LayoutParams layoutParams = new LayoutParams(LayoutParams.WRAP_CONTENT, LayoutParams.MATCH_PARENT);

//        mLeftText.setTextSize(TitleConfig.DEFAULT_ACTION_TEXT_SIZE);
        mLeftText.setSingleLine();
        mLeftText.setGravity(Gravity.CENTER_VERTICAL);
        mLeftText.setPadding(mOutPadding + mActionPadding, 0, mOutPadding, 0);

        mCenterText = new TextView(context);
        mSubTitleText = new TextView(context);
        mCenterLayout.addView(mCenterText);
        mCenterLayout.addView(mSubTitleText);

        mCenterLayout.setGravity(Gravity.CENTER);
//        mCenterText.setTextSize(TitleConfig.DEFAULT_MAIN_TEXT_SIZE);
        mCenterText.setSingleLine();
        mCenterText.setGravity(Gravity.CENTER);
        mCenterText.setEllipsize(TextUtils.TruncateAt.END);
//        mSubTitleText.setTextSize(TitleConfig.DEFAULT_SUB_TEXT_SIZE);
        mSubTitleText.setSingleLine();
        mSubTitleText.setGravity(Gravity.CENTER);
        mSubTitleText.setEllipsize(TextUtils.TruncateAt.END);

        mRightLayout.setPadding(mOutPadding, 0, mOutPadding, 0);

        addView(mLeftText, layoutParams);
        addView(mCenterLayout);
        addView(mRightLayout, layoutParams);
        addView(mDividerView, new LayoutParams(LayoutParams.MATCH_PARENT, 1));
    }

    /**设置状态栏是否沉浸*/
    private void setImmersive(boolean immersive) {
        mImmersive = immersive;
        if (mImmersive) {
            mStatusBarHeight = getStatusBarHeight();
        } else {
            mStatusBarHeight = 0;
        }
    }

    /**设置状态栏的高度*/
    private void setHeight(int height) {
        mHeight = height;
        setMeasuredDimension(getMeasuredWidth(), mHeight);
    }
    /**设置状态栏返回的图片*/
    private void setLeftImageResource(int resId) {
        mLeftText.setCompoundDrawablesWithIntrinsicBounds(resId, 0, 0, 0);
    }
    /**设置状态栏返回点击事件*/
    public void setLeftClickListener(OnClickListener l) {
        mLeftText.setOnClickListener(l);
    }
    /**设置状态栏左边的文字*/
    private void setLeftText(CharSequence title) {
        mLeftText.setText(title);
    }
    /**设置状态栏左边的文字*/
    private void setLeftText(String resid) {
        mLeftText.setText(resid);
    }
    /**设置状态栏左边的文字大小*/
    private void setLeftTextSize(float size) {
        mLeftText.setTextSize(size);
    }
    /**设置状态栏左边的文字颜色*/
    private void setLeftTextColor(int color) {
        mLeftText.setTextColor(color);
    }
    /**设置状态栏左边的文字是否可见*/
    private void setLeftVisible(boolean visible) {
        mLeftText.setVisibility(visible ? View.VISIBLE : View.GONE);
    }
    /**设置状态栏标题*/
    private void setTitle(CharSequence title) {
        if(title==null){
            return;
        }
        int index = title.toString().indexOf("\n");
        if (index > 0) {
            setTitle(title.subSequence(0, index), title.subSequence(index + 1, title.length()), LinearLayout.VERTICAL);
        } else {
            index = title.toString().indexOf("\t");
            if (index > 0) {
                setTitle(title.subSequence(0, index), "  " + title.subSequence(index + 1, title.length()), LinearLayout.HORIZONTAL);
            } else {
                mCenterText.setText(title);
                mSubTitleText.setVisibility(View.GONE);
            }
        }
    }
    /**设置状态栏标题*/
    private void setTitle(CharSequence title, CharSequence subTitle, int orientation) {
        mCenterLayout.setOrientation(orientation);
        mCenterText.setText(title);

        mSubTitleText.setText(subTitle);
        mSubTitleText.setVisibility(View.VISIBLE);
    }
    /**设置状态栏标题点击时间*/
    public void setCenterClickListener(OnClickListener l) {
        mCenterLayout.setOnClickListener(l);
    }
    /**设置状态栏标题*/
    @SuppressLint("ResourceType")
    public void setTitle(@IdRes int resid) {
        setTitle(getResources().getString(resid));
    }
    /**设置状态栏标题颜色*/
    @SuppressLint("ResourceAsColor")
    private void setTitleColor(@ColorRes int resid) {
        mCenterText.setTextColor(resid);
    }
    /**设置状态栏标题大小*/
    private void setTitleSize(float size) {
        mCenterText.setTextSize(size);
    }
    /**设置状态栏子标题背景*/
    @SuppressLint("ResourceType")
    private void setTitleBackground(@IdRes int resid) {
        if(resid<=0){
            return;
        }
        mCenterText.setBackgroundResource(resid);
    }
    /**设置状态栏子标题颜色*/
    private void setSubTitleColor(@IdRes int resid) {
        mSubTitleText.setTextColor(resid);
    }
    /**设置状态栏子标题大小*/
    private void setSubTitleSize(float size) {
        mSubTitleText.setTextSize(size);
    }
    /**设置状态栏自定义标题*/
    private void setCustomTitle(View titleView) {
        if (titleView == null) {
            mCenterText.setVisibility(View.VISIBLE);
            if (mCustomCenterView != null) {
                mCenterLayout.removeView(mCustomCenterView);
            }

        } else {
            if (mCustomCenterView != null) {
                mCenterLayout.removeView(mCustomCenterView);
            }
            LayoutParams layoutParams = new LayoutParams(LayoutParams.MATCH_PARENT, LayoutParams.MATCH_PARENT);
            mCustomCenterView = titleView;
            mCenterLayout.addView(titleView, layoutParams);
            mCenterText.setVisibility(View.GONE);
        }
    }
    /**设置状态栏分割线背景*/
    private void setDivider(Drawable drawable) {
        if(drawable==null){
            return;
        }
        mDividerView.setBackgroundDrawable(drawable);
    }
    /**设置状态栏分割线颜色*/
    @SuppressLint("ResourceAsColor")
    private void setDividerColor(@ColorRes  int color) {
        if(color==0){
            return;
        }
        mDividerView.setBackgroundColor(color);
    }
    /**设置状态栏分割线高度*/
    private void setDividerHeight(int dividerHeight) {
        if(dividerHeight<0){
            return;
        }
        mDividerView.getLayoutParams().height = dividerHeight;
    }
    /**设置状态栏右边文字的颜色*/
    private void setActionTextColor(int colorResId) {
        mActionTextColor = colorResId;
    }
    /**设置状态栏标题的点击事件*/
    public void setOnTitleClickListener(OnClickListener listener) {
        mCenterText.setOnClickListener(listener);
    }

    @Override
    public void onClick(View view) {
        final Object tag = view.getTag();
        if (tag instanceof Action) {
            final Action action = (Action) tag;
            action.performAction(view);
        }
    }
    /**添加右边的扩展view*/
    private void addActions(ActionList actionList) {
        if(actionList==null){
            return;
        }
        int actions = actionList.size();
        for (int i = 0; i < actions; i++) {
            addAction(actionList.get(i));
        }
    }
    /**添加右边的扩展view*/
    public View addAction(Action action) {
        final int index = mRightLayout.getChildCount();
        return addAction(action, index);
    }
    /**添加右边的扩展view*/
    public View addAction(Action action, int index) {
        LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(LayoutParams.WRAP_CONTENT,
                LayoutParams.MATCH_PARENT);
        View view = inflateAction(action);
        mRightLayout.addView(view, index, params);
        return view;
    }

    /**
     * 移除右边所有的view
     * Removes all action views from this action bar
     */
    public void removeAllActions(boolean remove) {
        if(remove){
            mRightLayout.removeAllViews();
        }
    }

    /**
     * 移除某个view
     * Remove a action from the action bar.
     * @param index position of action to remove
     */
    public void removeActionAt(int index) {
        mRightLayout.removeViewAt(index);
    }

    /**
     * 移除某个view
     * Remove a action from the action bar.
     * @param action The action to remove
     */
    public void removeAction(Action action) {
        int childCount = mRightLayout.getChildCount();
        for (int i = 0; i < childCount; i++) {
            View view = mRightLayout.getChildAt(i);
            if (view != null) {
                final Object tag = view.getTag();
                if (tag instanceof Action && tag.equals(action)) {
                    mRightLayout.removeView(view);
                }
            }
        }
    }

    /**
     * Returns the number of actions currently registered with the action bar.
     * @return action count
     */
    public int getActionCount() {
        return mRightLayout.getChildCount();
    }

    /**
     * Inflates a {@link View} with the given {@link Action}.
     * @param action the action to inflate
     * @return a view
     */
    private View inflateAction(Action action) {
        View view = null;
        if (TextUtils.isEmpty(action.getText())) {
            ImageView img = new ImageView(getContext());
            img.setImageResource(action.getDrawable());
            view = img;
        } else {
            TextView text = new TextView(getContext());
            text.setGravity(Gravity.CENTER);
            text.setText(action.getText());
            text.setTextSize(DEFAULT_ACTION_TEXT_SIZE);
            if (mActionTextColor != 0) {
                text.setTextColor(mActionTextColor);
            }
            view = text;
        }

        view.setPadding(mActionPadding, 0, mActionPadding, 0);
        view.setTag(action);
        view.setOnClickListener(this);
        return view;
    }

    /**根据action 获取view*/
    public View getViewByAction(Action action) {
        View view = findViewWithTag(action);
        return view;
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        int heightMode = MeasureSpec.getMode(heightMeasureSpec);
        int height;
        if (heightMode != MeasureSpec.EXACTLY) {
            height = mHeight + mStatusBarHeight;
            heightMeasureSpec = MeasureSpec.makeMeasureSpec(mHeight, MeasureSpec.EXACTLY);
        } else {
            height = MeasureSpec.getSize(heightMeasureSpec) + mStatusBarHeight;
        }
        mScreenWidth = MeasureSpec.getSize(widthMeasureSpec);
        measureChild(mLeftText, widthMeasureSpec, heightMeasureSpec);
        measureChild(mRightLayout, widthMeasureSpec, heightMeasureSpec);
        if (mLeftText.getMeasuredWidth() > mRightLayout.getMeasuredWidth()) {
            mCenterLayout.measure(
                    MeasureSpec.makeMeasureSpec(mScreenWidth - 2 * mLeftText.getMeasuredWidth(), MeasureSpec.EXACTLY)
                    , heightMeasureSpec);
        } else {
            mCenterLayout.measure(
                    MeasureSpec.makeMeasureSpec(mScreenWidth - 2 * mRightLayout.getMeasuredWidth(), MeasureSpec.EXACTLY)
                    , heightMeasureSpec);
        }
        measureChild(mDividerView, widthMeasureSpec, heightMeasureSpec);
        setMeasuredDimension(MeasureSpec.getSize(widthMeasureSpec), height);
    }

    @Override
    protected void onLayout(boolean changed, int l, int t, int r, int b) {
        mLeftText.layout(0, mStatusBarHeight, mLeftText.getMeasuredWidth(), mLeftText.getMeasuredHeight() + mStatusBarHeight);
        mRightLayout.layout(mScreenWidth - mRightLayout.getMeasuredWidth(), mStatusBarHeight,
                mScreenWidth, mRightLayout.getMeasuredHeight() + mStatusBarHeight);
        if (mLeftText.getMeasuredWidth() > mRightLayout.getMeasuredWidth()) {
            mCenterLayout.layout(mLeftText.getMeasuredWidth(), mStatusBarHeight,
                    mScreenWidth - mLeftText.getMeasuredWidth(), getMeasuredHeight());
        } else {
            mCenterLayout.layout(mRightLayout.getMeasuredWidth(), mStatusBarHeight,
                    mScreenWidth - mRightLayout.getMeasuredWidth(), getMeasuredHeight());
        }
        mDividerView.layout(0, getMeasuredHeight() - mDividerView.getMeasuredHeight(), getMeasuredWidth(), getMeasuredHeight());
    }

    public static int dip2px(int dpValue) {
        final float scale = Resources.getSystem().getDisplayMetrics().density;
        return (int) (dpValue * scale + 0.5f);
    }

    /**
     * 计算状态栏高度高度
     * getStatusBarHeight
     */
    public static int getStatusBarHeight() {
        return getInternalDimensionSize(Resources.getSystem(), STATUS_BAR_HEIGHT_RES_NAME);
    }

    private static int getInternalDimensionSize(Resources res, String key) {
        int result = 0;
        int resourceId = res.getIdentifier(key, "dimen", "android");
        if (resourceId > 0) {
            result = res.getDimensionPixelSize(resourceId);
        }
        return result;
    }


    /**
     * A {@link LinkedList} that holds a list of {@link Action}s.
     */
    @SuppressWarnings("serial")
    public static class ActionList extends LinkedList<Action> {
    }

    /**
     * Definition of an action that could be performed, along with a icon to
     * show.
     */
    public interface Action {
        String getText();
        int getDrawable();
        void performAction(View view);
    }

    public static abstract class ImageAction implements Action {
        private int mDrawable;

        public ImageAction(int drawable) {
            mDrawable = drawable;
        }

        @Override
        public int getDrawable() {
            return mDrawable;
        }

        @Override
        public String getText() {
            return null;
        }
    }

    public static abstract class TextAction implements Action {
        final private String mText;

        public TextAction(String text) {
            mText = text;
        }

        @Override
        public int getDrawable() {
            return 0;
        }

        @Override
        public String getText() {
            return mText;
        }
    }

}
