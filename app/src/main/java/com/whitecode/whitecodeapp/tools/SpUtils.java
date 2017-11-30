package com.whitecode.whitecodeapp.tools;

import android.content.Context;
import android.content.SharedPreferences;

/**
 * SharedPreferences工具类
 */
public class SpUtils {

    private static final String SP_DATA = "sp_data";

    public static SharedPreferences getSharedPreferences(Context context) {
        return context.getSharedPreferences(SP_DATA,Context.MODE_PRIVATE);
    }

    public static Boolean getBoolean(Context context,String key) {
        SharedPreferences sp = getSharedPreferences(context);
        Boolean result = sp.getBoolean(key,false);
        return result;
    }

    public static void putBoolean(Context context,String key,Boolean value) {
        SharedPreferences sp = getSharedPreferences(context);
        SharedPreferences.Editor editor = sp.edit();
        editor.putBoolean(key,value);
        editor.commit();
    }

}
