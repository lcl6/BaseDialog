package com.lcl6.cn.basedialog.widget;

import android.content.Context;
import android.content.res.TypedArray;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.EditText;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.TextView;

import com.lcl6.cn.basedialog.R;

/**
 * Created by liancl on 2018/1/23.
 */

public class ChoiceLayout extends FrameLayout {

    TextView textTitle;
    TextView redStart;
    EditText edContent;

    public ChoiceLayout(@NonNull Context context) {
        super(context);
    }

    public ChoiceLayout(@NonNull Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        init(context, attrs);
    }

    public ChoiceLayout(@NonNull Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init(context, attrs);
    }

    public ChoiceLayout(@NonNull Context context, @Nullable AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
        init(context, attrs);
    }

    private void init(Context context, AttributeSet attrs) {
        TypedArray typedArray = context.obtainStyledAttributes(attrs, R.styleable.ChoiceLayout);
        boolean reStartVisiable = typedArray.getBoolean(R.styleable.ChoiceLayout_redstartvisiable,false);
        boolean drawablerightvisiable = typedArray.getBoolean(R.styleable.ChoiceLayout_drawablerightvisiable,false);

        String title = typedArray.getString(R.styleable.ChoiceLayout_title);
        String content = typedArray.getString(R.styleable.ChoiceLayout_content);
        View inflate = LayoutInflater.from(context).inflate(R.layout.lin_choice, this);
        textTitle = inflate.findViewById(R.id.tv_left_title);
        textTitle.setText(title);
        redStart= inflate.findViewById(R.id.tv_red_start);
        redStart.setVisibility(reStartVisiable?VISIBLE:GONE);
        edContent = inflate.findViewById(R.id.ed_right_content);
        edContent.setText(content);
        ImageView right = inflate.findViewById(R.id.img_right);
        right.setVisibility(drawablerightvisiable?VISIBLE:GONE);

    }

    public void setTextTitle(String title){
        textTitle.setText(title);
    }

    public void setTextContent(String content){
        edContent.setText(content);
    }

    public void setRedStartVisiable(boolean visiable){
        redStart.setVisibility(visiable?VISIBLE:GONE);
    }

}
