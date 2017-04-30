package com.treehouse.blog.model;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created by jorgeotero on 4/29/17.
 */
public abstract class Entry {
    private String date;
    private String title;
    private String body;

    public Entry(String title, String body) {
        this.title = title;
        this.body = body;
        Date date = new Date();
        String dateformat = "MMMM dd, yyyy 'at' hh:mm";
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat(dateformat);
        this.date = simpleDateFormat.format(date);
    }

    public String getDate() {
        return date;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getBody() {
        return body;
    }

    public void setBody(String body) {
        this.body = body;
    }
}
