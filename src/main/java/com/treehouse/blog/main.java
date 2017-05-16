package com.treehouse.blog;

import com.treehouse.blog.model.*;
import spark.ModelAndView;
import spark.template.handlebars.HandlebarsTemplateEngine;

import java.lang.reflect.Array;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static spark.Spark.*;

/**
 * Created by jorgeotero on 4/28/17.
 */
public class main {
    public static void main(String[] args) {
        staticFileLocation("/public");

        BlogDao blogDao = new SimpleBlogDao();

        before((request, response) -> {
            if (null == request.attribute("model")) {
                Map<String, Object> model = new HashMap<>();
                request.attribute("model", model);
            }
            if (null != request.cookie("password")) {
                HashMap model = request.attribute("model");
                model.put("password", request.cookie("password"));
            }
        });

        before("/new", (request, response) -> {
            HashMap model = request.attribute("model");
            if (null == model.get("password")) {
                FlashMessage.setFlashMessage(request, "Please sign in first!");
                response.redirect("/password");
                halt();
            }
        });

        before("/edit/:slug", (request, response) -> {
            HashMap model = request.attribute("model");
            if (null == model.get("password")) {
                FlashMessage.setFlashMessage(request, "Please sign in first!");
                response.redirect("/password");
                halt();
            }
        });

        get("/", (request, response) -> {
            HashMap model = request.attribute("model");
            model.put("posts", blogDao.getPosts());
            return new ModelAndView(model, "index.hbs");
        }, new HandlebarsTemplateEngine());

        get("/index", (request, response) -> {
            response.redirect("/");
            return null;
        });

        get("/detail/:slug", (request, response) -> {
            HashMap model = request.attribute("model");
            model.put("post", blogDao.getPostsbySlug(request.params("slug")));
            return new ModelAndView(model, "detail.hbs");
        }, new HandlebarsTemplateEngine());

        get("/edit/:slug", (request, response) -> {
            HashMap model = request.attribute("model");
            model.put("post", blogDao.getPostsbySlug(request.params("slug")));
            return new ModelAndView(model, "edit.hbs");
        }, new HandlebarsTemplateEngine());

        post("/:slug/save", (request, response) -> {
            String title = request.queryParams("title");
            String body = request.queryParams("entry");
            Post post = blogDao.getPostsbySlug(request.params("slug"));
            post.setTitle(title);
            post.setBody(body);
            response.redirect(String.format("/detail/%s", request.params("slug")));
            return null;
        });

        get("/new", (request, response) -> {
            HashMap model = request.attribute("model");
            return new ModelAndView(model, "new.hbs");
        }, new HandlebarsTemplateEngine());

        post("/new", (request, response) -> {
            HashMap model = request.attribute("model");
            String title = request.queryParams("title");
            String body = request.queryParams("entry");
            Post post = new Post(title, body);
            String tags = request.queryParams("tags");
            List<String> tagList = Arrays.asList(tags.split(",+\\s?"));
            tagList.forEach(tag -> post.addTag(tag));
            blogDao.addPost(post);
            response.redirect("/");
            return null;
        });

        post("/comment/:slug", (request, response) -> {
            String name = request.queryParams("name");
            String comment = request.queryParams("comment");
            Post post = blogDao.getPostsbySlug(request.params("slug"));
            post.addComments(new Comment(name, comment));
            response.redirect(String.format("/detail/%s", post.getSlug()));
            return null;
        });

        get("/password", (request, response) -> {
            HashMap model = request.attribute("model");
            model.put("flash_message", FlashMessage.captureFlashMessage(request));
            return new ModelAndView(model, "password.hbs");
        }, new HandlebarsTemplateEngine());

        post("/password", (request, response) -> {
            String password = request.queryParams("password");
            HashMap model = request.attribute("model");
            if (password.equals(blogDao.getUser())) {
                response.cookie("password", password);
                response.redirect("/");
            } else {
                FlashMessage.setFlashMessage(request, "Incorrect password, please try again!");
                response.redirect("/password");
            }
            return null;
        });

        get("/delete/:slug", (request, response) -> {
            Post post = blogDao.getPostsbySlug(request.params("slug"));
            blogDao.deletePost(post);
            response.redirect("/");
            return null;
        });

        post("/delete/tag/:slug", (request, response) -> {
            Post post = blogDao.getPostsbySlug(request.params("slug"));
            post.deleteTag(request.queryParams("tag"));
            response.redirect(String.format("/edit/%s", request.params("slug")));
            return null;
        });

        post("/add/tag/:slug", (request, response) -> {
            Post post = blogDao.getPostsbySlug(request.params("slug"));
            post.addTag(request.queryParams("tag"));
            response.redirect(String.format("/edit/%s", request.params("slug")));
            return null;
        });
    }
}
//  TODO: add and deleted tags on a new entry.