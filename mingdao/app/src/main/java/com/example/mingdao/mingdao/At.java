package com.example.mingdao.mingdao;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.net.Uri;
import android.text.Html;
import android.text.Spannable;
import android.text.SpannableString;
import android.text.Spanned;
import android.text.TextUtils;
import android.text.method.LinkMovementMethod;
import android.text.style.ClickableSpan;
import android.text.style.ForegroundColorSpan;
import android.text.style.URLSpan;
import android.util.Log;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import java.net.URL;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by mingdao on 15/7/28.
 */
public class At {
    public static void At(String all, final Context mContext, TextView textView) {
        if (TextUtils.isEmpty(all)) {
            return;
        }


        Map<String, String> nameIdUserMap = new HashMap<String, String>();
        Map<String, String> nameIdGroupMap = new HashMap<String, String>();
        Pattern atPeoplePtn = Pattern.compile("###.*?###");
        String tempAll = all;
        Matcher matcher = atPeoplePtn.matcher(tempAll);
        while (matcher.find()) {
            int i = matcher.start();
            int j = matcher.end();
            String idName = tempAll.substring(i + 3, j - 3);
            String idNames[] = idName.split("\\|");
            if (idNames != null && idNames.length > 1) {
                all = all.replace(tempAll.subSequence(i, j), " @" + idNames[1] + " ");
                nameIdUserMap.put(idNames[1], idNames[0]);
            }
        }
        Pattern atGroupPtn = Pattern.compile("\\$\\$\\$.*?\\$\\$\\$");
        tempAll = all;
        matcher = atGroupPtn.matcher(tempAll);
        while (matcher.find()) {
            int i = matcher.start();
            int j = matcher.end();
            String idName = tempAll.substring(i + 3, j - 3);
            String idNames[] = idName.split("\\|");
            if (idNames != null && idNames.length > 1) {
                all = all.replace(tempAll.subSequence(i, j), " @" + idNames[1] + " ");
                nameIdGroupMap.put(idNames[1], idNames[0]);
            }
        }
        SpannableString ss = new SpannableString(all);
        setAtUserAndGroup(mContext, nameIdUserMap, nameIdGroupMap, all, ss);
        SmileyParser parser = new SmileyParser(mContext);
//        CharSequence smiley = parser.replace(ss, textView.getLineHeight()).toString();
        textView.setText(parser.replace(ss, textView.getLineHeight()));
        textView.setMovementMethod(LinkMovementMethod.getInstance());






        /*Set<String> userSet = nameIdUserMap.keySet();
        Set<String> groupSet = nameIdGroupMap.keySet();
        List<String> list = new ArrayList<String>();
        list.addAll(userSet);
        list.addAll(groupSet);
        Iterator<String> ite = list.iterator();
        StringBuffer tempStr;
        while (ite.hasNext()) {
            String key = ite.next();
            tempStr = new StringBuffer(all);
            while (tempStr.indexOf("@" + key) >= 0) {
                int i = tempStr.indexOf("@" + key);
                int j = i + key.length() + 1;
                String name = key;
                if (!TextUtils.isEmpty(nameIdUserMap.get(name))) {
                    ss.setSpan(new ForegroundColorSpan(0xff288ccf), i, j, Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
                }else if(!TextUtils.isEmpty(nameIdGroupMap.get(name))){
                    ss.setSpan(new ForegroundColorSpan(0xff288ccf), i, j, Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
                }
                tempStr.replace(i, i + 1, "#");
            }
        }*/




        /*return ss;*/

    }

    private static void setAtUserAndGroup(final Context mContext, final Map<String, String> nameIdUserMap, Map<String, String> nameIdGroupMap, final String all, SpannableString ss) {
        // 处理@人和@群组的点击事件
        Set<String> userSet = nameIdUserMap.keySet();
        Set<String> groupSet = nameIdGroupMap.keySet();
        List<String> list = new ArrayList<String>();
        list.addAll(userSet);
        list.addAll(groupSet);
        Iterator<String> ite = list.iterator();
        StringBuffer tempStr;
        final String url = "https://www.baidu.com/s?";
        while (ite.hasNext()) {
            String key = ite.next();
            tempStr = new StringBuffer(all);
            while (tempStr.indexOf("@" + key) >= 0) {
                final int i = tempStr.indexOf("@" + key);
                final int j = i + key.length() + 1;
                final String name = key;
                if (!TextUtils.isEmpty(nameIdUserMap.get(name))) {
                    ss.setSpan(new ForegroundColorSpan(0xff288ccf), i, j, Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
                    ss.setSpan(new ClickableSpan() {
                        @Override
                        public void onClick(View widget) {
                            StringBuilder sb = new StringBuilder(url);
                            sb.append("&wd=" + all.substring(i + 1, j));
                            Uri uri = Uri.parse(sb.toString());
                            Intent it = new Intent(Intent.ACTION_VIEW, uri);
                            mContext.startActivity(it);
                        }
                    }, i, j, Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
                } else if (!TextUtils.isEmpty(nameIdGroupMap.get(name))) {
                    Group g = new Group();
                    g.setId(nameIdGroupMap.get(name));
                    g.setName(name);
                    ss.setSpan(new ForegroundColorSpan(0xff288ccf), i, j, Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
                    ss.setSpan(new ClickableSpan() {
                        @Override
                        public void onClick(View widget) {
                            Toast.makeText(mContext, "success", Toast.LENGTH_SHORT).show();
                            StringBuilder sb = new StringBuilder(url);
                            sb.append("&wd=" + all.substring(i + 1, j));
                            Uri uri = Uri.parse(sb.toString());
                            Intent it = new Intent(Intent.ACTION_VIEW, uri);
                            mContext.startActivity(it);
                        }
                    }, i, j, Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);

                }
                tempStr.replace(i, i + 1, "#");
            }
        }
    }


}

