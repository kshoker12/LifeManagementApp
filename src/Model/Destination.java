package Model;

import Persistence.Writable;
import org.json.JSONObject;

public class Destination implements Writable {
    private String name;
    private int point;
    private int earned;
    private boolean finish;


    public Destination(int point) {
        this.point = point;
        this.finish = false;
        this.earned = -1;
        this.name = "";
    }

    public boolean isFinish() {
        return finish;
    }

    public int getPoint() {
        return point;
    }

    public String getName() {
        return name;
    }

    public int getEarned() {
        return earned;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setEarned(int earned) {
        this.earned = earned;
        this.finish = true;
    }

    public void setEarned(int earned, String s) {
        this.earned = earned;
    }

    public void setFinish(boolean finish) {
        this.finish = finish;
    }

    public void reset() {
        this.earned = -1;
        this.name = "";
        this.finish = false;
    }

    @Override
    public JSONObject toJson() {
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("name", name);
        jsonObject.put("point", point);
        jsonObject.put("earned", earned);
        jsonObject.put("finish", finish);
        return jsonObject;
    }
}
