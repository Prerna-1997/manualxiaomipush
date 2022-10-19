package com.example.manualxiaomipush;

import androidx.appcompat.app.AppCompatActivity;

import android.app.NotificationManager;
import android.os.Bundle;

import com.clevertap.android.sdk.CleverTapAPI;

import com.clevertap.android.sdk.pushnotification.PushConstants;
import com.xiaomi.mipush.sdk.MiPushClient;

public class MainActivity extends AppCompatActivity {

    CleverTapAPI clevertapDefaultInstance;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        clevertapDefaultInstance = CleverTapAPI.getDefaultInstance(getApplicationContext());
        CleverTapAPI.setDebugLevel(CleverTapAPI.LogLevel.VERBOSE);
        CleverTapAPI.enableXiaomiPushOn(PushConstants.ALL_DEVICES);

        CleverTapAPI.createNotificationChannel(getApplicationContext(),"xiaomi","xiaomi","Your Channel Description", NotificationManager.IMPORTANCE_MAX,true);

        MiPushClient.registerPush(this, "2882303761520518752", "5582051899752");
    }
}