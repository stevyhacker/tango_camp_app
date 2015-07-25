package me.montecode.app.summertangocamp.kolasin;

import android.app.Application;
import android.content.ContextWrapper;
import android.util.Log;

import com.parse.Parse;
import com.parse.ParseACL;
import com.parse.ParseCrashReporting;
import com.parse.ParseException;
import com.parse.ParseInstallation;
import com.parse.ParsePush;
import com.parse.ParseUser;
import com.parse.SaveCallback;
import com.pixplicity.easyprefs.library.Prefs;

import java.util.Locale;

public class TangoApplication extends Application {

    @Override
    public void onCreate() {
        super.onCreate();

        new Prefs.Builder()
                .setContext(this)
                .setMode(ContextWrapper.MODE_PRIVATE)
                .setPrefsName(getPackageName())
                .setUseDefaultSharedPreference(true)
                .build();

        // Initialize Crash Reporting.
        ParseCrashReporting.enable(this);

        // Enable Local Datastore.
        Parse.enableLocalDatastore(this);

        // Add your initialization code here
        Parse.initialize(this, getResources().getString(R.string.parse_app_id), getResources().getString(R.string.parse_client_key));
        ParseInstallation.getCurrentInstallation().saveInBackground();

        ParseUser.enableAutomaticUser();
        ParseACL defaultACL = new ParseACL();
        // Optionally enable public read access.
        // defaultACL.setPublicReadAccess(true);
        ParseACL.setDefaultACL(defaultACL, true);

        ParsePush.subscribeInBackground("", new SaveCallback() {
            @Override
            public void done(ParseException e) {
                if (e == null) {
                    Log.d("com.parse.push", "successfully subscribed to the broadcast channel.");
                } else {
                    Log.e("com.parse.push", "failed to subscribe for push", e);
                }
            }
        });

        if (!Prefs.getBoolean("languageSubscribed", false)) {
            if (Locale.getDefault().getLanguage().equalsIgnoreCase("sr") || Locale.getDefault().getLanguage().equalsIgnoreCase("bs") || Locale.getDefault().getLanguage().equalsIgnoreCase("hr")) {
                Prefs.putString("subscribedToChannel", "Srpski");

                ParsePush.subscribeInBackground("Srpski", new SaveCallback() {
                    @Override
                    public void done(ParseException e) {
                        if (e == null) {
                            Log.d("com.parse.push", "successfully subscribed to the broadcast channel.");

                        } else {
                            Log.e("com.parse.push", "failed to subscribe for push", e);
                        }
                    }
                });
            } else {
                Prefs.putString("subscribedToChannel", "English");

                ParsePush.subscribeInBackground("English", new SaveCallback() {
                    @Override
                    public void done(ParseException e) {
                        if (e == null) {
                            Log.d("com.parse.push", "successfully subscribed to the broadcast channel.");

                        } else {
                            Log.e("com.parse.push", "failed to subscribe for push", e);
                        }
                    }
                });
            }
            Prefs.putBoolean("languageSubscribed", true);
        }


    }
}
