package com.treehouse.blog.model;

import spark.Request;

/**
 * Created by jorgeotero on 5/1/17.
 */
public class FlashMessage {
    public static String FLASH_MESSAGE_KEY = "flash_message";

    public static void setFlashMessage(Request request, String message) {
        request.session().attribute(FLASH_MESSAGE_KEY, message);
    }

    private static String getFlashMessage(Request request) {
        if (null == request.session(false)) {
            return null;
        }
        if (!request.session().attributes().contains(FLASH_MESSAGE_KEY)) {
            return null;
        }
        return (String) request.session().attribute(FLASH_MESSAGE_KEY);
    }

    public static String captureFlashMessage(Request request) {
        String flashMessage = getFlashMessage(request);
        if (null != flashMessage) {
            request.session().removeAttribute(FLASH_MESSAGE_KEY);
        }
        return flashMessage;
    }
}
