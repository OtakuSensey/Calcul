package com.example.calculator;

import android.app.Activity;
import android.content.Intent;

public class ThemeManager {

    final static int THEME_1 = 0;
    final static int THEME_2 = 1;
    private static int mTheme = THEME_1;

static void changeTo(Activity activity, int theme)
{
    if(mTheme !=theme) {
        mTheme = theme;
        activity.finish();
        Intent intent = new Intent(activity.getApplicationContext(), about.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        activity.startActivity(intent);
        Intent intent2 = new Intent(activity.getApplicationContext(), MainActivity.class);
        intent2.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        activity.startActivity(intent2);
    }
}
static void setTheme(Activity activity)
{
    switch(mTheme)
    {
        default:
        case THEME_1:
            activity.setTheme(R.style.Theme_Calculator);
            break;
        case THEME_2:
            activity.setTheme(R.style.AppNewThem);
            break;

    }
}
}