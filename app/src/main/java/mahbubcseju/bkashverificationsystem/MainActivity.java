package mahbubcseju.bkashverificationsystem;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TableLayout;
<<<<<<< HEAD
import android.database.Cursor;
import android.widget.TextView;
import android.widget.TableRow;

=======
>>>>>>> b81b68751bc44f779ffab8e4f1558e6fe62e65ef
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

<<<<<<< HEAD
    DatabaseHelper myDb=new DatabaseHelper(this);
    private TableLayout mTableLayout;
//    private int MY_PERMISSIONS_REQUEST_SMS_RECEIVE = 10;
    public static final String OTP_REGEX = "[0-9]{1,6}";
    public static int cnt=0;
//    ActivityCompat.requestPermissions(this,new String[]{Manifest.permission.RECEIVE_SMS},MY_PERMISSIONS_REQUEST_SMS_RECEIVE);
//    @Override
//    public void onRequestPermissionsResult(int requestCode,  String[] permissions, int[] grantResults) {
//        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
//        if (requestCode == MY_PERMISSIONS_REQUEST_SMS_RECEIVE) {
//            // YES!!
//            Log.i("TAG", "MY_PERMISSIONS_REQUEST_SMS_RECEIVE --> YES");
//        }
//    }

    private void requestSmsPermission() {
        String permission = Manifest.permission.RECEIVE_SMS;
        int grant = ContextCompat.checkSelfPermission(this, permission);
        if ( grant != PackageManager.PERMISSION_GRANTED) {
            String[] permission_list = new String[1];
            permission_list[0] = permission;
            ActivityCompat.requestPermissions(this, permission_list, 1);
        }
    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);

//        System.out.println("jkfre");
        setContentView(R.layout.activity_main);
       requestSmsPermission();
        mTableLayout = (TableLayout) findViewById(R.id.table);
        mTableLayout.setStretchAllColumns(true);
        TableRow row=new TableRow(this);TextView tv1=new TextView(this);TextView tv2=new TextView(this);TextView tv3=new TextView(this);TextView tv4=new TextView(this);
        tv1.setText("______");tv2.setText("______");tv3.setText("______");tv4.setText("______");
        row.addView(tv1);row.addView(tv2);row.addView(tv3);row.addView(tv4);

        mTableLayout.addView(row);
       // System.out.println("jkfre");
        init();
=======
    DatabaseHelper myDb;
    private TableLayout mTableLayout;

    public static final String OTP_REGEX = "[0-9]{1,6}";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
>>>>>>> b81b68751bc44f779ffab8e4f1558e6fe62e65ef
        SmsReceiver.bindListener(new SmsListener() {
            @Override
            public void messageReceived(String messageText) {

                String phn=findPhn(messageText);
                String date1=finddate(messageText);
                String bal=findbal( messageText);
                String tid=findtid( messageText);
<<<<<<< HEAD

                if(check(tid)==false) {
                   addintable(phn,date1,bal,tid);
                    myDb.insertData(phn, date1, bal, tid);

                }
=======
                myDb.insertData(phn,date1,bal,tid);
                init();
>>>>>>> b81b68751bc44f779ffab8e4f1558e6fe62e65ef

            }
        });

    }
<<<<<<< HEAD
    public void addintable(String phn,String date1,String bal,String tid)
    {
        if(check(tid)==false) {
            TableRow row = new TableRow(this);TextView tv1 = new TextView(this);TextView tv2 = new TextView(this);TextView tv3 = new TextView(this);TextView tv4 = new TextView(this);
            tv1.setText(phn);tv2.setText(tid);tv3.setText(bal);tv4.setText(date1);
            row.addView(tv1);row.addView(tv2);row.addView(tv3);row.addView(tv4);
            mTableLayout.addView(row);
        }
    }
    public boolean check(String tid)
    {
        Cursor cursor= myDb.getAllData();
        if (cursor.moveToFirst()) {
            while (!cursor.isAfterLast()) {
                String tid1 = cursor.getString(cursor.getColumnIndex("TID"));
                //init();
                if(tid1.equals(tid)){

                    return true;}
                cursor.moveToNext();
            }
        }
        return false;
    }
    public void init()
    {
//        myDb.insertData("A","B","C","D");
//        System.out.println("jkfre");
        Cursor cursor= myDb.getAllData();
        if (cursor.moveToFirst()) {
            while (!cursor.isAfterLast()) {
//
                String phn = cursor.getString(cursor.getColumnIndex("PHN"));
                String date1 = cursor.getString(cursor.getColumnIndex("DATE"));
                String bal = cursor.getString(cursor.getColumnIndex("BAL"));
                String tid = cursor.getString(cursor.getColumnIndex("TID"));

                TableRow row=new TableRow(this);
                TextView tv1=new TextView(this);
                TextView tv2=new TextView(this);
                TextView tv3=new TextView(this);
                TextView tv4=new TextView(this);
                tv1.setText(phn);
                tv2.setText(tid);
                tv3.setText(bal);
                tv4.setText(date1);
                row.addView(tv1);row.addView(tv2);row.addView(tv3);row.addView(tv4);

                mTableLayout.addView(row);
                //list.add(name);
                cursor.moveToNext();
            }
        }
=======
    public void init()
    {
        mTableLayout = (TableLayout) findViewById(R.id.table);
        mTableLayout.setStretchAllColumns(true);
        

>>>>>>> b81b68751bc44f779ffab8e4f1558e6fe62e65ef

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
<<<<<<< HEAD
                if(x.charAt(i)=='T'&&x.charAt(i+1)=='k')
=======
                if(x.charAt(i)=='T'&&x.charAt(i+1)=='K')
>>>>>>> b81b68751bc44f779ffab8e4f1558e6fe62e65ef
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

