package com.example.lenovo.base.utils;

import android.app.Activity;

import java.util.ArrayList;
import java.util.List;


public class ActivityCollector {
    private static List<Activity> sActivityList = new ArrayList<Activity>();

    public static void addActivity(Activity activity) {
        sActivityList.add(activity);
    }

    public static void removeActivity(Activity activity) {
        sActivityList.remove(activity);
    }

    public static Activity getTopActivity() {
        return sActivityList.size() == 0 ? null : sActivityList.get(sActivityList.size() - 1);
    }

    public static void removeAllActivity() {
        for (Activity activity : sActivityList) {
            if (!activity.isFinishing())
                activity.finish();
        }
        sActivityList.clear();
    }
}
