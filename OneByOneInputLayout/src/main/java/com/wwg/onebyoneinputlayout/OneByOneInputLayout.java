package com.wwg.onebyoneinputlayout;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.os.Build;
import android.text.Editable;
import android.text.InputFilter;
import android.text.InputType;
import android.text.TextWatcher;
import android.util.AttributeSet;
import android.util.Log;
import android.view.Gravity;
import android.view.ViewGroup;
import android.widget.LinearLayout;

import androidx.annotation.Nullable;
import androidx.appcompat.widget.AppCompatEditText;

import java.util.ArrayList;

public class OneByOneInputLayout extends LinearLayout {

    private String TAG = "OneByOneInputLayout";

    private OneByOneConfig config;
    private Context context;

    private ArrayList<AppCompatEditText> inputViews;

    public OneByOneInputLayout(Context context) {
        this(context,null);
    }

    public OneByOneInputLayout(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        init(context,attrs);
    }

    public OneByOneInputLayout(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init(context,attrs);
    }

    private void init(Context context, @Nullable AttributeSet attrs){

        this.context = context;

        inputViews = new ArrayList<>();

        if (config == null){
            config = new OneByOneConfig();
        }

        if (attrs != null){

            TypedArray typedArray = context.obtainStyledAttributes(attrs, R.styleable.OneByOneInputLayout);
            for (int i = 0; i < typedArray.getIndexCount(); i++) {

                if (i == R.styleable.OneByOneInputLayout_inputNum){

                    config.setInputNum(typedArray.getInt(i,6));

                }else if (i == R.styleable.OneByOneInputLayout_inputWidth){

                    config.setInputWidth((int) typedArray.getDimension(i, ViewGroup.LayoutParams.WRAP_CONTENT));

                }else if (i == R.styleable.OneByOneInputLayout_inputHeight){

                    config.setInputHeight((int) typedArray.getDimension(i, ViewGroup.LayoutParams.WRAP_CONTENT));

                }else if (i == R.styleable.OneByOneInputLayout_inputMaxLength){

                    config.setInputMaxLength(typedArray.getInt(i, 1));

                }else if (i == R.styleable.OneByOneInputLayout_inputBackGround){

                    config.setInputBackGround(typedArray.getResourceId(i, 0));

                }else if (i == R.styleable.OneByOneInputLayout_inputCursorDrawable){

                    config.setInputCursorDrawable(typedArray.getResourceId(i, 0));

                }

            }

        }

    }

    private void drawView(){
        inputViews.clear();
        removeAllViews();

        for (int i = 0; i < config.getInputNum(); i++) {

            //创建一个外层的承载布局(利于权重时,实际输入框布局不变形)
            LinearLayout linearLayout = new LinearLayout(context);
            if (i == 0) {
                LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);
                linearLayout.setLayoutParams(layoutParams);
                linearLayout.setGravity(Gravity.CENTER);
            } else {
                LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams(0, ViewGroup.LayoutParams.WRAP_CONTENT, 1);
                linearLayout.setLayoutParams(layoutParams);
                linearLayout.setGravity(Gravity.END);
            }

            AppCompatEditText appCompatEditText = new AppCompatEditText(context);
            //设置布局
            LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams(
                    config.getInputWidth(),config.getInputHeight());

            appCompatEditText.setLayoutParams(layoutParams);
            //Api等级大于或等于29时
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.Q) {
                //设置光标
                appCompatEditText.setTextCursorDrawable(config.getInputCursorDrawable());
            }
            //消除背景
            appCompatEditText.setPadding(0, 0, 0, 0);
            //设置焦点改变时的背景
            appCompatEditText.setBackgroundResource(config.getInputBackGround());
            //设置输入模式
            appCompatEditText.setInputType(config.getInputType());
            //设置文本居中
            appCompatEditText.setGravity(Gravity.CENTER);
            //设置最大输入数
            appCompatEditText.setFilters(new InputFilter[]{new InputFilter.LengthFilter(1)});
            //将控件添加进集合
            inputViews.add(appCompatEditText);
            //将控件添加进承载布局
            linearLayout.addView(appCompatEditText);
            //将承载布局添加进根布局
            addView(linearLayout);
        }

        //设置输入框们的输入监听
        for (int i = 0; i < inputViews.size(); i++) {

            int finalI = i;

            AppCompatEditText appCompatEditText = inputViews.get(finalI);

            appCompatEditText.addTextChangedListener(new TextWatcher() {
                @Override
                public void beforeTextChanged(CharSequence s, int start, int count, int after) {

                }

                @Override
                public void onTextChanged(CharSequence s, int start, int before, int count) {

                }

                @Override
                public void afterTextChanged(Editable s) {

                    if (s.toString().trim().length() == config.getInputMaxLength()) {
                        //完成了文本输入
                        if (finalI == inputViews.size() - 1) {
                            //当前为最后一个输入框,清除当前输入框焦点
                            appCompatEditText.clearFocus();
                            //获取全部的输入内容
                            if (config.getInputListener() == null) {
                                Log.e(TAG,"未对控件设置监听");
                            } else {
                                Log.i(TAG,"输入内容" + getInputInfo());
                                config.getInputListener().getInputInfo(getInputInfo());
                            }
                        } else {
                            //不是最后一个输入框时,将焦点转移到下一个输入框
                            appCompatEditText.clearFocus();
                            inputViews.get(finalI + 1).requestFocus();
                        }
                    }else if (s.toString().trim().length() == 0){
                        //清除了文本,且当前输入框持有焦点
                        if (finalI != 0 && appCompatEditText.hasFocus()) {
                            //将焦点转移到前一个输入框
                            appCompatEditText.requestFocus();
                            inputViews.get(finalI - 1).requestFocus();
                            //清除此输入框后面的输入框里的值
                            for (int j = 1; j < inputViews.size() - finalI; j++) {

                                inputViews.get(finalI + j).setText("");

                            }
                        }

                    }

                }

            });

        }
    }

    /**
     * 获取设置类
     * @return
     */
    public OneByOneConfig getConfig(){
        return config;
    }

    /**
     * 设置输入回调监听
     * @param listener
     */
    public void setInputListener(OneByOneInputListener listener){

        config.setInputListener(listener);

        drawView();

    }

    /**
     * 获取全部输入的内容
     */
    public String getInputInfo() {

        String info = "";

        for (int i = 0; i < inputViews.size(); i++) {

            info = info + inputViews.get(i).getText().toString().trim();

        }

        return info;

    }

    /**
     * 清除全部输入内容
     */
    public void clearInputInfo() {

        for (int i = 0; i < inputViews.size(); i++) {

            inputViews.get(i).setText("");

        }

    }

}
