package com.treehouse.blog;

import spark.ModelAndView;
import spark.template.handlebars.HandlebarsTemplateEngine;

import static spark.Spark.get;
import static spark.Spark.staticFileLocation;

/**
 * Created by jorgeotero on 4/28/17.
 */
public class main {
    public static void main(String[] args) {
        staticFileLocation("/public");

        get("/", (request, response) -> {
            return new ModelAndView(null, "index.hbs");
        }, new HandlebarsTemplateEngine());
    }
}
