package com.treehouse.blog.model;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

/**
 * Created by jorgeotero on 4/29/17.
 */
public class SimpleBlogDaoTest {

    private BlogDao dao;
    private Post example;

    @Before
    public void setUp() throws Exception {
        dao = new SimpleBlogDao();
        example = new Post("That time at the mall", "Lorem ipsum dolor sit amet, consectetur adipiscing elit. Nunc ut rhoncus felis, vel tincidunt neque. Vestibulum ut metus eleifend, malesuada nisl at, scelerisque sapien. Vivamus pharetra massa libero, sed feugiat turpis efficitur at.");
        example.addComments(new Comment("Carling Kirk", "Lorem ipsum dolor sit amet, consectetur adipiscing elit. Nunc ut rhoncus felis, vel tincidunt neque. Vestibulum ut metus eleifend, malesuada nisl at, scelerisque sapien. Vivamus pharetra massa libero, sed feugiat turpis efficitur at."));
        dao.addPost(example);

    }

    @Test
    public void getPostsbySlugTest() throws Exception {
        String slug = "that-time-at-the-mall";
        assertEquals("Verify we get the right title", example.getTitle(), dao.getPostsbySlug(slug).getTitle() );
        assertEquals("Verify we get the right slug", example.getSlug(), dao.getPostsbySlug(slug).getSlug() );
        assertEquals("Verify we get the right body", example.getBody(), dao.getPostsbySlug(slug).getBody() );
    }
}