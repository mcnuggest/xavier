package com.example.mingdao.mingdao;

import java.util.List;

/**
 * Created by mingdao on 15/7/24.
 */
public class Post {
    private String id;
    private String guid;
    private String text;
    private Text_attributeEntity text_attribute;
    private List<Tag>tags;
    private String create_time;
    private String source;
    private String reply_count;
    private String like_count;
    private String reshared_count;
    private String favorite_count;
    private String favorite;
    private String like;
    private String type;
    private String share_type;
    private List<Detail>details;
    private String have_bestanswer;
    private String mark;
    private List<Group>groups;
    private User user;
    private Repost repost;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getGuid() {
        return guid;
    }

    public void setGuid(String guid) {
        this.guid = guid;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public Text_attributeEntity getText_attribute() {
        return text_attribute;
    }

    public void setText_attribute(Text_attributeEntity text_attribute) {
        this.text_attribute = text_attribute;
    }

    public List<Tag> getTags() {
        return tags;
    }

    public void setTags(List<Tag> tags) {
        this.tags = tags;
    }

    public String getCreate_time() {
        return create_time;
    }

    public void setCreate_time(String create_time) {
        this.create_time = create_time;
    }

    public String getSource() {
        return source;
    }

    public void setSource(String source) {
        this.source = source;
    }

    public String getReply_count() {
        return reply_count;
    }

    public void setReply_count(String reply_count) {
        this.reply_count = reply_count;
    }

    public String getLike_count() {
        return like_count;
    }

    public void setLike_count(String like_count) {
        this.like_count = like_count;
    }

    public String getReshared_count() {
        return reshared_count;
    }

    public void setReshared_count(String reshared_count) {
        this.reshared_count = reshared_count;
    }

    public String getFavorite_count() {
        return favorite_count;
    }

    public void setFavorite_count(String favorite_count) {
        this.favorite_count = favorite_count;
    }

    public String getFavorite() {
        return favorite;
    }

    public void setFavorite(String favorite) {
        this.favorite = favorite;
    }

    public String getLike() {
        return like;
    }

    public void setLike(String like) {
        this.like = like;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getShare_type() {
        return share_type;
    }

    public void setShare_type(String share_type) {
        this.share_type = share_type;
    }

    public List<Detail> getDetails() {
        return details;
    }

    public void setDetails(List<Detail> details) {
        this.details = details;
    }

    public String getHave_bestanswer() {
        return have_bestanswer;
    }

    public void setHave_bestanswer(String have_bestanswer) {
        this.have_bestanswer = have_bestanswer;
    }

    public String getMark() {
        return mark;
    }

    public void setMark(String mark) {
        this.mark = mark;
    }

    public List<Group> getGroups() {
        return groups;
    }

    public void setGroups(List<Group> groups) {
        this.groups = groups;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Repost getRepost() {
        return repost;
    }

    public void setRepost(Repost repost) {
        this.repost = repost;
    }
}
