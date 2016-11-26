package com.superexcitingboat.runningdate.utils;

import android.content.ClipData;
import android.content.ClipboardManager;
import android.content.Context;
import android.content.Intent;
import android.widget.Toast;

import com.superexcitingboat.runningdate.config.Api;

import java.text.SimpleDateFormat;
import java.util.Date;

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

    /**
     * @param second second
     * @return time like 00:00:00
     */
    public static String secondToTime(int second) {
        int tmp = second / 3600;
        second = second - tmp * 3600;
        String h = tmp > 9 ? "" + tmp : "0" + tmp;
        if (Integer.valueOf(h) > 99) {
            h = "99";
        }
        tmp = second / 60;
        second = second - tmp * 60;
        String min = tmp > 9 ? "" + tmp : "0" + tmp;
        String sec = second > 9 ? "" + second : "0" + second;
        return h + ":" + min + ":" + sec;
    }

    public static String cutNumber(double number, int bit) {
        String tmp = String.valueOf(number);
        int index = tmp.indexOf(".");
        if (tmp.length() - index - 1 < bit) {
            return tmp;
        }
        return tmp.substring(0, index + bit + 1);
    }

    /**
     * @param timestamp timestamp
     * @return date like 2016-11-24  14:59:26
     */
    public static String timeStampToDate(long timestamp) {
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd  HH:mm:ss ");
        Date curDate = new Date(timestamp);
        return formatter.format(curDate);
    }



}
