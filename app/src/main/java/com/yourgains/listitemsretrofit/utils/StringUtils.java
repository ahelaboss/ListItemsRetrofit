package com.yourgains.listitemsretrofit.utils;

import org.jetbrains.annotations.Nullable;

/**
 * Created by alexeyshishov
 * on 2/3/19.
 */
public class StringUtils {

    public static boolean isNotEmpty(@Nullable String str) {
        return str != null && str.trim().length() > 0;
    }

    public static boolean isEmpty(@Nullable String str) {
        return str == null || str.trim().length() == 0;
    }
}
