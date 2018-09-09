package com.yaratech.yaratube.ui.login.verification;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.provider.Telephony;
import android.telephony.SmsMessage;

/**
 * Created by Vah on 9/7/2018.
 */

public class SMSBroadcastReceiver extends BroadcastReceiver {

    private final String serviceProviderNumber = "+98200049103";

    private static SMSBroadcastListener mlistener;

    @Override
    public void onReceive(Context context, Intent intent) {
        if (intent.getAction().equals(Telephony.Sms.Intents.SMS_RECEIVED_ACTION)) {
            String smsSender = "";
            String smsBody = "";

            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
                for (SmsMessage smsMessage : Telephony.Sms.Intents.getMessagesFromIntent(intent)) {
                    smsSender = smsMessage.getDisplayOriginatingAddress();
                    smsBody += smsMessage.getMessageBody();
                }
            }

            if (smsSender.equals(serviceProviderNumber)) {
                if (mlistener != null) {
                    smsBody = smsBody.replaceAll("\\D+", "");
                    mlistener.onTextReceived(smsBody);
                }
            }
        }
    }

    public void bindListener(SMSBroadcastListener listener) {
        mlistener = listener;
    }

    public void unbindListener() {
        mlistener = null;
    }

    interface SMSBroadcastListener {
        void onTextReceived(String text);
    }
}