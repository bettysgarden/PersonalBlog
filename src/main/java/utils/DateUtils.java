package utils;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class DateUtils {

    /**
     * date Transformation String (datetime) 2017-09-18 12:17:18
     *
     * @param date
     * @return
     */
    public static String getFormatDate(Date date) {

        DateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        return format.format(date);
    }

    /**
     * String(datetime) transformable date
     *
     * @param date
     * @return
     * @throws ParseException
     */
    public static Date getDate(String date) throws ParseException {
        DateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        return format. parse(date);
    }
}
