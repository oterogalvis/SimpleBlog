package com.treehouse.blog;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created by jorgeotero on 4/29/17.
 */
public class Play {
    public static void main(String[] args) {
        Date date = new Date();
        String dateformat = "MMMM dd, yyyy 'at' h:m";
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat(dateformat);
        System.out.printf("%s", simpleDateFormat.format(date));
    }
}
