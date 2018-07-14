package com.teja_kummarikuntla.myphonedetails;

import android.Manifest;
import android.app.Activity;
import android.content.Context;
import android.content.pm.PackageManager;
import android.net.wifi.WifiInfo;
import android.net.wifi.WifiManager;
import android.provider.Settings;
import android.os.Build;
import android.support.annotation.NonNull;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.telephony.TelephonyManager;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import java.net.NetworkInterface;
import java.util.Collections;
import java.util.List;


public class MainActivity extends AppCompatActivity {
    Button mstartButton;
    TextView mvarText;
    String info;
    String strPhoneType;
    static final int PERMISSION_READ_STATE = 123;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

    }


    public void start(View view) {
        int permissionCheck = ContextCompat.checkSelfPermission(this, Manifest.permission.READ_PHONE_STATE);

        if (permissionCheck == PackageManager.PERMISSION_GRANTED) {
            myTelephoneyManager();
        } else {
            ActivityCompat.requestPermissions(this,new String[]{Manifest.permission.READ_PHONE_STATE},PERMISSION_READ_STATE);
        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);

        switch (requestCode) {
            case PERMISSION_READ_STATE:
                if (grantResults.length >= 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                    myTelephoneyManager();
                } else {
                    Toast.makeText(this, "Permissions Denied!", Toast.LENGTH_SHORT).show();
                }
        }

    }


    private void myTelephoneyManager() {
        TelephonyManager manager = (TelephonyManager) getSystemService(Context.TELEPHONY_SERVICE);
        int phoneType = manager.getPhoneType();
        switch (phoneType) {
            case (TelephonyManager.PHONE_TYPE_CDMA):
                strPhoneType = "CDMA";
                 break;

            case (TelephonyManager.PHONE_TYPE_GSM):
                strPhoneType = "GSM";
                break;
            case (TelephonyManager.PHONE_TYPE_NONE):
                strPhoneType = "NONE";
                break;
        }


        boolean isRoming = manager.isNetworkRoaming();
        String strPhoneType1 = strPhoneType;
        String IMEInumber = manager.getDeviceId();
        String subscriberID = manager.getSubscriberId();
        String simSerialNumber = manager.getSimSerialNumber();
        String networkCountyIso = manager.getNetworkCountryIso();
        String simCountyIso = manager.getSimCountryIso();
        String softwareVersion = manager.getDeviceSoftwareVersion();
        String voiceMailnumber = manager.getVoiceMailNumber();
        String PhoneModel = android.os.Build.MODEL;
        String AndroidVersion = android.os.Build.VERSION.RELEASE;
        String bootLoader = Build.BOOTLOADER;
        String brandProduct = Build.BRAND;
        //String myMAC_ADRESS = getMacAddr();

        info = brandProduct + " "+PhoneModel +" Details: \n";

        info+="\n Device Brand: "+brandProduct;
        info+="\n Phone Model: "+PhoneModel;
        info+="\n IMEI Number: "+IMEInumber;
        info+="\n Android Version: "+AndroidVersion;
        info+="\n Software Version: "+softwareVersion;
        info+="\n SubscriberID: "+subscriberID;
        info+="\n Phone Network Type: "+strPhoneType1;
        info+="\n Network Country Iso: "+networkCountyIso;
        info+="\n SIM Country Iso: "+simCountyIso;
        info+="\n In Romaing: "+isRoming;
        info+="\n Voice Mail Number: "+voiceMailnumber;
        info+="\n Bootloader: "+bootLoader;
        //info+="\n MAC Adress: "+myMAC_ADRESS;


        mstartButton = (Button) findViewById(R.id.iStartButton);
        mvarText = (TextView) findViewById(R.id.iTextView);
        mvarText.setText(info);



    }



}
