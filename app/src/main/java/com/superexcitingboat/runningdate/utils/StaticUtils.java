package com.superexcitingboat.runningdate.utils;

import android.content.ClipData;
import android.content.ClipboardManager;
import android.content.Context;
import android.content.Intent;
import android.widget.Toast;

import com.superexcitingboat.runningdate.config.Api;

public class StaticUtils {//一些工具方法可以扔在这儿

    public static void shareApp(Context context) {
        Intent shareIntent = new Intent();
        shareIntent.setAction(Intent.ACTION_SEND);
        shareIntent.putExtra(Intent.EXTRA_TEXT, Api.BASE_URL);
        shareIntent.setType("text/plain");

        //设置分享列表的标题，并且每次都显示分享列表
        context.startActivity(Intent.createChooser(shareIntent, "分享到"));
    }

    public static void copyToClipboard(Context context, String text) {
        ClipboardManager clipboardManager = (ClipboardManager) context.getSystemService(Context.CLIPBOARD_SERVICE);
        clipboardManager.setPrimaryClip(ClipData.newPlainText("text", text));
        Toast.makeText(context, "已复制到剪贴板", Toast.LENGTH_SHORT).show();
    }

    public static String cutLine(String line, int max) {
        String[] result = line.split("\n", max);
        if (result.length < max) {
            return line;
        } else {
            StringBuilder stringBuilder = new StringBuilder();
            for (String tmp : result) {
                stringBuilder.append(tmp);
            }
            return stringBuilder.toString();
        }
    }

}
