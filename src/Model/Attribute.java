package Model;

import org.json.JSONObject;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;

public class Attribute extends Component {
    private List<Integer> weeklyRatings;
    private String category;

    public Attribute(String name, double rating, String category) {
        super(name);
        this.rating = rating;
        this.weeklyRatings = new ArrayList<>();
        this.category = category;
    }

    @Override
    public void update() {
        Double average = getAverage();
        if (average >= 5) {
            addAverage(average);
        } else {
            deductAverage(average);
        }
        setRating();
    }

    private void setRating() {
        DecimalFormat numberFormat = new DecimalFormat("#.0");
        this.rating = Double.valueOf(numberFormat.format(rating));
    }

    private void deductAverage(Double average) {
        if (rating > 90) {
            rating -= average / 9;
        } else if (rating > 80) {
            rating -= average / 8;
        } else if (rating > 70) {
            rating -= average / 7;
        } else if (rating > 60) {
            rating -= average / 6;
        } else if (rating > 50) {
            rating -= average / 5;
        } else if (rating > 40) {
            rating -= average / 4;
        } else if (rating > 30) {
            rating -= average / 3;
        } else if (rating > 20) {
            rating -= average / 2;
        } else if (rating > 0) {
            rating -= average;
        } else {
            rating = 0.0;
        }
    }

    private void addAverage(Double average) {
        if (rating > 90) {
            rating += average / 9;
        } else if (rating > 80) {
            rating += average / 8;
        } else if (rating > 70) {
            rating += average / 7;
        } else if (rating > 60) {
            rating += average / 6;
        } else if (rating > 50) {
            rating += average / 5;
        } else if (rating > 40) {
            rating += average / 4;
        } else if (rating > 30) {
            rating += average / 3;
        } else if (rating > 20) {
            rating += average / 2;
        } else if (rating > 0) {
            rating += average;
        } else {
            rating = average;
        }
    }

    private Double getAverage() {
        Double average = 0.0;
        for (Integer next: weeklyRatings) {
            average += next;
        }
        weeklyRatings.clear();
        return average / 7;
    }

    public String getCategory() {
        return category;
    }

    public void addRating(int rating) {
        weeklyRatings.add(rating);
    }

    public List<Integer> getWeeklyRatings() {
        return weeklyRatings;
    }

    public String obtainRating(Integer i) {
        int size = weeklyRatings.size() - 1;
        if (i > size) {
            return "N/A";
        }
        return weeklyRatings.get(i).toString();
    }

    @Override
    public JSONObject toJson() {
        JSONObject json = new JSONObject();
        json.put("name", name);
        json.put("weeklyRatings", weeklyRatings);
        json.put("rating", rating);
        json.put("category", category);
        return json;
    }
}
