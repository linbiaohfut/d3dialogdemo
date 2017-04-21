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
public class D3InfoDialog extends D3BaseDialog {

    private TextView mInfoIKnowedBtn;
    private Builder mBuilder;

    private D3InfoDialog(Builder builder) {
        super(builder.getContext());
        mBuilder = builder;
        initDialog();

    }
    private void initDialog() {
        changeWindowSize(mBuilder.getWidth(),mBuilder.getHeight());
        mDialog.setOnDismissListener(this);
        mDialog.setCanceledOnTouchOutside(mBuilder.isTouchOutside());

        if (mBuilder.getTitleVisible() && !TextUtils.isEmpty(mBuilder.getTitleText())) {
            mTitleView.setVisibility(View.VISIBLE);
        } else {
            mTitleView.setVisibility(View.GONE);
        }

        mTitleView.setText(mBuilder.getTitleText());
        mTitleView.setTextColor(mBuilder.getTitleTextColor());
        mTitleView.setTextSize(mBuilder.getTitleTextSize());
        if (mBuilder.getTitleVisible() && !TextUtils.isEmpty(mBuilder.getContentText())) {
            mMessageView.setVisibility(View.VISIBLE);
        } else {
            mMessageView.setVisibility(View.GONE);
        }

        if (mBuilder.getTopIcon() != 0) {
            setTopIcon(mBuilder.getTopIcon());
        }else{
            setTopIcon(R.drawable.ic_successed);
        }
        mMessageView.setText(mBuilder.getContentText());
        mMessageView.setTextColor(mBuilder.getContentTextColor());
        mMessageView.setTextSize(mBuilder.getContentTextSize());

        mInfoIKnowedBtn.setText(mBuilder.getButtonText());
        mInfoIKnowedBtn.setTextColor(mBuilder.getButtonTextColor());
        mInfoIKnowedBtn.setTextSize(mBuilder.getButtonTextSize());
        if (mBuilder.getButtonBackgroundResource() != 0) {
            mInfoIKnowedBtn.setBackgroundResource(mBuilder.getButtonBackgroundResource());
        }
        mInfoIKnowedBtn.setOnClickListener(this);
    }
    @Override
    protected void handleCustomView(View view) {
        mInfoIKnowedBtn = findView(R.id.dialog_info_btn);
    }

    @Override
    protected int getCustomLayout() {
        return R.layout.dialog_info;
    }

    @Override
    public void onClick(View view) {
        int i = view.getId();
        if (i == R.id.dialog_info_btn ) {
            if (mBuilder.getListener() != null) {
                mBuilder.getListener().clickIKnowedButton(view);
            }
            dismiss();
        }
    }

    public static class Builder {
        private String titleText;
        private int titleTextColor;
        private int titleTextSize;
        private String contentText;
        private int contentTextColor;
        private int contentTextSize;
        private String buttonText;
        private int buttonTextColor;
        private int buttonTextSize;
        private boolean isTitleVisible;
        private boolean isTouchOutside;
        private float height;
        private float width;
        private int topIconId;
        private int buttonBackgroundResource;
        private OnClickInfoDialogListener listener;

        private Context mContext;
        public Builder(Context context) {

            mContext = context;
            titleText = "";
            titleTextColor = ContextCompat.getColor(mContext, R.color.black);
            contentText = "";
            contentTextColor = ContextCompat.getColor(mContext, R.color.black_light);
            buttonText = "知道了";
            buttonTextColor = ContextCompat.getColor(mContext, R.color.white);

            isTitleVisible = true;
            isTouchOutside = true;

            height = 0.28f;
            width = 0.8f;
            titleTextSize = 20;
            contentTextSize = 16;
            buttonTextSize = 14;
            topIconId = 0;
            buttonBackgroundResource = 0;
        }

        public Context getContext() {
            return mContext;
        }

        /**
         * 设置顶部图片
         * @param iconRes
         */
        public Builder setTopIcon(@DrawableRes int iconRes){
            topIconId = iconRes;
            return this;
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

        public String getButtonText() {
            return buttonText;
        }

        public Builder setButtonText(String buttonText) {
            this.buttonText = buttonText;
            return this;
        }

        public int getButtonTextColor() {
            return buttonTextColor;
        }

        public Builder setButtonTextColor(@ColorRes int buttonTextColor) {
            this.buttonTextColor = ContextCompat.getColor(mContext, buttonTextColor);
            return this;
        }


        public boolean getTitleVisible() {
            return isTitleVisible;
        }

        public Builder setTitleVisible(boolean titleVisible) {
            isTitleVisible = titleVisible;
            return this;
        }

        public boolean isTouchOutside() {
            return isTouchOutside;
        }

        public Builder setCanceledOnTouchOutside(boolean touchOutside) {
            isTouchOutside = touchOutside;
            return this;
        }

        public float getMinHeight() {
            return height;
        }

        public Builder setMinHeight(float height) {
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

        public float getHeight() {
            return height;
        }

        public void setHeight(float height) {
            this.height = height;
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

        public int getButtonBackgroundResource() {
            return buttonBackgroundResource;
        }
        public Builder setButtonBackgroundResource(@DrawableRes int resId) {
            buttonBackgroundResource = resId;
            return this;
        }

        public OnClickInfoDialogListener getListener() {
            return listener;
        }

        public Builder setOnclickListener(OnClickInfoDialogListener listener) {
            this.listener = listener;
            return this;
        }

        public D3InfoDialog build() {
            return new D3InfoDialog(this);
        }
    }

    public interface OnClickInfoDialogListener {

        void clickIKnowedButton(View view);//点击“我知道了”按钮
    }
}
