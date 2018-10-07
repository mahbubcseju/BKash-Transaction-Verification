package mahbubcseju.bkashverificationsystem;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TableLayout;
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

public class MainActivity extends AppCompatActivity {

    DatabaseHelper myDb;
    private TableLayout mTableLayout;

    public static final String OTP_REGEX = "[0-9]{1,6}";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        SmsReceiver.bindListener(new SmsListener() {
            @Override
            public void messageReceived(String messageText) {

                String phn=findPhn(messageText);
                String date1=finddate(messageText);
                String bal=findbal( messageText);
                String tid=findtid( messageText);
                myDb.insertData(phn,date1,bal,tid);
                init();

            }
        });

    }
    public void init()
    {
        mTableLayout = (TableLayout) findViewById(R.id.table);
        mTableLayout.setStretchAllColumns(true);
        


    }
    public String findPhn(String x)
    {
        String res="";
        for(int i=0;i<x.length();i++)
        {
            if(i+13<x.length())
            if(x.charAt(i)=='f'&&x.charAt(i+1)=='r'&&x.charAt(i+2)=='o'&&x.charAt(i+3)=='m')
            {

                for(int j=i+5;j<=i+13;j++)res+=x.charAt(j);
                return res;
            }
        }
        return res;
    }
    public String finddate(String x)
    {
        String res="";
        for(int i=0;i<x.length();i++)
        {
            if(i+1<x.length())
                if(x.charAt(i)=='a'&&x.charAt(i+1)=='t')
                {

                    for(int j=i+3;j<x.length();j++)res+=x.charAt(j);
                    return res;
                }
        }
        return res;
    }
    public String findbal(String x)
    {
        String res="";
        for(int i=0;i<x.length();i++)
        {
            if(i+1<x.length())
                if(x.charAt(i)=='T'&&x.charAt(i+1)=='K')
                {

                    for(int j=i+3;j<x.length()&&x.charAt(j)!=' '&&x.charAt(j)!='\0';j++)res+=x.charAt(j);
                    return res;
                }
        }
        return res;
    }
    public String findtid(String x)
    {
        String res="";
        for(int i=0;i<x.length();i++)
        {
            if(i+4<x.length())
                if(x.charAt(i)=='T'&&x.charAt(i+1)=='r'&&x.charAt(i+2)=='x'&&x.charAt(i+3)=='I'&&x.charAt(i+4)=='D')
                {

                    for(int j=i+6;j<x.length()&&x.charAt(j)!=' ';j++)res+=x.charAt(j);
                    return res;
                }
        }
        return res;
    }

}

