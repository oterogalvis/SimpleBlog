package com.treehouse.blog.model;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;
/**
 * Created by jorgeotero on 4/30/17.
 */
public class CommentTest {

    private Post example;

    @Before
    public void setUp() throws Exception {
        example = new Post("That time at the mall", "Lorem ipsum dolor sit amet, consectetur adipiscing elit. Nunc ut rhoncus felis, vel tincidunt neque. Vestibulum ut metus eleifend, malesuada nisl at, scelerisque sapien. Vivamus pharetra massa libero, sed feugiat turpis efficitur at.");
    }

    @Test
    public void constructorTest() throws Exception {
        Comment comment = new Comment("", "");

        example.addComments(comment);

        assertEquals("Comment title equal to Anonymous", "Anonymous", comment.getTitle());
        assertEquals("Comment title equal to Empty comment...", "Empty comment...", comment.getBody());
    }
}