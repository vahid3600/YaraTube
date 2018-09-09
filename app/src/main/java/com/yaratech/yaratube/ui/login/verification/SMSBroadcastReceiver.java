package com.yaratech.yaratube.ui.login.verification;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.provider.Telephony;
import android.telephony.SmsMessage;
<<<<<<< HEAD
import android.util.Log;
=======
>>>>>>> daa60e45d156f167875e34092ae171504d245a65

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

<<<<<<< HEAD
            Log.e("Tag",smsBody);
=======
>>>>>>> daa60e45d156f167875e34092ae171504d245a65
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

<<<<<<< HEAD
    public void unbindListener() {
        mlistener = null;
    }

=======
>>>>>>> daa60e45d156f167875e34092ae171504d245a65
    interface SMSBroadcastListener {
        void onTextReceived(String text);
    }
}