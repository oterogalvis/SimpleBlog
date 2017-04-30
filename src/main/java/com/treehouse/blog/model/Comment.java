package com.treehouse.blog.model;

/**
 * Created by jorgeotero on 4/29/17.
 */
public class Comment extends Entry {

    public Comment(String title, String body) {
        super(title, body);
        if (null == title || title.equals("")) {
            this.setTitle("Anonymous");
        }
        if (null == body || body.equals("")) {
            this.setBody("Empty comment...");
        }
    }
}
