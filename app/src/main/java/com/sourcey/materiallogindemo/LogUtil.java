package com.sourcey.materiallogindemo;

import android.util.Log;

public class LogUtil {

    /***
     * 控制台日志
     * @param msg	内容
     */
    public static void logConsole(String msg){
        if(SystemAttributes.isDebug){
            System.out.println(msg);
        }
    }

    /***
     *	带tag的日志
     * @param tag
     * @param msg
     */
    public static void logByTag(String tag, String msg){
        if(SystemAttributes.isDebug){
            Log.i(tag, msg);
        }
    }
}
