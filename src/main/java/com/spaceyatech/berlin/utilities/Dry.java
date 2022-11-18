package com.spaceyatech.berlin.utilities;

import java.text.SimpleDateFormat;
import java.util.Date;

public class Dry {

    public static final String getCurrentDate() {
        String pattern = "yyyy-mm-dd hh:mm:ss";
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat(pattern);
        String date = simpleDateFormat.format(new Date());

        return date;
    }


}
