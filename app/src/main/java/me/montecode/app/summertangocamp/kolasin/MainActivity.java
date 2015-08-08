package me.montecode.app.summertangocamp.kolasin;

import android.os.Build;
import android.os.Bundle;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBar;
import android.support.v7.app.ActionBarActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.Window;
import android.view.WindowManager;

import com.parse.ParseAnalytics;
import com.pixplicity.easyprefs.library.Prefs;

import java.io.IOException;
import java.io.InputStream;

import io.realm.Realm;


public class MainActivity extends ActionBarActivity {

    /**
     * Fragment managing the behaviors, interactions and presentation of the navigation drawer.
     */
    private NavigationDrawerFragment mNavigationDrawerFragment;

    /**
     * Used to store the last screen title. For use in {@link #restoreActionBar()}.
     */
    private CharSequence mTitle;
    Realm realm;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mNavigationDrawerFragment = (NavigationDrawerFragment)
                getSupportFragmentManager().findFragmentById(R.id.navigation_drawer);
        mTitle = getTitle();

        // Set up the drawer.
        mNavigationDrawerFragment.setUp(
                R.id.navigation_drawer,
                (DrawerLayout) findViewById(R.id.drawer_layout));


        int currentapiVersion = android.os.Build.VERSION.SDK_INT;


        if (currentapiVersion >= Build.VERSION_CODES.LOLLIPOP) {
            Window window = getWindow();
            window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
            window.clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
            window.setStatusBarColor(getResources().getColor(R.color.primary_dark_status));

        }

        if (getIntent().getExtras() != null) {

            Bundle extras = getIntent().getExtras();
            String notificationType = extras.getString("notification");
            if (notificationType != null) {

                if (notificationType.equalsIgnoreCase("parse")) {
                    ParseAnalytics.trackAppOpenedInBackground(getIntent());
                    NotificationsFragment notificationFragment = new NotificationsFragment();
                    FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
                    transaction.replace(R.id.container, notificationFragment);
                    transaction.addToBackStack(null);
                    transaction.commit();
                    setTitle(getString(R.string.notifications));
                }
            }
        }



        InputStream is = null;

        if(!Prefs.getBoolean("firstTime",false)){
            Realm.deleteRealmFile(getApplicationContext());
            realm = Realm.getInstance(this);

            Prefs.putBoolean("firstTime",true);
            Prefs.putBoolean("inputDone", true);
            try {

                if (Prefs.getString("subscribedToChannel", "English").equalsIgnoreCase("English")) {
                    is = getAssets().open("schedule_9_11.json");
                } else {
                    is = getAssets().open("raspored_9_11.json");
                }

                realm.beginTransaction();
                realm.createObjectFromJson(Schedule.class, is);
                realm.commitTransaction();

                Prefs.putBoolean("jsonInputDone", true);
            } catch (IOException e) {
                e.printStackTrace();
            }

        }

        realm = Realm.getInstance(this);

        boolean jsonInputDone = Prefs.getBoolean("jsonInputDone", false);
        boolean inputDone = Prefs.getBoolean("inputDone", false);

        if (!jsonInputDone && !inputDone) {

            try {

                if (Prefs.getString("subscribedToChannel", "English").equalsIgnoreCase("English")) {
                    is = getAssets().open("schedule_9_11.json");
                } else {
                    is = getAssets().open("raspored_9_11.json");
                }

                realm.beginTransaction();
                realm.createObjectFromJson(Schedule.class, is);
                realm.commitTransaction();


                Prefs.putBoolean("jsonInputDone", true);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        if (findViewById(R.id.container) != null) {

            if (savedInstanceState != null) {
                return;
            }

            NotificationsFragment notificationFragment = new NotificationsFragment();
            notificationFragment.setArguments(getIntent().getExtras());

            getSupportFragmentManager().beginTransaction()
                    .add(R.id.container, notificationFragment).commit();

        }

    }


    public void restoreActionBar() {
        ActionBar actionBar = getSupportActionBar();
        actionBar.setNavigationMode(ActionBar.NAVIGATION_MODE_STANDARD);
        actionBar.setDisplayShowTitleEnabled(true);
        actionBar.setTitle(mTitle);
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        if (!mNavigationDrawerFragment.isDrawerOpen()) {
            // Only show items in the action bar relevant to this screen
            // if the drawer is not showing. Otherwise, let the drawer
            // decide what to show in the action bar.
//            getMenuInflater().inflate(R.menu.main, menu);
            restoreActionBar();
            return true;
        }
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
//        if (id == R.id.action_settings) {
//            return true;
//        }

        return super.onOptionsItemSelected(item);
    }

    /**
     * A placeholder fragment containing a simple view.
     */
//    public static class PlaceholderFragment extends Fragment {
//        /**
//         * The fragment argument representing the section number for this
//         * fragment.
//         */
//        private static final String ARG_SECTION_NUMBER = "section_number";
//
//        /**
//         * Returns a new instance of this fragment for the given section
//         * number.
//         */
//        public static PlaceholderFragment newInstance(int sectionNumber) {
//            PlaceholderFragment fragment = new PlaceholderFragment();
//            Bundle args = new Bundle();
//            args.putInt(ARG_SECTION_NUMBER, sectionNumber);
//            fragment.setArguments(args);
//            return fragment;
//        }
//
//        public PlaceholderFragment() {
//        }
//
//        @Override
//        public View onCreateView(LayoutInflater inflater, ViewGroup container,
//                                 Bundle savedInstanceState) {
//            View rootView = inflater.inflate(R.layout.fragment_main, container, false);
//            return rootView;
//        }
//
//        @Override
//        public void onAttach(Activity activity) {
//            super.onAttach(activity);
//            ((MainActivity) activity).onSectionAttached(
//                    getArguments().getInt(ARG_SECTION_NUMBER));
//        }
//    }

}
