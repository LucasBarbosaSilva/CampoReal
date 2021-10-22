package edge.utils;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class DateUtils {
    public static Date getDateDiffDays(int diffDays) {
        Calendar calendar = Calendar.getInstance();
        calendar.add(Calendar.DAY_OF_MONTH, diffDays);
        return calendar.getTime();
    }

    public static String getDateFomated(Date date) {
        DateFormat format = new SimpleDateFormat("dd/MM/YYYY");
        return format.format(date);
    }
}