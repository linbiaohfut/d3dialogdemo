package com.linzhuowei.d3dialog;

import android.content.Context;
import android.support.annotation.ColorRes;
import android.support.annotation.DrawableRes;
import android.support.v4.content.ContextCompat;
import android.text.TextUtils;
import android.view.View;
import android.widget.TextView;

/**
 * Created by linzhuowei on 2017/4/7.
 */
public class D3AlertDialog extends D3BaseDialog {
    private TextView mLeftBtn;
    private TextView mRightBtn;

    private Builder mBuilder;
    private D3AlertDialog(Builder builder) {
        super(builder.getContext());
        mBuilder = builder;
        initDialog();
    }

    private void initDialog() {
        changeWindowSize(mBuilder.getWidth(),mBuilder.getHeight());
        mDialog.setCanceledOnTouchOutside(mBuilder.isTouchOutside());
        if (mBuilder.getTopIcon() != 0) {
            setTopIcon(mBuilder.getTopIcon());
        }else{
            setTopIcon(R.drawable.ic_ask);
        }
        if (mBuilder.getTitleVisible() && !TextUtils.isEmpty(mBuilder.getTitleText())) {

            mTitleView.setVisibility(View.VISIBLE);
        } else {

            mTitleView.setVisibility(View.GONE);
        }

        mTitleView.setText(mBuilder.getTitleText());
        mTitleView.setTextColor(mBuilder.getTitleTextColor());
        mTitleView.setTextSize(mBuilder.getTitleTextSize());
        if ( !TextUtils.isEmpty(mBuilder.getContentText())) {
            setMessage(mBuilder.getContentText());
        }

        mMessageView.setTextColor(mBuilder.getContentTextColor());
        mMessageView.setTextSize(mBuilder.getContentTextSize());
        mLeftBtn.setText(mBuilder.getLeftButtonText());
        mLeftBtn.setTextColor(mBuilder.getLeftButtonTextColor());
        mLeftBtn.setTextSize(mBuilder.getButtonTextSize());
        mRightBtn.setText(mBuilder.getRightButtonText());
        mRightBtn.setTextColor(mBuilder.getRightButtonTextColor());
        mRightBtn.setTextSize(mBuilder.getButtonTextSize());

        mLeftBtn.setOnClickListener(this);
        mRightBtn.setOnClickListener(this);

    }
    
    @Override
    protected void handleCustomView(View view) {
        mLeftBtn = findView(R.id.alert_dialog_leftbtn);
        mRightBtn = findView(R.id.alert_dialog_rightbtn);
    }

    @Override
    protected int getCustomLayout() {
        return R.layout.dialog_alert;
    }

    @Override
    public void onClick(View view) {

        int i = view.getId();
        if (i == R.id.alert_dialog_leftbtn && mBuilder.getListener() != null) {
            mBuilder.getListener().clickLeftButton(mLeftBtn);
            dismiss();
            return;
        }

        if (i == R.id.alert_dialog_rightbtn && mBuilder.getListener() != null) {
            mBuilder.getListener().clickRightButton(mRightBtn);
            dismiss();
            return;
        }

    }

    public static class Builder {

        private String titleText;
        private int titleTextColor;
        private int titleTextSize;
        private String contentText;
        private int contentTextColor;
        private int contentTextSize;
        private String leftButtonText;
        private int leftButtonTextColor;
        private String rightButtonText;
        private int rightButtonTextColor;
        private int buttonTextSize;
        private boolean isTitleVisible;
        private boolean isTouchOutside;
        private float height;
        private float width;
        private boolean isCheckBoxVisible;
        private DialogOnClickListener listener;
        private Context mContext;
        private int topIconId;
        public Builder(Context context) {

            mContext = context;
            titleText = "";
            titleTextColor = ContextCompat.getColor(mContext, R.color.black);
            contentText = "";
            contentTextColor = ContextCompat.getColor(mContext, R.color.black_light);
            leftButtonText = "取消";
            leftButtonTextColor = ContextCompat.getColor(mContext, R.color.secondaryText);
            rightButtonText = "确定";
            rightButtonTextColor = ContextCompat.getColor(mContext, R.color.black_light);
            listener = null;
            isTitleVisible = true;
            isTouchOutside = false;
            height = 0.21f;
            width = 0.73f;
            titleTextSize = 16;
            contentTextSize = 14;
            buttonTextSize = 14;
            isCheckBoxVisible = false;
            topIconId = 0;
        }

        public Context getContext() {
            return mContext;
        }
        /**
         * 设置顶部图片
         * @param iconRes
         */
        public void setTopIcon(@DrawableRes int iconRes){
            topIconId = iconRes;
        }
        public int getTopIcon(){
            return topIconId ;
        }
        public String getTitleText() {
            return titleText;
        }

        public Builder setTitleText(String titleText) {
            this.titleText = titleText;
            return this;
        }

        public int getTitleTextColor() {
            return titleTextColor;
        }

        public Builder setTitleTextColor(@ColorRes int titleTextColor) {
            this.titleTextColor = ContextCompat.getColor(mContext, titleTextColor);
            return this;
        }

        public String getContentText() {
            return contentText;
        }

        public Builder setContentText(String contentText) {
            this.contentText = contentText;
            return this;
        }

        public int getContentTextColor() {
            return contentTextColor;
        }

        public Builder setContentTextColor(@ColorRes int contentTextColor) {
            this.contentTextColor = ContextCompat.getColor(mContext, contentTextColor);
            return this;
        }

        public String getLeftButtonText() {
            return leftButtonText;
        }

        public Builder setLeftButtonText(String leftButtonText) {
            this.leftButtonText = leftButtonText;
            return this;
        }

        public int getLeftButtonTextColor() {
            return leftButtonTextColor;
        }

        public Builder setLeftButtonTextColor(@ColorRes int leftButtonTextColor) {
            this.leftButtonTextColor = ContextCompat.getColor(mContext, leftButtonTextColor);
            return this;
        }

        public String getRightButtonText() {
            return rightButtonText;
        }

        public Builder setRightButtonText(String rightButtonText) {
            this.rightButtonText = rightButtonText;
            return this;
        }

        public int getRightButtonTextColor() {
            return rightButtonTextColor;
        }

        public Builder setRightButtonTextColor(@ColorRes int rightButtonTextColor) {
            this.rightButtonTextColor = ContextCompat.getColor(mContext, rightButtonTextColor);
            return this;
        }

        public boolean getTitleVisible() {
            return isTitleVisible;
        }

        public Builder setTitleVisible(boolean titleVisible) {
            isTitleVisible = titleVisible;
            return this;
        }

        public boolean getCheckBoxVisible() {
            return isCheckBoxVisible;
        }

        public Builder setCheckBoxVisible(boolean checkBoxVisible) {
            isCheckBoxVisible = checkBoxVisible;
            return this;
        }

        public boolean isTouchOutside() {
            return isTouchOutside;
        }

        public Builder setCanceledOnTouchOutside(boolean touchOutside) {
            isTouchOutside = touchOutside;
            return this;
        }

        public float getHeight() {
            return height;
        }

        public Builder setHeight(float height) {
            this.height = height;
            return this;
        }

        public float getWidth() {
            return width;
        }

        public Builder setWidth(float width) {
            this.width = width;
            return this;
        }

        public int getContentTextSize() {
            return contentTextSize;
        }

        public Builder setContentTextSize(int contentTextSize) {
            this.contentTextSize = contentTextSize;
            return this;
        }

        public int getTitleTextSize() {
            return titleTextSize;
        }

        public Builder setTitleTextSize(int titleTextSize) {
            this.titleTextSize = titleTextSize;
            return this;
        }

        public int getButtonTextSize() {
            return buttonTextSize;
        }

        public Builder setButtonTextSize(int buttonTextSize) {
            this.buttonTextSize = buttonTextSize;
            return this;
        }

        public DialogOnClickListener getListener() {
            return listener;
        }

        public Builder setOnclickListener(DialogOnClickListener listener) {
            this.listener = listener;
            return this;
        }


        public D3AlertDialog build() {

            return new D3AlertDialog(this);
        }
    }

    public interface DialogOnClickListener {

        void clickLeftButton(View view);

        void clickRightButton(View view);
    }
}
