package broadcast;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.util.Log;

public class bootReceiver extends BroadcastReceiver {
    final static  String Tag = "BootReceiver";
    @Override
    public void onReceive(Context context, Intent intent){
        Log.i(Tag, "BradCast intent: "+ intent.getAction());
    }
}
