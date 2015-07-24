package com.example.mingdao.mingdao;

import java.util.List;

/**
 * Created by mingdao on 15/7/24.
 */
public class Detail {
    public String getAnonymous() {
        return Anonymous;
    }

    public void setAnonymous(String anonymous) {
        Anonymous = anonymous;
    }

    public String getAvailableNumber() {
        return AvailableNumber;
    }

    public void setAvailableNumber(String availableNumber) {
        AvailableNumber = availableNumber;
    }

    public String getVisble() {
        return Visble;
    }

    public void setVisble(String visble) {
        Visble = visble;
    }

    public String getDeadline() {
        return Deadline;
    }

    public void setDeadline(String deadline) {
        Deadline = deadline;
    }

    public List<Option> getOptions() {
        return options;
    }

    public void setOptions(List<Option> options) {
        this.options = options;
    }

    public String getLink_des() {
        return link_des;
    }

    public void setLink_des(String link_des) {
        this.link_des = link_des;
    }

    public String getLink_title() {
        return link_title;
    }

    public void setLink_title(String link_title) {
        this.link_title = link_title;
    }

    public String getLink_url() {
        return link_url;
    }

    public void setLink_url(String link_url) {
        this.link_url = link_url;
    }

    public String getIs_center() {
        return is_center;
    }

    public void setIs_center(String is_center) {
        this.is_center = is_center;
    }

    public String getMiddle_pic() {
        return middle_pic;
    }

    public void setMiddle_pic(String middle_pic) {
        this.middle_pic = middle_pic;
    }

    public String getThumbnail_pic() {
        return thumbnail_pic;
    }

    public void setThumbnail_pic(String thumbnail_pic) {
        this.thumbnail_pic = thumbnail_pic;
    }

    public String getOriginal_filename() {
        return original_filename;
    }

    public void setOriginal_filename(String original_filename) {
        this.original_filename = original_filename;
    }

    public String getFilesize() {
        return filesize;
    }

    public void setFilesize(String filesize) {
        this.filesize = filesize;
    }

    public String getFile_type() {
        return file_type;
    }

    public void setFile_type(String file_type) {
        this.file_type = file_type;
    }

    public String getAllow_down() {
        return allow_down;
    }

    public void setAllow_down(String allow_down) {
        this.allow_down = allow_down;
    }

    public String getOriginal_file() {
        return original_file;
    }

    public void setOriginal_file(String original_file) {
        this.original_file = original_file;
    }

    public String getVideo_url() {
        return video_url;
    }

    public void setVideo_url(String video_url) {
        this.video_url = video_url;
    }

    public String getVideo_fileName() {
        return video_fileName;
    }

    public void setVideo_fileName(String video_fileName) {
        this.video_fileName = video_fileName;
    }

    public String getVideo_thumbUrl() {
        return video_thumbUrl;
    }

    public void setVideo_thumbUrl(String video_thumbUrl) {
        this.video_thumbUrl = video_thumbUrl;
    }

    private String Anonymous;
    private String AvailableNumber;
    private String Visble;
    private String Deadline;
    private List<Option>options;
    private String link_des;
    private String link_title;
    private String link_url;
    private String is_center;
    private String middle_pic;
    private String thumbnail_pic;
    private String original_filename;
    private String filesize;
    private String file_type;
    private String allow_down;
    private String original_file;
    private String video_url;
    private String video_fileName;
    private String video_thumbUrl;
}
