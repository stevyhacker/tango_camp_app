package me.montecode.app.summertangocamp.kolasin;

import io.realm.RealmObject;

/**
 * Created by stevyhacker on 25.7.15..
 */
public class Activities extends RealmObject {


    private String description;

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

}
