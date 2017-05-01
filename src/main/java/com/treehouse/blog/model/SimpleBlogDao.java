package com.treehouse.blog.model;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by jorgeotero on 4/29/17.
 */
public class SimpleBlogDao implements BlogDao {
    private List<Post> posts;
    private String user;

    public SimpleBlogDao() {
        posts = new ArrayList<>();
        setUser("admin");

        Post example1 = new Post("The best day I’ve ever had", "Lorem ipsum dolor sit amet, consectetur adipiscing elit. Nunc ut rhoncus felis, vel tincidunt neque. Vestibulum ut metus eleifend, malesuada nisl at, scelerisque sapien. Vivamus pharetra massa libero, sed feugiat turpis efficitur at.");
        example1.addComments(new Comment("Carling Kirk", "Lorem ipsum dolor sit amet, consectetur adipiscing elit. Nunc ut rhoncus felis, vel tincidunt neque. Vestibulum ut metus eleifend, malesuada nisl at, scelerisque sapien. Vivamus pharetra massa libero, sed feugiat turpis efficitur at."));
        addPost(example1);
        example1.addTag("optimistic");
        example1.addTag("happy");
        Post example2 = new Post("The absolute worst day I’ve ever had", "Lorem ipsum dolor sit amet, consectetur adipiscing elit. Nunc ut rhoncus felis, vel tincidunt neque. Vestibulum ut metus eleifend, malesuada nisl at, scelerisque sapien. Vivamus pharetra massa libero, sed feugiat turpis efficitur at.");
        example2.addComments(new Comment("Carling Kirk", "Lorem ipsum dolor sit amet, consectetur adipiscing elit. Nunc ut rhoncus felis, vel tincidunt neque. Vestibulum ut metus eleifend, malesuada nisl at, scelerisque sapien. Vivamus pharetra massa libero, sed feugiat turpis efficitur at."));
        example2.addComments(new Comment("Jesus Maldonado", "Lorem ipsum dolor sit amet, consectetur adipiscing elit. Nunc ut rhoncus felis, vel tincidunt neque. Vestibulum ut metus eleifend, malesuada nisl at, scelerisque sapien. Vivamus pharetra massa libero, sed feugiat turpis efficitur at."));
        addPost(example2);
        Post example3 = new Post("That time at the mall", "Lorem ipsum dolor sit amet, consectetur adipiscing elit. Nunc ut rhoncus felis, vel tincidunt neque. Vestibulum ut metus eleifend, malesuada nisl at, scelerisque sapien. Vivamus pharetra massa libero, sed feugiat turpis efficitur at.");
        addPost(example3);
        example3.addTag("memory");
    }

    @Override
    public boolean addPost(Post post) {
        return posts.add(post);
    }

    @Override
    public boolean deletePost(Post post) {
        return posts.remove(post);
    }

    @Override
    public List<Post> getPosts() {
        return posts;
    }

    @Override
    public Post getPostsbySlug(String postSlug) {
        return posts.stream().filter(post ->
                post.getSlug().equals(postSlug)).findFirst().orElseThrow(NotFoundException::new);
    }

    @Override
    public String getUser() {
        return user;
    }

    public void setUser(String user) {
        this.user = user;
    }
}
