package me.letitfly.mat.utils;

import android.annotation.SuppressLint;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by Trumeet on 2017/3/16.
 * @author Trumeet
 */

public class FormatUtils {
    private static final String TAG = "FormatUtil";

    @SuppressLint("SimpleDateFormat")
    public static Date convertDateLine (String dateLine) {
        // TODO: Finish it.
        try {
            Logger.i(TAG, "formatDate");
            return new SimpleDateFormat("yyyy-MM-dd k:m:s")
                    .parse(dateLine);
        } catch (ParseException e) {
            Logger.i(TAG, "try match 7&nbsp;天前");
            // 7&nbsp;天前
            Matcher matcher = Pattern.compile("\\d+&nbsp;天前").matcher(dateLine);
            if (matcher.matches()) {
                Logger.i(TAG, "7&nbsp;天前 matches");
                if (matcher.find()) {
                    Logger.i(TAG, "7&nbsp;天前 find");
                    int days = Integer.parseInt(dateLine.substring(matcher.start()
                            , matcher.end()));
                    return getDateBefore(new Date(), days);
                }
            } else {
                Logger.i(TAG, "try match 昨天&nbsp;23:01");
                // 昨天&nbsp;23:01
                Matcher m1 = Pattern.compile("昨天&nbsp;\\d+:\\d+").matcher(dateLine);
                if (m1.matches()) {
                    Logger.i(TAG, "昨天&nbsp;23:01 matches");
                    int hour = -1;
                    int time = -1;
                    while (m1.find()) {
                        if (hour == -1) {
                            hour = Integer.parseInt(dateLine.substring(m1.start()
                                    , m1.end()));
                        } else if (time == -1) {
                            time = Integer.parseInt(dateLine.substring(m1.start()
                                    , m1.end()));
                        } else break;
                    }
                    Logger.i(TAG, "昨天&nbsp;23:01 hour:" + hour + "time:" + time);
                    Date date = new Date();
                    date.setHours(hour);
                    date.setTime(time);
                    return getDateBefore(date, date.getDay() - 1);
                }
            }
            Logger.e(TAG, "Fail to parse date!", e);
            return new Date();
        }
    }
    /**
     * 得到几天前的时间
     * @param d
     * @param day
     * @return
     */
    private static Date getDateBefore(Date d,int day){
        Calendar now =Calendar.getInstance();
        now.setTime(d);
        now.set(Calendar.DATE,now.get(Calendar.DATE)-day);
        return now.getTime();
    }
}
