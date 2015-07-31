package com.example.mingdao.mingdao;

import android.content.Context;
import android.graphics.drawable.AnimationDrawable;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;

public class GifDrawalbe extends AnimationDrawable {

	public GifDrawalbe(Context context, int id) {
		GifHelper helper = new GifHelper();
		helper.read(context.getResources().openRawResource(id));
		
		System.out.println("---------------------con:"+context.getResources().openRawResource(id).toString());
		int gifCount = helper.getFrameCount();
		if (gifCount <= 0) {
            Drawable drawable=context.getResources().getDrawable(id);
            addFrame(drawable, 0);
            drawable.setBounds(0, 0, drawable.getIntrinsicWidth(), drawable.getIntrinsicHeight());
		}else {
            BitmapDrawable bd = new BitmapDrawable(null, helper.getImage());
            int delay=helper.getDelay(0);
            addFrame(bd, helper.getDelay(0));
            for (int i = 1; i < helper.getFrameCount(); i++) {
                addFrame(new BitmapDrawable(null, helper.nextBitmap()), helper.getDelay(i));
            }
            bd.setBounds(0, 0, bd.getIntrinsicWidth(), bd.getIntrinsicHeight());
            int width=helper.getImage().getWidth();
            int height=helper.getImage().getHeight();
            setBounds(0, 0, helper.getImage().getWidth(), helper.getImage().getHeight());
        }
		invalidateSelf();
	}

}
