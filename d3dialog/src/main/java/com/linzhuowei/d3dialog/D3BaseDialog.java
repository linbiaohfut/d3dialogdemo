package com.linzhuowei.d3dialog;

import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.graphics.Bitmap;
import android.graphics.drawable.Drawable;
import android.support.annotation.DrawableRes;
import android.support.annotation.LayoutRes;
import android.support.annotation.StringRes;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

/**
 * Created by linzhuowei on 2017/4/7.
 */
public abstract class D3BaseDialog implements View.OnClickListener, DialogInterface.OnDismissListener{
    private static final String TAG = "BaseDialog";
    protected Dialog mDialog;
    private View mDialogView;
    protected ImageView mTopIconView;
    private LinearLayout mContentView;
    protected TextView mTitleView;
    protected TextView mMessageView;
    private Context mContext;
    private View mCustomView;//自定义的View

    protected float height;
    protected float width;

    public D3BaseDialog(Context context) {
        this(context,R.style.MyDialogStyle);
    }

    public D3BaseDialog(Context context, int theme) {
        mContext = context;
        init(context, theme);
    }

    private void init(Context context, int theme) {
        mDialog = new Dialog(context, theme);
        //mDialog = new Dialog(mContext, R.style.MyDialogStyle);
        mDialogView = LayoutInflater.from(mContext).inflate(R.layout.dialog_base, null);

        mTopIconView = (ImageView) mDialogView.findViewById(R.id.dialog_top_icon_iv);
        mContentView = (LinearLayout) mDialogView.findViewById(R.id.content_layout);
        mTitleView = (TextView) mDialogView.findViewById(R.id.ld_title);
        mMessageView = (TextView) mDialogView.findViewById(R.id.ld_message);
        mTopIconView.setVisibility(View.VISIBLE);

        ViewGroup parent =  (ViewGroup)mDialogView.findViewById(R.id.ld_custom_view_container);
        if (getCustomLayout() > 0) {
            mCustomView = LayoutInflater.from(mContext).inflate(getCustomLayout(), parent, true);//加载自定义布局
            handleCustomView(mCustomView);
        }
        else{
            mCustomView = null;
        }

        mDialog.setContentView(mDialogView);

        height = 0.28f;
        width = 0.8f;

        Window dialogWindow = mDialog.getWindow();
        WindowManager.LayoutParams lp = dialogWindow.getAttributes();
        lp.width = (int) (ScreenSizeUtils.getInstance(mContext).getScreenWidth() * width);
        lp.height = WindowManager.LayoutParams.WRAP_CONTENT;
        lp.gravity = Gravity.CENTER;
        dialogWindow.setAttributes(lp);
    }

    /**
     * 设置dialog的窗体大小
     * @param widthRate 与屏幕宽度的比值(dialog宽度占屏幕宽度的百分比)，取值0~1
     * @param heightRate 与屏幕高度的比值(dialog高度占屏幕高度的百分比)，取值0~1
     * @return
     */
    public D3BaseDialog changeWindowSize(float widthRate , float heightRate ) {
        height = heightRate;
        width = widthRate;
        Window dialogWindow = mDialog.getWindow();
        WindowManager.LayoutParams lp = dialogWindow.getAttributes();
        lp.width = (int) (ScreenSizeUtils.getInstance(mContext).getScreenWidth() * width);
        lp.height = WindowManager.LayoutParams.WRAP_CONTENT;
        lp.gravity = Gravity.CENTER;
        dialogWindow.setAttributes(lp);
        return  this;
    }

    public D3BaseDialog setTopIcon(Bitmap bitmap) {
        mTopIconView.setVisibility(View.VISIBLE);
        mTopIconView.setImageBitmap(bitmap);
        calcMarginTop();
        return  this;
    }

    public D3BaseDialog setTopIcon(Drawable drawable) {
        mTopIconView.setVisibility(View.VISIBLE);
        mTopIconView.setImageDrawable(drawable);
        calcMarginTop();
        return  this;
    }

    public D3BaseDialog setTopIcon(@DrawableRes int iconRes) {
        mTopIconView.setVisibility(View.VISIBLE);
        mTopIconView.setImageResource(iconRes);
        calcMarginTop();
        return  this;
    }
    protected  void calcMarginTop(){

        int w = View.MeasureSpec.makeMeasureSpec(0,View.MeasureSpec.UNSPECIFIED);
        int h = View.MeasureSpec.makeMeasureSpec(0,View.MeasureSpec.UNSPECIFIED);
        mTopIconView.measure(w, h);
        int height = mTopIconView.getMeasuredHeight();
        RelativeLayout.LayoutParams layoutParams = (RelativeLayout.LayoutParams)mContentView.getLayoutParams();
        layoutParams.topMargin = height / 2;
        mContentView.setLayoutParams(layoutParams);
    }


    public D3BaseDialog setMessage(@StringRes int message) {
        return setMessage(string(message));
    }

    public D3BaseDialog setMessage(CharSequence message) {
        mMessageView.setVisibility(View.VISIBLE);
        mMessageView.setText(message);
        return  this;
    }

    public D3BaseDialog setTitle(@StringRes int title) {
        return setTitle(string(title));
    }

    public D3BaseDialog setTitle(CharSequence title) {
        mTitleView.setVisibility(View.VISIBLE);
        mTitleView.setText(title);
        return this;
    }



    public void show() {
        mDialog.show();
        
    }

    public void dismiss() {
        mDialog.dismiss();

    }

    public boolean isShowing() {
        return mDialog != null && mDialog.isShowing();
    }

    protected <ViewClass extends View> ViewClass findView(int id) {
        if (mCustomView != null ) {
            ViewClass view = (ViewClass) mDialogView.findViewById(id);
            if ( view != null) {
                return  view ;
            }
        }
        return (ViewClass) mDialogView.findViewById(id);
    }

    protected String string(@StringRes int res) {
        return mDialogView.getContext().getString(res);
    }
    protected abstract void handleCustomView(View view);
    @LayoutRes
    protected abstract int getCustomLayout();

    @Override
    public void onClick(View v) {

    }

    @Override
    public void onDismiss(DialogInterface dialog) {

    }
}
