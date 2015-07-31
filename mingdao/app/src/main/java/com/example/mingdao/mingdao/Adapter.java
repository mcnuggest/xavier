package com.example.mingdao.mingdao;

import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.text.SpannableString;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;


import com.nostra13.universalimageloader.core.DisplayImageOptions;
import com.nostra13.universalimageloader.core.ImageLoader;
import com.nostra13.universalimageloader.core.assist.ImageScaleType;
import com.nostra13.universalimageloader.core.listener.SimpleImageLoadingListener;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Created by mingdao on 15/7/28.
 */

public class Adapter extends BaseAdapter {
    Context context;
    private LayoutInflater mInflater = null;
    public List<Post> posts = new ArrayList<>();
    Bitmap bitmap;
    int number;
    DisplayImageOptions options;
    long timediff;
    long days;
    long hours;
    long minutes;
    long seconds;
    LikeIt likeIt = new LikeIt();
    String url = "https://www.baidu.com/s?";

    public Adapter(Context context, List<Post> data) {
        this.context = context;
        posts = data;
        this.mInflater = LayoutInflater.from(context);
        options = new DisplayImageOptions.Builder()
                .showImageOnLoading(R.color.White) //设置图片在下载期间显示的图片
                .showImageForEmptyUri(R.color.White)//设置图片Uri为空或是错误的时候显示的图片
                .showImageOnFail(R.color.White)  //设置图片加载/解码过程中错误时候显示的图片
                .cacheInMemory(true)//设置下载的图片是否缓存在内存中
                .cacheOnDisk(true)//设置下载的图片是否缓存在SD卡中
                .imageScaleType(ImageScaleType.IN_SAMPLE_INT)//设置图片以如何的编码方式显示
                .bitmapConfig(Bitmap.Config.RGB_565)//设置图片的解码类型
                .delayBeforeLoading(0)//int delayInMillis为你设置的下载前的延迟时间
                .resetViewBeforeLoading(true)//设置图片在下载前是否重置，复位
                .build();
    }

    @Override
    public int getCount() {
        return posts.size();
    }

    @Override
    public Post getItem(int position) {
        return posts.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        final ViewHolder holder;
        if (convertView == null) {
            holder = new ViewHolder();
            convertView = mInflater.inflate(R.layout.dongtai, null);
            holder.name = (TextView) convertView.findViewById(R.id.name);
            holder.time = (TextView) convertView.findViewById(R.id.time);
            holder.text = (TextView) convertView.findViewById(R.id.text);
            holder.avatar = (ImageView) convertView.findViewById(R.id.image);
            holder.source = (TextView) convertView.findViewById(R.id.source);
            holder.like = (TextView) convertView.findViewById(R.id.like);
            holder.likeimage = (ImageView) convertView.findViewById(R.id.likeimage);
            convertView.setTag(holder);
        } else {
            holder = (ViewHolder) convertView.getTag();
        }
        final Post post = posts.get(position);
        String imageurl = post.getUser().getAvatar();
        ImageLoader.getInstance().displayImage(imageurl, holder.avatar, options);
        At.At(post.getText(),context,holder.text);
        /*holder.text.setText(At.At(post.getText(), context));*/
        holder.name.setText(post.getUser().getName());
        holder.name.setClickable(true);
        holder.name.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                StringBuilder sb = new StringBuilder(url);
                sb.append("&wd=" + post.getUser().getName());
                Uri uri = Uri.parse(sb.toString());
                Intent it = new Intent(Intent.ACTION_VIEW, uri);
                context.startActivity(it);
            }
        });






        timediff = DataUtilJudge.DataUtilJudge(post.getCreate_time());
        days = hours / 24;
        hours = timediff / 60 / 60;
        minutes = timediff / 60;
        seconds = timediff;
        if (seconds <= 60) {
            holder.time.setText(seconds + "秒前");
        } else if (minutes < 60) {
            holder.time.setText(minutes + "分钟前");
        } else if (hours < 24) {
            holder.time.setText(hours + "小时前");
        } else if (days <= 30) {
            holder.time.setText(days + "天前");
        } else {
            holder.time.setText(post.getCreate_time());
        }
        int likenumber = Integer.parseInt(post.getLike_count());
        holder.source.setText(post.getSource());
        holder.like.setText(post.getLike_count());
        likenumber = likenumber+1;
        final int finalLikenumber = likenumber;
        holder.likeimage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                holder.like.setText(finalLikenumber + "");
                likeIt.LikeIt(context,post.getGuid());
            }
        });

        return convertView;

    }



    class ViewHolder {

        public TextView name;
        public TextView time;
        public TextView text;
        public ImageView avatar;
        public TextView source;
        public TextView like;
        public ImageView likeimage;


    }
}


