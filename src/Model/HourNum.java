package Model;

import Persistence.Writable;
import org.json.JSONObject;

public class HourNum implements Writable {
    private int hour;

    public HourNum() {
        hour = 1;
    }

    public void incrementHour() {
        hour+=1;
    }

    public void resetHour() {
        hour = 1;
    }

    public int getHour() {
        return hour;
    }

    public String getEnding() {
        String ending = "";
        if (hour == 1) {
            ending = "st";
        } else if (hour == 2) {
            ending = "nd";
        } else if (hour == 3) {
            ending = "rd";
        } else {
            ending = "th";
        }
        return ending;
    }

    public void setHour(int hour) {
        this.hour = hour;
    }

    @Override
    public JSONObject toJson() {
        JSONObject json = new JSONObject();
        json.put("hour", hour);
        return json;
    }
}
