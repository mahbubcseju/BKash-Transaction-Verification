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

import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.telephony.SmsMessage;

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
            Bundle data  = intent.getExtras();

            Object[] pdus = (Object[]) data.get("pdus");

            for(int i=0;i<pdus.length;i++){
                SmsMessage smsMessage = SmsMessage.createFromPdu((byte[]) pdus[i]);

                String sender = smsMessage.getDisplayOriginatingAddress();
                //Check the sender to filter messages which we require to read

                if (sender.equals("GADGETSAINT"))
                {

                    String messageBody = smsMessage.getMessageBody();

                    //Pass the message text to interface
                    mListener.messageReceived(messageBody);

                }
            }

        }

        public static void bindListener(SmsListener listener) {
            mListener = listener;
        }
    }

