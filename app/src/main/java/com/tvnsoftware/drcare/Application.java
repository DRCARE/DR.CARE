package com.tvnsoftware.drcare;

import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.Intent;

import com.tvnsoftware.drcare.model.users.User;

import java.util.List;

/**
 * Created by Samn on 02-Aug-17.
 */

public class Application extends android.app.Application {
    public static AlarmManager alarmManager;
    public static PendingIntent pendingIntent;
    public static Intent alarm_intent;


    public static List<User> users =  User.getUserList();
}
