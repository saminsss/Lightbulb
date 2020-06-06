package com.myapp.samin.lightbulb;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.widget.Toast;

public class MyReceiver extends BroadcastReceiver {
    public MyReceiver() {
    }

    @Override
    public void onReceive(Context context, Intent intent) {
        String toastMsg = "Press anywhere";
        Toast.makeText(context, toastMsg, Toast.LENGTH_LONG).show();
    }
}
