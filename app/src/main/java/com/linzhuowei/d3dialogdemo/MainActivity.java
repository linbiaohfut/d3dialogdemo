package com.linzhuowei.d3dialogdemo;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;

import com.linzhuowei.d3dialog.D3AlertDialog;
import com.linzhuowei.d3dialog.D3EditDialog;
import com.linzhuowei.d3dialog.D3InfoDialog;

public class MainActivity extends AppCompatActivity implements View.OnClickListener{

    Button successTipBtn;
    Button failureTipBtn;
    Button confirmBtn;
    Button editBtn;

    private D3EditDialog mD3EditDialog;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        successTipBtn = (Button)findViewById(R.id.success_tip_btn);
        failureTipBtn = (Button)findViewById(R.id.failure_tip_btn);
        confirmBtn = (Button)findViewById(R.id.confirm_btn);
        editBtn = (Button)findViewById(R.id.edit_btn);

        successTipBtn.setOnClickListener(this);
        failureTipBtn.setOnClickListener(this);
        confirmBtn.setOnClickListener(this);
        editBtn.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.success_tip_btn:{
                new D3InfoDialog.Builder(this)
                        .setTitleText("添加成功")
                        .setContentText("添加用户成功")
                        .setButtonText("好的")
                        .build().show();
            }
            case R.id.failure_tip_btn:{
                new D3InfoDialog.Builder(this)
                        .setTopIcon(R.drawable.ic_error)
                        .setTitleText("注册失败")
                        .setContentText("用户已被注册")
                        .setButtonBackgroundResource(R.drawable.background_error_tip_text)
                        .build().show();
            }
            break;
            case R.id.confirm_btn:{
                new D3AlertDialog.Builder(this)
                        .setTitleText("删除设备")
                        .setContentText("是否删除该设备？")
                        .setRightButtonText("确定")
                        .setOnclickListener(new D3AlertDialog.DialogOnClickListener() {
                            @Override
                            public void clickLeftButton(View view) {

                            }

                            @Override
                            public void clickRightButton(View view) {

                            }
                        }).build().show();
            }
            break;
            case R.id.edit_btn:{
                mD3EditDialog = new D3EditDialog.Builder(this)
                        .setMaxLength(30)
                        .setHintText("最多30位字符")
                        .setMaxLines(2)
                        .setLeftButtonText("暂不修改")
                        .setRightButtonTextColor(R.color.black_light)
                        .setRightButtonText("修改")
                        .setCanceledOnTouchOutside(false)
                        .setOnclickListener(new D3EditDialog.OnClickEditDialogListener() {
                            @Override
                            public boolean clickLeftButton(View view, String editText) {
                                return true;
                            }

                            @Override
                            public boolean clickRightButton(View view, String editText) {
                                if (TextUtils.isEmpty(editText)) {
                                    mD3EditDialog.setErrorMessage("请输入用户姓名");
                                    return false;
                                }
                                mD3EditDialog.setErrorMessage("");
                                return true;
                            }
                        })
                        .build();
                mD3EditDialog.show();
            }
            break;
        }
    }
}
