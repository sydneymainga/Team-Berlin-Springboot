package com.spaceyatech.berlin.utilities;

import java.text.SimpleDateFormat;
import java.util.Date;
/**
 * Author : sydney mainga
 * Date : 23rd/nov/2023
 * purpose: this class is meant for reuse by other developers contributing to space ya tech project in accordance with Do not Repeat(DRY)
 * Yourself principle
 * */
public class Dry {

    public static final String getCurrentDate() {
        String pattern = "yyyy-MM-dd hh:mm:ss";
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat(pattern);
        String date = simpleDateFormat.format(new Date());

        return date;
    }

    /*SimpleDateFormat formatter = new SimpleDateFormat("yyyy-mm-dd hh:mm:ss");
    Date date = new Date();
    formatter.format(date)*/


}
