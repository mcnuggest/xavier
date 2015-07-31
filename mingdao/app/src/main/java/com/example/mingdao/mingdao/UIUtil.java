package com.example.mingdao.mingdao;

import android.app.AlertDialog;
import android.app.Dialog;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.text.Spannable;
import android.text.SpannableString;
import android.text.style.ForegroundColorSpan;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;


import java.util.Calendar;

/**
 * Created by alanyuan on 15/4/13.
 */
public class UIUtil {

    public static Toast toast = null;

    /**
     * 根据手机的分辨率从 dp 的单位 转成为 px(像素)
     */
    public static int dip2px(Context context, float dpValue) {
        final float scale = context.getResources().getDisplayMetrics().density;
        return (int) (dpValue * scale + 0.5f);
    }

    /**
     * 根据手机的分辨率从 px(像素) 的单位 转成为 dp
     */
    public static int px2dip(Context context, float pxValue) {
        final float scale = context.getResources().getDisplayMetrics().density;
        return (int) (pxValue / scale + 0.5f);
    }

    public static void showToastLong(Context ctx, int resId) {
//		Toast.makeText(ctx, ctx.getText(resId), Toast.LENGTH_LONG).show();
        if (toast == null) {
            toast = Toast.makeText(ctx, resId, Toast.LENGTH_LONG);
        } else {
            toast.setText(ctx.getText(resId));
        }
        toast.show();
    }

    public static void showToastLong(Context ctx, String content) {
//		Toast.makeText(ctx, content, Toast.LENGTH_LONG).show();
        if (toast == null) {
            toast = Toast.makeText(ctx, content, Toast.LENGTH_LONG);
        } else {
            toast.setText(content);
        }
        toast.show();
    }

    public static void showToastShort(Context ctx, String content) {
//		Toast.makeText(ctx, content, Toast.LENGTH_SHORT).show();
        if (toast == null) {
            toast = Toast.makeText(ctx, content, Toast.LENGTH_SHORT);
        } else {
            toast.setText(content);
        }
        toast.show();
    }

    public static void showToastShort(Context ctx, int resId) {
//		Toast.makeText(ctx, ctx.getText(resId), Toast.LENGTH_SHORT).show();
        if (toast == null) {
            toast = Toast.makeText(ctx, resId, Toast.LENGTH_SHORT);
        } else {
            toast.setText(ctx.getText(resId));
        }
        toast.show();
    }


    //隐藏虚拟键盘
    public static void hideKeyboard(View v) {
        InputMethodManager imm = (InputMethodManager) v.getContext().getSystemService(Context.INPUT_METHOD_SERVICE);
        if (imm.isActive()) {
            imm.hideSoftInputFromWindow(v.getApplicationWindowToken(), 0);
        }
    }

    //显示虚拟键盘
    public static void showKeyboard(View v) {
        InputMethodManager imm = (InputMethodManager) v.getContext().getSystemService(Context.INPUT_METHOD_SERVICE);
        imm.showSoftInput(v, 0);
//        imm.toggleSoftInput(0, InputMethodManager.HIDE_NOT_ALWAYS);
    }

    /**
     * 创建进度条对话框
     *
     * @param con
     * @param resId
     * @return
     */
    public static ProgressDialog createLoadingDialog(Context con, int resId) {
        ProgressDialog mProgressDialog = new ProgressDialog(con);
        mProgressDialog.setMessage(con.getText(resId));
        mProgressDialog.setIndeterminate(true);
        return mProgressDialog;
    }


    /**
     * *
     * alertDialog 类似于 contextmenu
     */
    public static void showMenuDialog(Context contenxt, String title, String[] menus, DialogInterface.OnClickListener dialoglistener) {
        AlertDialog.Builder builder = new AlertDialog.Builder(contenxt);
        builder.setTitle(title);
        builder.setItems(menus, dialoglistener);
        builder.show();
    }


}
