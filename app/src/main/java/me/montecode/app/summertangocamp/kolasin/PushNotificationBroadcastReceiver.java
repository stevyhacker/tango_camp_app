package me.montecode.app.summertangocamp.kolasin;

import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.widget.Toast;

import com.parse.ParsePushBroadcastReceiver;

import org.json.JSONException;
import org.json.JSONObject;

/**
 * Created by stevyhacker on 16.6.15..
 */
public class PushNotificationBroadcastReceiver extends ParsePushBroadcastReceiver {


    @Override
    public void onPushOpen(Context context, Intent intent) {
        Log.e("Push", "Clicked");
        Intent i = new Intent(context, MainActivity.class);
        i.putExtra("notification", "parse");
        i.putExtras(intent.getExtras());
        i.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        context.startActivity(i);
    }

    @Override
    public void onPushReceive(Context context, Intent intent) {
        JSONObject json = null;
        try {
            json = new JSONObject(intent.getExtras().getString("com.parse.Data"));
        } catch (JSONException e) {
            e.printStackTrace();
        }
        if (json != null) {
            Toast.makeText(context, json.toString(), Toast.LENGTH_SHORT).show();
            Log.e("WORKS", "YEAH");
            Log.e("JSON PARSE DATA ", json.toString());
        }



        super.onPushReceive(context,intent);//disable this line for silent pushes

    }


}
