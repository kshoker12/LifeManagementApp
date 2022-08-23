package Model;

import org.json.JSONArray;
import org.json.JSONObject;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;

public class Category extends Component {
    private List<Attribute> attributes;

    public Category(String name) {
        super(name);
        attributes = new ArrayList<>();
    }

    private void setRating() {
        DecimalFormat numberFormat = new DecimalFormat("#.0");
        Double rating = 0.0;
        for (Attribute next: attributes) {
            rating += next.getRating();
        }
        rating = rating / attributes.size();
        this.rating = Double.valueOf(numberFormat.format(rating));
    }

    @Override
    public void update() {
        for (Attribute next: attributes) {
            next.update();
        }
        setRating();
    }

    public List<Attribute> getAttributes() {
        return attributes;
    }

    public void addAttribute(Attribute attribute) {
        attributes.add(attribute);
        setRating();
    }

    public void setRating(Double rating) {
        this.rating = rating;
    }

    @Override
    public JSONObject toJson() {
        JSONObject json = new JSONObject();
        json.put("name", name);
        json.put("rating", rating);
        json.put("attributes", attributesToJson());
        return json;
    }

    // EFFECTS: turns the exercises represented by this muscle group as a JSON array
    private JSONArray attributesToJson() {
        JSONArray attributes = new JSONArray();

        for (Attribute e : this.attributes) {
            attributes.put(e.toJson());
        }

        return attributes;
    }
}
