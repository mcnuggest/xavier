package com.example.mingdao.mingdao;

import java.util.List;

/**
 * Created by mingdao on 15/7/24.
 */
public class DongTai {
    private List<Post>posts;
    private String more;
    private String sincepostid;

    public List<Post> getPosts() {
        return posts;
    }

    public void setPosts(List<Post> posts) {
        this.posts = posts;
    }

    public String getMore() {
        return more;
    }

    public void setMore(String more) {
        this.more = more;
    }

    public String getSincepostid() {
        return sincepostid;
    }

    public void setSincepostid(String sincepostid) {
        this.sincepostid = sincepostid;
    }
}
