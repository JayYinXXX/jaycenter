package center.jay.jaycenter.util;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

/**
 * 日期计算
 */
public class DateUtil {
    // 距今天的天数，今天返回0，昨天返回1，前天返回2...
    public static int getIntervalDays(Date obj) {
        Date today = new Date();
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        String s = sdf.format(today);
        try {
            today = sdf.parse(s);
        } catch(Exception e) {
            e.printStackTrace();
        }
        long interval = (today.getTime() - obj.getTime());
        // 今天和今天之后返回0
        if (interval <= 0) {
            return 0;
        }
        long oneDay = 24 * 60 * 60 * 1000;
        int days = (int) (interval / oneDay) + 1;
        return days;
    }

    // 计算指定的时间是星期几
    public static int dayOfWeek(Date date) {
        Calendar cal = Calendar.getInstance();
        cal.setTime(date);
        int d = cal.get(Calendar.DAY_OF_WEEK) - 1;
        if (d <= 0) {
            d = 7;
        }
        return d;
    }
}
