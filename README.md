# d3dialogdemo
带图标且可以自定义布局的Android 对话框
## 使用方法
  * 成功提示框
``` Java
new D3InfoDialog.Builder(this)
                        .setTitleText("添加成功")
                        .setContentText("添加用户成功")
                        .setButtonText("好的")
                        .build()
                        .show();
``` 
  *效果图
  ![](https://github.com/linbiaohfut/d3dialogdemo/blob/master/screenshots/Screenshot_2017-04-20-19-44-19-093_success.png)  

  * 错误提示框
  ``` Java
  new D3InfoDialog.Builder(this)
                        .setTopIcon(R.drawable.ic_error)
                        .setTitleText("注册失败")
                        .setContentText("用户已被注册")
                        .setButtonBackgroundResource(R.drawable.background_error_tip_text)
                        .build()
                        .show();
``` 
  * 效果图
  ![](https://github.com/linbiaohfut/d3dialogdemo/blob/master/screenshots/Screenshot_2017-04-20-20-01-03-799_error.png) 
  
  * 确认提示框
``` Java
  new D3AlertDialog.Builder(this)
      .setTitleText("删除设备")
      .setContentText("是否删除该设备？")
      .setRightButtonText("确定")
      .setOnclickListener(new D3AlertDialog.DialogOnClickListener() {
          @Override
          public void clickLeftButton(View view) {
              //点击取消按钮
          }

          @Override
          public void clickRightButton(View view) {
              //点击确定按钮
          }
      }).build()
      .show();
``` 
  * 效果图
  ![](https://github.com/linbiaohfut/d3dialogdemo/blob/master/screenshots/Screenshot_2017-04-20-19-44-26-156_ask.png) 
  
  * 编辑提示框
``` Java
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
``` 
  * 效果图
  ![](https://github.com/linbiaohfut/d3dialogdemo/blob/master/screenshots/Screenshot_2017-04-20-19-45-18-290_edit.png)                
  
  
  
  
