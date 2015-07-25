package me.montecode.app.summertangocamp.kolasin;

import io.realm.RealmList;
import io.realm.RealmObject;

/**
 * Created by stevyhacker on 25.7.15..
 */
public class Day extends RealmObject {

    private String day;

    private RealmList<Activities> activities;


    public RealmList<Activities> getActivities() {
        return activities;
    }

    public void setActivities(RealmList<Activities> activities) {
        this.activities = activities;
    }

    public String getDay() {
        return day;
    }

    public void setDay(String day) {
        this.day = day;
    }

}
