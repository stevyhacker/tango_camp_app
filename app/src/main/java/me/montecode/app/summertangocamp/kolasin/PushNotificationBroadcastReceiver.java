package me.montecode.app.summertangocamp.kolasin;

import android.content.Context;
import android.content.Intent;
import android.util.Log;

import com.parse.ParsePushBroadcastReceiver;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.nio.charset.Charset;

import io.realm.Realm;
import io.realm.RealmQuery;
import io.realm.RealmResults;

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
        boolean silent = false;

        try {
            json = new JSONObject(intent.getExtras().getString("com.parse.Data"));
        } catch (JSONException e) {
            e.printStackTrace();
        }

        Realm realm = Realm.getInstance(context.getApplicationContext());
        RealmQuery<Schedule> query = realm.where(Schedule.class);

        if (json != null) {

            Log.d("JSON PARSE DATA ", json.toString());

            try {
                if (json.getString("silent").equalsIgnoreCase("true")) {
                    silent = true;
                }

            } catch (JSONException e) {
                e.printStackTrace();
            }

            try {
                RealmResults<Schedule> realmResults = query.contains("push_id", json.getString("push_id"), true).findAll();

                if (!realmResults.isEmpty()) {

                    InputStream stream = new ByteArrayInputStream(json.toString().getBytes(Charset.forName("UTF-8")));

                    realm.beginTransaction();
                    realm.createOrUpdateObjectFromJson(Schedule.class, stream);
                    realm.commitTransaction();

                }
            } catch (JSONException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }


        }


        if (!silent) {
            super.onPushReceive(context, intent);//disable this line for silent pushes
        }

    }


}
