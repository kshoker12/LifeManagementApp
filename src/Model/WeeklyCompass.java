package Model;

import Persistence.Writable;
import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class WeeklyCompass implements Writable {
    private String name;
    private List<Destination> destinationList;
    private int totalPoints;
    private int earnedPoints;
    private int percentage;

    public WeeklyCompass(String name) {
        this.name = name;
        destinationList = new ArrayList<>();
        getPoints();
        this.earnedPoints = 0;
        this.percentage = 0;
    }

    public boolean isFinish() {
        boolean finish = true;
        for (Destination next: destinationList) {
            if (!next.isFinish()) {
                finish = false;
                break;
            }
        }
        return finish;
    }


    public void addDestination(Destination destination) {
        this.destinationList.add(destination);
        getPoints();
    }

    public List<Destination> getDestinationList() {
        return destinationList;
    }

    public String getName() {
        return name;
    }

    public int getTotalPoints() {
        return totalPoints;
    }

    public int getPercentage() {
        return percentage;
    }

    public void reset() {
        for (Destination next: destinationList) {
             next.reset();
        }
        earnedPoints = 0;
        percentage = 0;
        getPoints();
    }

    public void getPoints() {
        int points = 0;
        for (Destination next: destinationList) {
            points += next.getPoint();
        }
        totalPoints = points;
    }

    public int getTotalEarnedPoints() {
        return earnedPoints;
    }

    public void getEarnedPoints() {
        int points = 0;
        for (Destination next: destinationList) {
            points += next.getEarned();
        }
        this.earnedPoints = points;
        double perc = (double) points / totalPoints * 100;
        percentage = (int) perc;
    }

    public void setPercentage(int percentage) {
        this.percentage = percentage;
    }

    public void setEarnedPoints(int earnedPoints) {
        this.earnedPoints = earnedPoints;
    }

    public void setTotalPoints(int totalPoints) {
        this.totalPoints = totalPoints;
    }

    public void setDestinationList(List<Destination> destinationList) {
        this.destinationList = destinationList;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public JSONObject toJson() {
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("name", name);
        jsonObject.put("destinationList", destinationListToJson());
        jsonObject.put("totalPoints", totalPoints);
        jsonObject.put("earnedPoints", earnedPoints);
        jsonObject.put("percentage", percentage);
        return jsonObject;
    }

    private JSONArray destinationListToJson() {
        JSONArray jsonArray = new JSONArray();
        for (Destination next: destinationList) {
            jsonArray.put(next.toJson());
        }
        return jsonArray;
    }
}
