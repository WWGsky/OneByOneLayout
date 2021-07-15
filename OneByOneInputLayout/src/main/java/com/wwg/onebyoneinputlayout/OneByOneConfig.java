package com.wwg.onebyoneinputlayout;

import android.view.Gravity;
import android.view.ViewGroup;

public class OneByOneConfig {

    /**
     * 输入框个数
     */
    private int inputNum = 6;
    /**
     * 输入框宽度
     */
    private int inputWidth = ViewGroup.LayoutParams.WRAP_CONTENT;
    /**
     * 输入框高度
     */
    private int inputHeight = ViewGroup.LayoutParams.WRAP_CONTENT;
    /**
     * 输入框最大输入位数
     */
    private int inputMaxLength = 1;
    /**
     * 输入框背景
     */
    private int inputBackGround = 0;
    /**
     * 输入框光标资源
     * Api等级大于或等于29时,才会生效
     */
    private int inputCursorDrawable = 0;
    /**
     * 输入框输入模式
     */
    private int inputType;
    /**
     * 输入框对其方式
     */
    private int inputGravity = Gravity.CENTER;
    /**
     * 输入监听
     */
    private OneByOneInputListener inputListener;

    public int getInputNum() {
        return inputNum;
    }

    public void setInputNum(int inputNum) {
        this.inputNum = inputNum;
    }

    public int getInputWidth() {
        return inputWidth;
    }

    public void setInputWidth(int inputWidth) {
        this.inputWidth = inputWidth;
    }

    public int getInputHeight() {
        return inputHeight;
    }

    public void setInputHeight(int inputHeight) {
        this.inputHeight = inputHeight;
    }

    public int getInputMaxLength() {
        return inputMaxLength;
    }

    public void setInputMaxLength(int inputMaxLength) {
        this.inputMaxLength = inputMaxLength;
    }

    public int getInputBackGround() {
        return inputBackGround;
    }

    public void setInputBackGround(int inputBackGround) {
        this.inputBackGround = inputBackGround;
    }

    public int getInputCursorDrawable() {
        return inputCursorDrawable;
    }

    public void setInputCursorDrawable(int inputCursorDrawable) {
        this.inputCursorDrawable = inputCursorDrawable;
    }

    public int getInputType() {
        return inputType;
    }

    public void setInputType(int inputType) {
        this.inputType = inputType;
    }

    public int getInputGravity() {
        return inputGravity;
    }

    public void setInputGravity(int inputGravity) {
        this.inputGravity = inputGravity;
    }

    public OneByOneInputListener getInputListener() {
        return inputListener;
    }

    public void setInputListener(OneByOneInputListener inputListener) {
        this.inputListener = inputListener;
    }

}
