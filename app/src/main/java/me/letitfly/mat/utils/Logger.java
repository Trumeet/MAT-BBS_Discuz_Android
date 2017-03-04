package me.letitfly.mat.utils;

import android.util.Log;

import static me.letitfly.mat.BuildConfig.DEBUG_LOG;

/**
 * Created by Trumeet on 2017/3/2.
 * Log util
 * @author Trumeet
 */

public class Logger {
    public static void i (String tag, String message) {
        if (DEBUG_LOG) Log.i(tag, message);
    }

    public static void i (String tag, String message, Throwable throwable) {
        if (DEBUG_LOG) Log.i(tag, message, throwable);
    }

    public static void w (String tag, String message) {
        if (DEBUG_LOG) Log.w(tag, message);
    }

    public static void w (String tag, String message, Throwable throwable) {
        if (DEBUG_LOG) Log.w(tag, message, throwable);
    }

    public static void e (String tag, String message) {
        if (DEBUG_LOG) Log.e(tag, message);
    }

    public static void e (String tag, String message, Throwable throwable) {
        if (DEBUG_LOG) Log.e(tag, message, throwable);
    }
}
