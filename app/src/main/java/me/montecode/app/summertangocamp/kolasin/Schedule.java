package me.montecode.app.summertangocamp.kolasin;
import io.realm.RealmList;
import io.realm.RealmObject;

/**
 * Created by stevyhacker on 12.4.15..
 */
public class Schedule extends RealmObject {


    private String push_id;
    private String silent;
    private String alert;
    private String additional_data;
    private RealmList<Day> data;

    public String getPush_id() {
        return push_id;
    }

    public void setPush_id(String push_id) {
        this.push_id = push_id;
    }

    public String getSilent() {
        return silent;
    }

    public void setSilent(String silent) {
        this.silent = silent;
    }

    public String getAlert() {
        return alert;
    }

    public void setAlert(String alert) {
        this.alert = alert;
    }

    public RealmList<Day> getData() {
        return data;
    }

    public void setData(RealmList<Day> data) {
        this.data = data;
    }

    public String getAdditional_data() {
        return additional_data;
    }

    public void setAdditional_data(String additional_data) {
        this.additional_data = additional_data;
    }


}
