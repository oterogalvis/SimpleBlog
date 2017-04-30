package com.treehouse.blog.model;

import java.util.List;

/**
 * Created by jorgeotero on 4/29/17.
 */
public interface BlogDao {

    boolean addPost(Post post);

    boolean deletePost(Post post);

    List<Post> getPosts();

    Post getPostsbySlug(String postSlug);

    String getUser();
}
