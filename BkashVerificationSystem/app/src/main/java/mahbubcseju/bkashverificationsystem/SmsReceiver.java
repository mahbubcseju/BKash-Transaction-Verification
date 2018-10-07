package mahbubcseju.bkashverificationsystem;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.telephony.TelephonyManager;
import android.util.Log;
import android.Manifest;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.app.Activity;
import android.telephony.TelephonyManager;
import android.Manifest.permission;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.telephony.SmsMessage;
import android.provider.Telephony;
import android.util.Log;
import android.view.Menu;
import android.view.View;

import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;
/**
 * Created by Student on 10/7/2018.
 */


public class SmsReceiver extends BroadcastReceiver {

    //interface
    private static SmsListener mListener;

    @Override
    public void onReceive(Context context, Intent intent) {
        final String tag =  ".onReceive";
        Bundle bundle = intent.getExtras();
        if (bundle == null) {
            Log.w(tag, "BroadcastReceiver failed, no intent data to process.");
            return;
        }
//        if (intent.getAction().equals(SMS_RECEIVED)) {
            Log.d(tag, "SMS_RECEIVED");

            String smsOriginatingAddress, smsDisplayMessage;

            // You have to CHOOSE which code snippet to use NEW (KitKat+), or legacy
            // Please comment out the for{} you don't want to use.

            // API level 19 (KitKat 4.4) getMessagesFromIntent
            for (SmsMessage message : Telephony.Sms.Intents.
                    getMessagesFromIntent(intent)) {
                Log.d(tag, "KitKat or newer");
                if (message == null) {
                    Log.e(tag, "SMS message is null -- ABORT");
                    break;
                }
                smsOriginatingAddress = message.getDisplayOriginatingAddress();
                //see getMessageBody();
                if(smsOriginatingAddress.equals("bKash")) {
                    smsDisplayMessage = message.getDisplayMessageBody();
                    mListener.messageReceived(smsDisplayMessage);
                }
            }

            // Processing SMS messages the OLD way, before KitKat,
            // this WILL work on KitKat or newer Android
            // PDU is a “protocol data unit”, which is the industry
            // format for an SMS message
            Object[] data = (Object[]) bundle.get("pdus");
            for (Object pdu : data) {
                Log.d(tag, "legacy SMS implementation (before KitKat)");
                SmsMessage message = SmsMessage.createFromPdu((byte[]) pdu);
                if (message == null) {
                    Log.e(tag, "SMS message is null -- ABORT");
                    break;
                }
                smsOriginatingAddress = message.getDisplayOriginatingAddress();
                // see getMessageBody();
                if(smsOriginatingAddress.equals("bKash")) {
                    smsDisplayMessage = message.getDisplayMessageBody();
                    mListener.messageReceived(smsDisplayMessage);
                }
            }
//        } // onReceive method

    }

    public static void bindListener(SmsListener listener) {
        mListener = listener;
    }
}

