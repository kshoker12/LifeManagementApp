package Model;

import org.json.JSONArray;
import org.json.JSONObject;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class RatingSystem extends Component implements Iterable<Attribute> {
    private List<Category> categories;

    public RatingSystem(String name) {
        super(name);
        categories = new ArrayList<>();
    }

    public void setRating(Double rating) {
        this.rating = rating;
    }

    private void setRating() {
        DecimalFormat numberFormat = new DecimalFormat("#.0");
        Double rating = 0.0;
        for (Category next: categories) {
            rating += next.getRating();
        }
        rating = rating / categories.size();
        this.rating = Double.valueOf(numberFormat.format(rating));
    }

    public void addCategory(Category category) {
        categories.add(category);
        setRating();
    }

    @Override
    public void update() {
        for (Category next: categories) {
            next.update();
        }
        setRating();
    }

    public List<Category> getCategories() {
        return categories;
    }

    @Override
    // EFFECTS: returns Workouts represented by this Routine as a JSON object
    public JSONObject toJson() {
        JSONObject json = new JSONObject();
        json.put("name", name);
        json.put("categories", categoriesToJson());
        json.put("rating", rating);
        return json;
    }

    // EFFECTS: returns this Routine as a JSON array
    private JSONArray categoriesToJson() {
        JSONArray categories = new JSONArray();

        for (Category w : this.categories) {
            categories.put(w.toJson());
        }

        return categories;
    }

    @Override
    public Iterator<Attribute> iterator() {
        return new RatingSystemIterator();
    }

    private class RatingSystemIterator implements Iterator<Attribute> {
        private Iterator<Attribute> mentalToughnessIterator = categories.get(0).getAttributes().iterator();
        private Iterator<Attribute> mindsetIterator = categories.get(1).getAttributes().iterator();
        private Iterator<Attribute> workEthicIterator = categories.get(2).getAttributes().iterator();
        private Iterator<Attribute> socialSkillsIterator = categories.get(3).getAttributes().iterator();

        @Override
        public boolean hasNext() {
            return mentalToughnessIterator.hasNext() || mindsetIterator.hasNext() || workEthicIterator.hasNext() ||
                    socialSkillsIterator.hasNext();
        }

        @Override
        public Attribute next() {
            Attribute attribute = null;
            if (mentalToughnessIterator.hasNext()) {
                attribute = mentalToughnessIterator.next();
            } else if (mindsetIterator.hasNext()) {
                attribute = mindsetIterator.next();
            } else if (workEthicIterator.hasNext()) {
                attribute = workEthicIterator.next();
            } else if (socialSkillsIterator.hasNext()) {
                attribute = socialSkillsIterator.next();
            }
            return attribute;
        }
    }
}
