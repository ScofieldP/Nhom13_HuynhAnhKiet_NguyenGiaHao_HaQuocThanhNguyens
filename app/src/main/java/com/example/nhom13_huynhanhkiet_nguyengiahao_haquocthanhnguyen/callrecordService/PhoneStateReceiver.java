package com.example.nhom13_huynhanhkiet_nguyengiahao_haquocthanhnguyen.callrecordService;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.telephony.TelephonyManager;
import android.util.Log;

import com.example.nhom13_huynhanhkiet_nguyengiahao_haquocthanhnguyen.BaseApplication;
import com.example.nhom13_huynhanhkiet_nguyengiahao_haquocthanhnguyen.PrefHelper;
import com.example.nhom13_huynhanhkiet_nguyengiahao_haquocthanhnguyen.database.CallDetailEntity;
import com.example.nhom13_huynhanhkiet_nguyengiahao_haquocthanhnguyen.database.CallDetailRepository;
import com.example.nhom13_huynhanhkiet_nguyengiahao_haquocthanhnguyen.utilites.CommonMethods;
import com.example.nhom13_huynhanhkiet_nguyengiahao_haquocthanhnguyen.utilites.Constants;

import javax.inject.Inject;

public class PhoneStateReceiver extends BroadcastReceiver {


    CallDetailRepository callDetailRepository;

    @Override
    public void onReceive(Context context, Intent intent) {
        Log.i("tntkhang", "PhoneStateReceiver - onReceive");
        try {
            Bundle extras = intent.getExtras();
            String state = extras.getString(TelephonyManager.EXTRA_STATE);

            if (state.equals(TelephonyManager.EXTRA_STATE_RINGING)) {
            } else if (state.equals(TelephonyManager.EXTRA_STATE_OFFHOOK)) {
                String phoneNumber = intent.getStringExtra(TelephonyManager.EXTRA_INCOMING_NUMBER);
                String recPath = startRecording(context, phoneNumber);
                Log.i("tntkhang", "recPath : " + recPath);

                PrefHelper.setVal(Constants.Prefs.CALL_RECORD_STARTED, !recPath.isEmpty());
            } else if (state.equals(TelephonyManager.EXTRA_STATE_IDLE)) {
                if (PrefHelper.getBooleanVal(Constants.Prefs.CALL_RECORD_STARTED, false)) {
                    String phoneNumber = intent.getStringExtra(TelephonyManager.EXTRA_INCOMING_NUMBER);
                    stopRecording(context, phoneNumber);
                }
            }
            Log.i("tntkhang", "PhoneStateReceiver - onReceive: " + state);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private String startRecording(Context context, String number) {
        String trimNumber = CommonMethods.removeSpaceInPhoneNo(number);
        Log.i("tntkhang", "startRecording - trimNumber: " + trimNumber);

        String time = CommonMethods.getTime();
        String date = CommonMethods.getDate();
        String path = CommonMethods.getPath();
        String outputPath = path + "/" + trimNumber + "_" + time + ".mp3";
//        String outputPath = path + "/" + trimNumber + "_" + time + ".mp4";
        Log.i("tntkhang", "outputPath: " + outputPath);

        Intent recordService = new Intent(context, CallRecorderService.class);
        recordService.putExtra(Constants.Prefs.PHONE_CALL_NUMBER, trimNumber);
        recordService.putExtra(Constants.Prefs.CALL_RECORD_PATH, outputPath);

        context.startService(recordService);

        String name = "";
        CallDetailEntity callDetailEntity = new CallDetailEntity(trimNumber, name, time, date, outputPath);
        callDetailRepository.insert(callDetailEntity);
        return outputPath;
    }

    private void stopRecording(Context context, String phoneNo) {
        Log.i("tntkhang", "stopRecording: " + phoneNo);
        context.stopService(new Intent(context, CallRecorderService.class));
    }
}
