package saurabh.digipodium.broadcastreciever;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;

public class MessageReceiver extends BroadcastReceiver {

    @Override
    public void onReceive(Context context, Intent intent) {
        Intent applaunch = new Intent(context, MainActivity.class);
        context.startActivity(applaunch);
    }
}
