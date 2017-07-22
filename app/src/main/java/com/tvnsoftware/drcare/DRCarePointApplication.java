package com.tvnsoftware.drcare;

import android.app.Application;
import android.content.res.Configuration;

/**
 * Created by Thieusike on 7/23/2017.
 */

public class DRCarePointApplication extends Application {
    private static final String TAG = "DRCarePointApplication";
    private static DRCarePointApplication scApplication;
    public static DRCarePointApplication getInstance(){
        if(scApplication == null){
            scApplication = new DRCarePointApplication();
        }

        return  scApplication;
    }
    @Override
    public void onCreate() {
        super.onCreate();
        scApplication = this;
    }
    @Override
    public void onTerminate (){
        super.onTerminate();
    }

    @Override
    public void onConfigurationChanged (Configuration newConfig){
        super.onConfigurationChanged(newConfig);
    }
}
