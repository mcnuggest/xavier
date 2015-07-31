package com.example.mingdao.mingdao;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.text.Spannable;
import android.text.SpannableString;
import android.text.SpannableStringBuilder;
import android.text.style.ImageSpan;
import android.util.Log;



import java.util.HashMap;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class SmileyParser {
	private Context mContext;
	private static String[] mSmileyTexts;
	private Pattern mPattern;
	private HashMap<String, Integer> mSmileyToRes;

    public static final int[] DEFAULT_SMILEY_RES_IDS = {
            R.drawable.wx_thumb, R.drawable.hanx_thumb, R.drawable.lei_thumb,
            R.drawable.qiu_thumb, R.drawable.tx_thumb, R.drawable.ka_thumb,
            R.drawable.dy_thumb, R.drawable.se_thumb, R.drawable.ng_thumb,
            R.drawable.gz_thumb, R.drawable.yw_thumb, R.drawable.tu_thumb,
            R.drawable.qiao_thumb, R.drawable.fanu_v2, R.drawable.fendou_v2,
            R.drawable.haixiu_v2, R.drawable.zhuakuang_v2, R.drawable.yun_v2,
            R.drawable.shuai_v2, R.drawable.baoquan_v2,  R.drawable.handshake,
            R.drawable.yeah, R.drawable.good, R.drawable.small,
            R.drawable.ok, R.drawable.bianpao_v2, R.drawable.chaopiao_v2,
            R.drawable.chifan_v2, R.drawable.dengpao_v2, R.drawable.heicha_v2,
            R.drawable.hou_v2, R.drawable.xiongmao_v2, R.drawable.pijiu_v2,
            R.drawable.shandian_v2, R.drawable.shuangxi_v2, R.drawable.xuehua_v2,
            R.drawable.yewan_v2, R.drawable.yongbao_v2, R.drawable.cake,
            R.drawable.heart, R.drawable.unheart, R.drawable.rose,
            R.drawable.gift, R.drawable.mysun, R.drawable.vw_thumb,
            R.drawable.team };

	public SmileyParser(Context context) {
		mContext = context;
		mSmileyTexts = mContext.getResources().getStringArray(DEFAULT_SMILEY_TEXTS);
		mSmileyToRes = buildSmileyToRes();
		mPattern = buildPattern();

	}
	public static final int DEFAULT_SMILEY_TEXTS = R.array.default_smiley_texts;

	public static String[] getSmileyTexts(Context context) {
		if (mSmileyTexts == null) {
			mSmileyTexts = context.getResources().getStringArray(
					DEFAULT_SMILEY_TEXTS);
		}
		return mSmileyTexts;
	}

	public  HashMap<String, Integer> buildSmileyToRes() {
		if (DEFAULT_SMILEY_RES_IDS.length != mSmileyTexts.length) {
			// Log.w("SmileyParser", "Smiley resource ID/text mismatch");
			// 表情的数量需要和数组定义的长度一致！
			throw new IllegalStateException("Smiley resource ID/text mismatch");
		}

		HashMap<String, Integer> smileyToRes = new HashMap<String, Integer>(
				mSmileyTexts.length);
		for (int i = 0; i < mSmileyTexts.length; i++) {
			smileyToRes.put(mSmileyTexts[i], DEFAULT_SMILEY_RES_IDS[i]);
		}

		return smileyToRes;
	}

	// 构建正则表达式
	private Pattern buildPattern() {
		StringBuilder patternString = new StringBuilder(mSmileyTexts.length * 3);
		patternString.append('(');
		for (String s : mSmileyTexts) {
			patternString.append(Pattern.quote(s));
			patternString.append('|');
		}
		patternString.replace(patternString.length() - 1,
				patternString.length(), ")");
		return Pattern.compile(patternString.toString());
	}

	// 根据文本替换成图片
	public CharSequence replace(CharSequence text) {
		Log.e("SmileyParser text=", text.toString());
		SpannableStringBuilder builder = new SpannableStringBuilder(text);
		Matcher matcher = mPattern.matcher(text);
		while (matcher.find()) {
			int resId = mSmileyToRes.get(matcher.group());
			Drawable drawable=mContext.getResources().getDrawable(resId);  
			drawable.setBounds(0, 0, drawable.getIntrinsicWidth()/2, drawable.getIntrinsicHeight()/2);
			ImageSpan imageSpan = new ImageSpan(drawable,ImageSpan.ALIGN_BASELINE);
			builder.setSpan(imageSpan, matcher.start(),
					matcher.end(), Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
		}
		return builder;
	}
	
	// 根据文本替换成图片
	public CharSequence replace(CharSequence text,int lineHeight) {
		SpannableString builder = new SpannableString(text);
//        SpannableString builder=new SpannableString(text);
		Matcher matcher = mPattern.matcher(text);
        try {
            while (matcher.find()) {
                String a = matcher.group();
                int resId = mSmileyToRes.get(a);
//			    Drawable drawable=context.getResources().getDrawable(resId); //Drawable在Android 5.0系统上 0帧gif动画无法显示
                GifDrawalbe drawable = new GifDrawalbe(mContext, resId);  //GifDrawalbe适用Android 5.0系统
                int moveTopPx=UIUtil.dip2px(mContext, 2);
                int decreasePx=UIUtil.dip2px(mContext, 2);
                drawable.setOneShot(true);
                drawable.setBounds(0, -moveTopPx, lineHeight-decreasePx, lineHeight-moveTopPx-decreasePx);
//                drawable.setBounds(0, 0, drawable.getIntrinsicWidth(), drawable.getIntrinsicHeight());
//                drawable.start();
                ImageSpan imageSpan = new ImageSpan(drawable, ImageSpan.ALIGN_BASELINE);
                builder.setSpan(imageSpan, matcher.start(), matcher.end(), Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
            }
        }catch (Exception e){
            e.printStackTrace();
        }
		return builder;
	}
}
