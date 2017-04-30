package com.treehouse.blog.model;

import com.github.slugify.Slugify;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Created by jorgeotero on 4/29/17.
 */
public class Post extends Entry {
    private final String slug;
    private List<String> tags;
    private List<Comment> comments;

    public Post(String title, String body) {
        super(title, body);
        this.tags = new ArrayList<>();
        this.comments = new ArrayList<>();
        Slugify slugify = new Slugify();
        slug = slugify.slugify(title);
    }

    public void addTags(String tag) {
        tags.add(tag);
    }

    public void addComments(Comment comment) {
        comments.add(comment);
    }

    public List<String> getTags() {
        return tags;
    }

    public List<Comment> getComments() {
        return comments;
    }

    public String getSlug() {
        return slug;
    }
}
