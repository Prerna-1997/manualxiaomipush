package com.example.manualxiaomipush;

import android.content.Context;
import android.os.Bundle;

import com.clevertap.android.sdk.Utils;
import com.google.android.exoplayer2.util.Log;
import com.xiaomi.mipush.sdk.ErrorCode;
import com.xiaomi.mipush.sdk.MiPushClient;
import com.xiaomi.mipush.sdk.MiPushCommandMessage;
import com.xiaomi.mipush.sdk.MiPushMessage;
import com.xiaomi.mipush.sdk.PushMessageReceiver;

import com.clevertap.android.sdk.CleverTapAPI;

import org.json.JSONException;

import java.util.List;

public class XiaomiPush extends PushMessageReceiver {
    @Override
    public void onReceivePassThroughMessage(Context context, MiPushMessage miPushMessage) {
        try {
            Bundle extras = Utils.stringToBundle(miPushMessage.getContent());
            CleverTapAPI.createNotification(context, extras);
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void onCommandResult(Context context, MiPushCommandMessage message) {
        String command = message.getCommand();
        List<String> arguments = message.getCommandArguments();
        String mRegId = ((arguments != null && arguments.size() > 0) ? arguments.get(0) : null);
        String xiaomiRegion = MiPushClient.getAppRegion(context);
        if (MiPushClient.COMMAND_REGISTER.equals(command)) {
            if (message.getResultCode() == ErrorCode.SUCCESS) {
                Log.d("MiReceiver", "Xiaomi token - " + mRegId);
                CleverTapAPI.getDefaultInstance(context).pushXiaomiRegistrationId(mRegId, xiaomiRegion, true);
            }
        }
    }

    @Override
    public void onReceiveRegisterResult(Context context, MiPushCommandMessage message) {
        String command = message.getCommand();
        List<String> arguments = message.getCommandArguments();
        String mRegId = ((arguments != null && arguments.size() > 0) ? arguments.get(0) : null);
        String xiaomiRegion = MiPushClient.getAppRegion(context);
        if (MiPushClient.COMMAND_REGISTER.equals(command)) {
            if (message.getResultCode() == ErrorCode.SUCCESS) {
                Log.d("MiReceiver", "Xiaomi token - " + mRegId);
                CleverTapAPI.getDefaultInstance(context).pushXiaomiRegistrationId(mRegId, xiaomiRegion, true);
            }
        }
    }
}
