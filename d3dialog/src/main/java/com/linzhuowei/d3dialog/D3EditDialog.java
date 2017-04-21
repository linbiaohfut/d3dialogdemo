package com.linzhuowei.d3dialog;

import android.content.Context;
import android.content.DialogInterface;
import android.support.annotation.ColorRes;
import android.support.annotation.DrawableRes;
import android.support.v4.content.ContextCompat;
import android.text.Editable;
import android.text.InputFilter;
import android.text.InputType;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

/**
 * Created by linzhuowei on 2017/4/7.
 */
public class D3EditDialog extends D3BaseDialog {
    private EditText mTextInput;
    private TextView mErrorMessage;
    private TextView mLeftBtn;
    private TextView mRightBtn;

    private Builder mBuilder;

    private D3EditDialog(Builder builder) {
        super(builder.getContext());
        mBuilder = builder;
        initDialog();

    }

    private void initDialog() {
        changeWindowSize(mBuilder.getWidth(), mBuilder.getHeight());
        mDialog.setOnDismissListener(this);
        mDialog.setCanceledOnTouchOutside(mBuilder.isTouchOutside());
        if (mBuilder.getTopIcon() != 0) {
            setTopIcon(mBuilder.getTopIcon());
        } else {
            setTopIcon(R.drawable.ic_edit);
        }
        if (mBuilder.getTitleVisible() && !TextUtils.isEmpty(mBuilder.getTitleText())) {
            mTitleView.setVisibility(View.VISIBLE);
        } else {
            mTitleView.setVisibility(View.GONE);
        }

        mTitleView.setText(mBuilder.getTitleText());
        mTitleView.setTextColor(mBuilder.getTitleTextColor());
        mTitleView.setTextSize(mBuilder.getTitleTextSize());
        mTextInput.setText(mBuilder.getContentText());
        mTextInput.setInputType(mBuilder.getInputType());
        Log.e("TAG", "-------initDialog: length="+mBuilder.getContentText().length() );
        mTextInput.setSelection(mBuilder.getContentText().length());
        mTextInput.setTextColor(mBuilder.getContentTextColor());
        mTextInput.setTextSize(mBuilder.getContentTextSize());

        mLeftBtn.setText(mBuilder.getLeftButtonText());
        mLeftBtn.setTextColor(mBuilder.getLeftButtonTextColor());
        mLeftBtn.setTextSize(mBuilder.getButtonTextSize());
        mRightBtn.setText(mBuilder.getRightButtonText());
        mRightBtn.setTextColor(mBuilder.getRightButtonTextColor());
        mRightBtn.setTextSize(mBuilder.getButtonTextSize());

        mLeftBtn.setOnClickListener(this);
        mRightBtn.setOnClickListener(this);

        mTextInput.setHint(mBuilder.getHintText());
        mTextInput.setHintTextColor(mBuilder.getHintTextColor());
        if (mBuilder.getLines() != -1) {

            mTextInput.setLines(mBuilder.getLines());
        }
        if (mBuilder.getMaxLines() != -1) {

            mTextInput.setMaxLines(mBuilder.getMaxLines());
        }
        if (mBuilder.getMaxLength() != -1) {

            mTextInput.setFilters(new InputFilter[]{new InputFilter.LengthFilter(mBuilder.getMaxLength
                    ())});
        }
        mTextInput.addTextChangedListener(new TextWatcher(){
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                mErrorMessage.setVisibility(View.GONE);
                mErrorMessage.setText("");
            }

            @Override
            public void afterTextChanged(Editable s) {
                mTextInput.setSelection(mTextInput.getText().length());
            }
        });
    }

    public void setErrorMessage(String errorMessage) {
        if (!TextUtils.isEmpty(errorMessage)) {
            mErrorMessage.setVisibility(View.VISIBLE);
            mErrorMessage.setText(errorMessage);
        }
    }

    @Override
    protected int getCustomLayout() {
        return R.layout.dialog_text_input;
    }

    @Override
    protected void handleCustomView(View view) {
        mTextInput = findView(R.id.ld_text_input);
        mErrorMessage = findView(R.id.ld_error_message);
        mLeftBtn = findView(R.id.edit_dialog_leftbtn);
        mRightBtn = findView(R.id.edit_dialog_rightbtn);
    }


    @Override
    public void onDismiss(DialogInterface dialog) {
        mTextInput.setText("");
    }

    @Override
    public void onClick(View view) {
        int i = view.getId();
        if (i == R.id.edit_dialog_leftbtn && mBuilder.getListener() != null) {
            if (mBuilder.getListener().clickLeftButton(mLeftBtn, mTextInput.getText().toString())) {
                dismiss();
            }
            return;
        }

        if (i == R.id.edit_dialog_rightbtn && mBuilder.getListener() != null) {
            if (mBuilder.getListener().clickRightButton(mRightBtn, mTextInput.getText().toString())) {
                dismiss();
            }
            return;
        }
    }

    public interface OnClickEditDialogListener {

        boolean clickLeftButton(View view, String editText);

        boolean clickRightButton(View view, String editText);

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
        private int lineColor;
        private int lines;
        private int maxLines;
        private int maxLength;
        private boolean isTitleVisible;
        private boolean isTouchOutside;
        private float height;
        private float width;
        private String hintText;
        private int hintTextColor;
        private int inputType;
        private OnClickEditDialogListener listener;
        private int topIconId;
        private Context mContext;

        public Builder(Context context) {

            mContext = context;
            titleText = "";
            titleTextColor = ContextCompat.getColor(mContext, R.color.black);
            contentText = "";
            contentTextColor = ContextCompat.getColor(mContext, R.color.black_light);
            leftButtonText = "取消";
            leftButtonTextColor = ContextCompat.getColor(mContext, R.color.black_light);
            rightButtonText = "确定";
            rightButtonTextColor = ContextCompat.getColor(mContext, R.color.black_light);
            lineColor = ContextCompat.getColor(mContext, R.color.black_light);
            listener = null;
            isTitleVisible = true;
            isTouchOutside = true;
            hintText = "";
            hintTextColor = ContextCompat.getColor(mContext, R.color.gray);
            lines = -1;
            maxLines = -1;
            maxLength = -1;
            height = 0.28f;
            width = 0.8f;
            titleTextSize = 20;
            contentTextSize = 16;
            buttonTextSize = 14;
            inputType = InputType.TYPE_CLASS_TEXT | InputType.TYPE_TEXT_FLAG_MULTI_LINE;
            topIconId = 0;
        }

        public Context getContext() {
            return mContext;
        }

        /**
         * 设置顶部图片
         *
         * @param iconRes
         */
        public void setTopIcon(@DrawableRes int iconRes) {
            topIconId = iconRes;
        }

        public int getTopIcon() {
            return topIconId;
        }

        public String getTitleText() {
            return titleText;
        }

        public Builder setTitleText(String titleText) {
            this.titleText = titleText;
            return this;
        }

        public Builder setPhoneInputType() {
            inputType = InputType.TYPE_CLASS_PHONE;
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

        public float getHeight() {
            return height;
        }

        public void setHeight(float height) {
            this.height = height;
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

        public int getLineColor() {

            return lineColor;
        }

        public Builder setLineColor(@ColorRes int lineColor) {
            this.lineColor = ContextCompat.getColor(mContext, lineColor);
            return this;
        }

        public int getLines() {
            return lines;
        }

        public Builder setLines(int lines) {
            this.lines = lines;
            return this;
        }

        public int getMaxLines() {
            return maxLines;
        }

        public Builder setMaxLines(int maxLines) {
            this.maxLines = maxLines;
            return this;
        }

        public int getMaxLength() {
            return maxLength;
        }

        public Builder setMaxLength(int maxLength) {
            this.maxLength = maxLength;
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

        public String getHintText() {
            return hintText;
        }

        public Builder setHintText(String hintText) {
            this.hintText = hintText;
            return this;
        }

        public int getHintTextColor() {
            return hintTextColor;
        }

        public Builder setHintTextColor(int hintTextColor) {
            this.hintTextColor = ContextCompat.getColor(mContext, hintTextColor);
            return this;
        }

        public int getInputType() {
            return inputType;
        }

        public Builder setInputType(int inputType) {
            this.inputType = inputType;
            return this;
        }

        public OnClickEditDialogListener getListener() {
            return listener;
        }

        public Builder setOnclickListener(OnClickEditDialogListener listener) {
            this.listener = listener;
            return this;
        }

        public D3EditDialog build() {
            return new D3EditDialog(this);
        }
    }

}
